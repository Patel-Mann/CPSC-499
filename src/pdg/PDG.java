package pdg;

import org.lsmr.cfg.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple Program Dependence Graph (PDG)
 * Computes control and data dependencies for a CFG
 */
public class PDG {

    private ControlFlowGraph cfg;
    private Map<String, Set<String>> controlDeps;
    private Map<String, Set<String>> dataDeps;

    public PDG(ControlFlowGraph cfg) {
        this.cfg = cfg;
        this.controlDeps = new HashMap<>();
        this.dataDeps = new HashMap<>();

        System.out.println("Building PDG...");
        computeControlDependencies();
        computeDataDependencies();
        System.out.println("PDG complete!");
    }

    /**
     * Compute control dependencies using simple reachability
     */
    private void computeControlDependencies() {
        System.out.println("\tComputing control dependencies...");

        boolean changed = true;

        while (changed) {
            changed = false;

            for (Node node : cfg.nodes()) {
                Set<String> deps = new HashSet<>();

                for (Edge inEdge : node.inEdges()) {
                    Node pred = inEdge.source();

                    // get all deps from
                    if (controlDeps.containsKey(pred.label())) {
                        deps.addAll(controlDeps.get(pred.label()));
                    }

                    // update deps if pred is a branching node liek if and while
                    if (pred.outEdges().size() > 1) {
                        deps.add(pred.label());
                    }
                }
                // compare prev iteration to current if we found changes in depns update control
                // deps. else exit loop
                Set<String> oldDeps = controlDeps.get(node.label());
                if (oldDeps == null || !oldDeps.equals(deps)) {
                    controlDeps.put(node.label(), deps);
                    changed = true;
                }
            }
        }

        System.out.println("\t\tFound " + countDependencies(controlDeps) + " control dependencies");
    }

    /**
     * Compute data dependencies using reaching definitions
     */
    private void computeDataDependencies() {
        System.out.println("  Computing data dependencies...");

        // 1. Find definitions and uses for each node
        Map<String, Set<String>> defs = new HashMap<>();
        Map<String, Set<String>> uses = new HashMap<>();

        for (Node node : cfg.nodes()) {
            String label = node.label();
            defs.put(label, extractDefs(label));
            uses.put(label, extractUses(label));
        }
        System.out.println(defs.toString());
        System.out.println(uses.toString());

        // 2. Compute reaching definitions
        Map<String, Map<String, Set<String>>> reaching = computeReachingDefinitions(defs);

        // 3. Build data dependencies
        for (Node node : cfg.nodes()) {
            String label = node.label();
            Set<String> deps = new HashSet<>();

            for (String var : uses.get(label)) {
                if (reaching.containsKey(label) && reaching.get(label).containsKey(var)) {
                    deps.addAll(reaching.get(label).get(var));
                }
            }

            dataDeps.put(label, deps);
        }

        System.out.println("    Found " + countDependencies(dataDeps) + " data dependencies");
    }

