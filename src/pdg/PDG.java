package pdg;

import org.lsmr.cfg.*;
import java.util.*;

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

        for (Node node : cfg.nodes()) {
            Set<String> deps = new HashSet<>();

            for (Edge inEdge : node.inEdges()) {
                Node pred = inEdge.source();

                if (pred.outEdges().size() > 1) {
                    deps.add(pred.label());

                    if (controlDeps.containsKey(pred.label())) {
                        deps.addAll(controlDeps.get(pred.label()));
                    }
                }
            }

            controlDeps.put(node.label(), deps);
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

        // 2. Compute reaching definitions
        Map<String, Map<String, Set<String>>> reaching = computeReachingDefinitions(defs);
        // TODO: check what happens whena node has multiple variable definitions

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

        // Skip special nodes
        if (statement.startsWith("*"))
            return vars;

        if (statement.contains("=") && !statement.contains("==")) {
            String[] parts = statement.split("=");
            if (parts.length > 0) {
                String lhs = parts[0].trim();
                String[] tokens = lhs.split("\\s+");
                if (tokens.length > 0) {
                    String varName = tokens[tokens.length - 1];
                    varName = varName.replaceAll("[\\[\\].();,]", "");
                    if (!varName.isEmpty() && Character.isJavaIdentifierStart(varName.charAt(0))) {
                        vars.add(varName);
                    }
                }
            }
        }

        if (statement.contains("for") && statement.contains("(")) {
            String forPart = statement.substring(statement.indexOf("("));
            String[] forParts = forPart.split(";");
            if (forParts.length >= 3) {
                if (forParts[0].contains("=")) {
                    String[] initParts = forParts[0].split("=");
                    if (initParts.length > 0) {
                        String var = initParts[0].trim().replaceAll("[^a-zA-Z0-9_]", "");
                        if (!var.isEmpty())
                            vars.add(var);
                    }
                }
                String update = forParts[forParts.length - 1];
                if (update.contains("++") || update.contains("--")) {
                    String var = update.replaceAll("[^a-zA-Z0-9_]", "");
                    if (!var.isEmpty())
                        vars.add(var);
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

        if (statement.startsWith("*"))
            return vars;
        String[] tokens = statement.split("[\\s+\\-*/=<>!&|(){}\\[\\];,.]");
        for (String token : tokens) {
            token = token.trim();
            if (!token.isEmpty() &&
                    Character.isJavaIdentifierStart(token.charAt(0)) &&
                    !isKeyword(token) &&
                    !token.matches("\\d+")) {
                vars.add(token);
            }
        }
        vars.removeAll(extractDefs(statement));

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
                "static", "final", "this", "super", "try", "catch", "throw", "throws"));
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
        int iterations = 0;
        while (changed && iterations < 100) {
            changed = false;
            iterations++;

            for (Node node : cfg.nodes()) {
                String label = node.label();
                Map<String, Set<String>> oldReaching = new HashMap<>(reaching.get(label));
                Map<String, Set<String>> newReaching = new HashMap<>();
                for (Edge inEdge : node.inEdges()) {
                    Node pred = inEdge.source();
                    String predLabel = pred.label();
                    if (reaching.containsKey(predLabel)) {
                        for (Map.Entry<String, Set<String>> entry : reaching.get(predLabel).entrySet()) {
                            String var = entry.getKey();
                            newReaching.putIfAbsent(var, new HashSet<>());
                            newReaching.get(var).addAll(entry.getValue());
                        }
                    }

                    // Add predecessor's definitions
                    for (String var : defs.get(predLabel)) {
                        newReaching.putIfAbsent(var, new HashSet<>());
                        newReaching.get(var).clear();
                        newReaching.get(var).add(predLabel);
                    }
                }

                reaching.put(label, newReaching);

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