    /**
     * Extract variable definitions from a statement simple pattern matching for
     * Java 1.4
     */
    private Set<String> extractDefs(String statement) {
        Set<String> vars = new HashSet<>();
        // exclude comments
        if (statement.startsWith("*") || statement.startsWith("/"))
            return vars;

        // used for normal assignments exclude anything else
        boolean isDef = statement.contains("=") &&
                !statement.contains("==") &&
                !statement.contains("<=") &&
                !statement.contains(">=") &&
                !statement.contains("!=");

        if (isDef) {
            String lhs = statement.substring(0, statement.indexOf('=')).trim();

            // remove left over operators after getting teh substring
            if (lhs.endsWith("+") || lhs.endsWith("-") || lhs.endsWith("*") || lhs.endsWith("/") || lhs.endsWith("%")
                    || lhs.endsWith("&") || lhs.endsWith("|") || lhs.endsWith("^")) {
                lhs = lhs.substring(0, lhs.length() - 1).trim();
            }

            // for possible arrays
            if (lhs.contains("[")) {
                lhs = lhs.substring(0, lhs.indexOf('[')).trim();
            }

            // get last "word" splits by whitespace
            String[] tokens = lhs.split("\\s+");
            if (tokens.length > 0) {
                String var = tokens[tokens.length - 1];
                if (!var.isEmpty() && Character.isJavaIdentifierStart(var.charAt(0))) {
                    vars.add(var);
                }
            }
        }

        // Handle For Loops
        if (statement.contains("for") && statement.contains("(")) {
            String inside = statement.substring(statement.indexOf("(") + 1);
            if (inside.contains(";")) {
                String[] parts = inside.split(";");

                // Init def: "int i = 0"
                if (parts.length > 0 && parts[0].contains("=")) {
                    String part = parts[0].substring(0, parts[0].indexOf('=')).trim();
                    String[] tokens = part.split("\\s+");
                    if (tokens.length > 0) {
                        // retrieve last element
                        String var = tokens[tokens.length - 1];
                        vars.add(var);
                    }
                }

                // Update def for: "i++", "i--", "++i"
                if (parts.length >= 3) {
                    String update = parts[parts.length - 1];
                    update = update.replaceAll("\\)", ""); // remove closing paren if present
                    if (update.contains("++") || update.contains("--")) {
                        String var = update;
                        if (!var.isEmpty())
                            vars.add(var);
                    }
                }
            }
        }

        return vars;
    }

    /**
     * Extract variable uses from a statement
     */
    private Set<String> extractUses(String statement) {
        Set<String> vars = new HashSet<>();

        // check for normal definition
        boolean isDef = statement.contains("=") &&
                !statement.contains("==") &&
                !statement.contains("<=") &&
                !statement.contains(">=") &&
                !statement.contains("!=");

        String searchArea = statement;

        // Two cases: 1. normal def only RHS matters 2. compound def both LHS and RHS
        // matter
        if (isDef) {
            int eqIndex = statement.indexOf('=');
            // check for += -= etc. if it exists we found a compound def
            boolean isCompound = eqIndex > 0 && "+-*/%&|^".indexOf(statement.charAt(eqIndex - 1)) != -1;

            if (isCompound) {
                // both RHS & LHS
                searchArea = statement;
            } else {
                // only RHS
                searchArea = statement.substring(eqIndex + 1); // skip anything before =
            }
        }

        // Tokenize and filter
        // fancy split on non-identifier characters to extract all variables used
        String[] tokens = searchArea.split("[^a-zA-Z0-9_]+");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.isEmpty())
                continue;

            // Basic validity checks
            if (Character.isJavaIdentifierStart(token.charAt(0))
                    && !isKeyword(token)
                    && !token.matches("\\d+")) {
                // skip if its a method
                boolean isMethodCall = statement.matches(".*\\b" + token + "\\s*\\(.*");

                if (!isMethodCall) {
                    vars.add(token);
                }
            }
        }
        return vars;
    }

    /**
     * Check if a word is a Java keyword
     */
    private boolean isKeyword(String word) {
        Set<String> keywords = new HashSet<>(Arrays.asList(
                "if", "else", "while", "for", "do", "return", "break", "continue",
                "int", "double", "float", "char", "boolean", "void", "String",
                "new", "null", "true", "false", "class", "public", "private",
                "static", "final", "this", "super", "try", "catch", "throw", "throws", "EXIT", "block", "ENTRY",
                "THROWN"));
        return keywords.contains(word);
    }

    /**
     * Compute reaching definitions using iterative data flow analysis
     */
    private Map<String, Map<String, Set<String>>> computeReachingDefinitions(
            Map<String, Set<String>> defs) {

        Map<String, Map<String, Set<String>>> reaching = new HashMap<>();
        for (Node node : cfg.nodes()) {
            reaching.put(node.label(), new HashMap<>());
        }
        boolean changed = true;
        while (changed) {
            changed = false;

            for (Node node : cfg.nodes()) {
                String label = node.label();
                Map<String, Set<String>> oldReaching = new HashMap<>(reaching.get(label));
                Map<String, Set<String>> newReaching = new HashMap<>();
                for (Edge inEdge : node.inEdges()) {
                    Node pred = inEdge.source();
                    String predLabel = pred.label();

                    Map<String, Set<String>> predOut = new HashMap<>();

                    // In predecesor def
                    if (reaching.containsKey(predLabel)) {
                        for (Map.Entry<String, Set<String>> entry : reaching.get(predLabel).entrySet()) {
                            // create a copy of the set
                            predOut.put(entry.getKey(), new HashSet<>(entry.getValue()));
                        }
                    }

                    // kill & gen
                    if (defs.containsKey(predLabel)) {
                        for (String var : defs.get(predLabel)) {
                            // since new def, we update definition of x to new definition
                            Set<String> newDef = new HashSet<>();
                            newDef.add(predLabel);
                            predOut.put(var, newDef);
                        }
                    }

                    // merge
                    for (Map.Entry<String, Set<String>> entry : predOut.entrySet()) {
                        String var = entry.getKey();
                        newReaching.putIfAbsent(var, new HashSet<>());
                        newReaching.get(var).addAll(entry.getValue());
                    }
                }

                reaching.put(label, newReaching);
                // nothing changed then exit
                if (!mapsEqual(oldReaching, newReaching)) {
                    changed = true;
                }
            }
        }

        return reaching;
    }

    private boolean mapsEqual(Map<String, Set<String>> m1, Map<String, Set<String>> m2) {
        if (m1.size() != m2.size())
            return false;
        for (String key : m1.keySet()) {
            if (!m2.containsKey(key))
                return false;
            if (!m1.get(key).equals(m2.get(key)))
                return false;
        }
        return true;
    }

    private int countDependencies(Map<String, Set<String>> deps) {
        int count = 0;
        for (Set<String> set : deps.values()) {
            count += set.size();
        }
        return count;
    }

    public Set<String> getDependencies(String nodeLabel) {
        Set<String> all = new HashSet<>();
        if (controlDeps.containsKey(nodeLabel)) {
            all.addAll(controlDeps.get(nodeLabel));
        }
        if (dataDeps.containsKey(nodeLabel)) {
            all.addAll(dataDeps.get(nodeLabel));
        }
        return all;
    }

    public Set<String> computeForwardSlice(String nodeLabel) {
        Set<String> slice = new HashSet<>();
        Queue<String> worklist = new LinkedList<>();

        slice.add(nodeLabel);
        worklist.add(nodeLabel);
        while (!worklist.isEmpty()) {
            String current = worklist.poll();
            for (String node : getAllNodes()) {
                if (!slice.contains(node)) {
                    Set<String> deps = getDependencies(node);
                    if (deps.contains(current)) {
                        slice.add(node);
                        worklist.add(node);
                    }
                }
            }
        }

        return slice;
    }

    private Set<String> getAllNodes() {
        Set<String> nodes = new HashSet<>();
        for (Node node : cfg.nodes()) {
            nodes.add(node.label());
        }
        return nodes;
    }

    public void printPDG() {
        System.out.println("CONTROL DEPENDENCIES:");
        for (Map.Entry<String, Set<String>> entry : controlDeps.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                System.out.println("  " + entry.getKey());
                for (String dep : entry.getValue()) {
                    System.out.println("    <- " + dep);
                }
            }
        }

        System.out.println("\nDATA DEPENDENCIES:");
        for (Map.Entry<String, Set<String>> entry : dataDeps.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                System.out.println("  " + entry.getKey());
                for (String dep : entry.getValue()) {
                    System.out.println("    <- " + dep);
                }
            }
        }
    }

    public Map<String, Set<String>> getControlDependencies() {
        return new HashMap<>(controlDeps);
    }

    public Map<String, Set<String>> getDataDependencies() {
        return new HashMap<>(dataDeps);
    }
}
