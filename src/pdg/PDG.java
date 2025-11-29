// program Dependence Graph implementation using the Profs CFG.
// combines control dependence and data dependence.
package pdg;

import org.lsmr.cfg.ControlFlowGraph;
import org.lsmr.cfg.Node;
import org.lsmr.cfg.Edge;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.*;

public class PDG {
    private ControlFlowGraph cfg;
    private Graph<PDGNode, PDGEdge> pdg;
    private Map<Node, PDGNode> cfgToPdgMap;

    // Analysis results
    private Map<Node, Set<Node>> postDominators;
    private Map<Node, Set<Node>> controlDependence;
    private Map<Node, Set<Node>> dataDependence;

    public PDG(ControlFlowGraph cfg) {
        this.cfg = cfg;
        this.pdg = new DefaultDirectedGraph<>(PDGEdge.class);
        this.cfgToPdgMap = new HashMap<>();
        this.postDominators = new HashMap<>();
        this.controlDependence = new HashMap<>();
        this.dataDependence = new HashMap<>();

        buildPDG();
    }

    // build the complete PDG.
    private void buildPDG() {
        // Step 1: create PDG nodes from CFG nodes
        createPDGNodes();
        // Step 2: compute control dependence
        computeControlDependence();
        // Step 3: compute data dependence
        computeDataDependence();
        // Step 4: add edges to PDG
        addPDGEdges();
    }

    // Create a PDG node for each CFG node.
    private void createPDGNodes() {
        for (Node cfgNode : cfg.nodes()) {
            PDGNode pdgNode = new PDGNode(cfgNode);
            pdg.addVertex(pdgNode);
            cfgToPdgMap.put(cfgNode, pdgNode);
        }
    }

    // Compute control dependence using post-dominator analysis.
    // Source: https://pages.cs.wisc.edu/~fischer/cs701.f14/lectures/L6.4up.pdf
    private void computeControlDependence() {
        // If, compute post-dominators
        computePostDominators();
        // Then, compute control dependence
        for (Node node : cfg.nodes()) {
            controlDependence.put(node, new HashSet<>());
        }

        // for each edge X -> Y in the CFG
        for (Node x : cfg.nodes()) {
            for (Edge edge : x.outEdges()) {
                Node y = edge.target();
                if (y == null) continue;
                // find all nodes that are control-dependent on X via this edge
                for (Node node : cfg.nodes()) {
                    // Node is control-dependent on X if:
                    // 1. Node post-dominates Y (the successor)
                    // 2. Node does NOT post-dominate X
                    if (postDominates(node, y) && !postDominates(node, x)) {
                        controlDependence.get(x).add(node);
                    }
                }
            }
        }
    }

    // compute post-dominators using fixed-point algorithm.
    // node Y post-dominates X if all paths from X to exit go through Y.
    private void computePostDominators() {
        List<Node> allNodes = cfg.nodes();
        Node exitNode = cfg.normalExit;
        for (Node node : allNodes) {
            postDominators.put(node, new HashSet<>(allNodes));
        }

        if (exitNode != null) {
            postDominators.put(exitNode, new HashSet<>(Arrays.asList(exitNode)));
        }

        boolean changed = true;
        while (changed) {
            changed = false;

            for (Node node : allNodes) {
                if (node.equals(exitNode)) continue;

                Set<Node> newPostDom = new HashSet<>();
                newPostDom.add(node); // Node post-dominates itself

                Set<Edge> successorEdges = node.outEdges();
                if (!successorEdges.isEmpty()) {
                    boolean first = true;
                    for (Edge edge : successorEdges) {
                        Node successor = edge.target();
                        if (successor == null) continue;

                        if (first) {
                            newPostDom.addAll(postDominators.get(successor));
                            first = false;
                        } else {
                            newPostDom.retainAll(postDominators.get(successor));
                        }
                    }
                }

                if (!newPostDom.equals(postDominators.get(node))) {
                    postDominators.put(node, newPostDom);
                    changed = true;
                }
            }
        }
    }

    private boolean postDominates(Node dominator, Node node) {
        Set<Node> postDoms = postDominators.get(node);
        return postDoms != null && postDoms.contains(dominator);
    }

    private void computeDataDependence() {
        Map<Node, Set<String>> defs = new HashMap<>();
        Map<Node, Set<String>> uses = new HashMap<>();

        for (Node node : cfg.nodes()) {
            defs.put(node, extractDefs(node));
            uses.put(node, extractUses(node));
            PDGNode pdgNode = cfgToPdgMap.get(node);
            pdgNode.setDefs(defs.get(node));
            pdgNode.setUses(uses.get(node));
        }
        Map<Node, Set<Definition>> reachingDefs = computeReachingDefinitions(defs);
        for (Node node : cfg.nodes()) {
            dataDependence.put(node, new HashSet<>());
        }
        for (Node use : cfg.nodes()) {
            Set<String> usedVars = uses.get(use);
            Set<Definition> reaching = reachingDefs.get(use);

            for (Definition def : reaching) {
                if (usedVars.contains(def.variable)) {
                  dataDependence.get(def.node).add(use);
                }
            }
        }
    }

    // Extract variable definitions from a CFG node.
    private Set<String> extractDefs(Node node) {
        Set<String> defs = new HashSet<>();
        String label = node.label();

        if (label.startsWith("*")) {   // Skip special nodes
            return defs;
        }

        if (label.contains("=") && !label.contains("==")) {   // Look for assignments: variable = ...
            try {
                String[] parts = label.split("=");
                if (parts.length >= 2) {
                    String varPart = parts[0].trim();

                    // handle "int v" or just "v"
                    String[] tokens = varPart.split("\\s+");
                    String varName = tokens[tokens.length - 1].replaceAll("[^a-zA-Z0-9_]", "");

                    if (varName.length() > 0 && Character.isJavaIdentifierStart(varName.charAt(0))) {
                        defs.add(varName);
                    }
                }
            } catch (Exception e) {
                // parsing failed, ignore
            }
        }

        return defs;
    }

    // Extract variable uses from a CFG node.
    private Set<String> extractUses(Node node) {
        Set<String> uses = new HashSet<>();
        String label = node.label();

        // Skip special nodes
        if (label.startsWith("*")) {
            return uses;
        }

        // For assignments, extract from right-hand side
        if (label.contains("=") && !label.contains("==")) {
            String[] parts = label.split("=", 2);
            if (parts.length >= 2) {
                String rhs = parts[1].trim().replace(";", "");
                uses.addAll(extractVariablesFromExpression(rhs));
            }
        } else {
            // For other statements, extract all variables
            uses.addAll(extractVariablesFromExpression(label));
        }

        return uses;
    }

    // Extract variable names from an expression.
    private Set<String> extractVariablesFromExpression(String expr) {
        Set<String> vars = new HashSet<>();

        // Remove common operators and split
        String cleaned = expr.replaceAll("[+\\-*/()\\[\\]{}<>=!&|;,.]", " ");
        String[] tokens = cleaned.split("\\s+");

        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) continue;

            // Check if it's a valid identifier (not a number, not a keyword)
            if (isValidIdentifier(token) && !isKeyword(token)) {
                vars.add(token);
            }
        }

        return vars;
    }

    private boolean isValidIdentifier(String s) {
        if (s == null || s.isEmpty()) return false;

        // Check if it's a number
        try {
            Integer.parseInt(s);
            return false;
        } catch (NumberFormatException e) {
            // Not a number, continue
        }

        if (!Character.isJavaIdentifierStart(s.charAt(0))) return false;
        for (int i = 1; i < s.length(); i++) {
            if (!Character.isJavaIdentifierPart(s.charAt(i))) return false;
        }
        return true;
    }

    private boolean isKeyword(String s) {
        Set<String> keywords = new HashSet<>(Arrays.asList(
            "if", "else", "while", "for", "return", "int", "boolean", "void", "class",
            "public", "private", "static", "new", "this", "true", "false", "null",
            "break", "continue", "switch", "case", "default", "try", "catch", "finally",
            "throw", "throws", "extends", "implements", "abstract", "final", "native",
            "synchronized", "transient", "volatile", "strictfp", "package", "import"
        ));
        return keywords.contains(s);
    }

    // Compute reaching definitions using fixed-point analysis.
    private Map<Node, Set<Definition>> computeReachingDefinitions(Map<Node, Set<String>> defs) {
        Map<Node, Set<Definition>> reachingDefs = new HashMap<>();

        // Initialize
        for (Node node : cfg.nodes()) {
            reachingDefs.put(node, new HashSet<>());
        }

        // Fixed-point iteration
        boolean changed = true;
        while (changed) {
            changed = false;

            for (Node node : cfg.nodes()) {
                Set<Definition> newReaching = new HashSet<>();

                // Union of reaching definitions from all predecessors
                Set<Edge> inEdges = node.inEdges();
                for (Edge edge : inEdges) {
                    Node pred = edge.source();
                    Set<Definition> predReaching = new HashSet<>(reachingDefs.get(pred));

                    // Kill definitions of variables defined in pred
                    Set<String> predDefs = defs.get(pred);
                    predReaching.removeIf(def -> predDefs.contains(def.variable));

                    // Gen: Add new definitions from pred
                    for (String var : predDefs) {
                        predReaching.add(new Definition(pred, var));
                    }

                    newReaching.addAll(predReaching);
                }

                if (!newReaching.equals(reachingDefs.get(node))) {
                    reachingDefs.put(node, newReaching);
                    changed = true;
                }
            }
        }

        return reachingDefs;
    }

     // Add control and data dependence edges to PDG.
    private void addPDGEdges() {
        // Add control dependence edges
        for (Node from : controlDependence.keySet()) {
            PDGNode fromPDG = cfgToPdgMap.get(from);
            for (Node to : controlDependence.get(from)) {
                PDGNode toPDG = cfgToPdgMap.get(to);
                PDGEdge edge = new PDGEdge(PDGEdge.EdgeType.CONTROL);
                try {
                    pdg.addEdge(fromPDG, toPDG, edge);
                } catch (IllegalArgumentException e) {
                    // Edge might already exist
                }
            }
        }

        // Add data dependence edges
        for (Node from : dataDependence.keySet()) {
            PDGNode fromPDG = cfgToPdgMap.get(from);
            for (Node to : dataDependence.get(from)) {
                PDGNode toPDG = cfgToPdgMap.get(to);
                PDGEdge edge = new PDGEdge(PDGEdge.EdgeType.DATA);
                try {
                    pdg.addEdge(fromPDG, toPDG, edge);
                } catch (IllegalArgumentException e) {
                    // Edge might already exist
                }
            }
        }
    }

    /**
     * Compute forward slice from a given node label.
     * Returns all nodes that could be impacted by a change to the given node.
     **/
    public Set<String> computeForwardSlice(String nodeLabel) {
        Node startCFGNode = cfg.findNode(nodeLabel); // Find the CFG node with this label
        if (startCFGNode == null) {
            return Collections.emptySet();
        }
        return computeForwardSliceFromNode(startCFGNode);
    }

    // Compute forward slice from a given node.
    private Set<String> computeForwardSliceFromNode(Node startNode) {
        PDGNode startPDGNode = cfgToPdgMap.get(startNode);
        Set<String> impactedLabels = new HashSet<>();
        Set<PDGNode> visited = new HashSet<>();
        Queue<PDGNode> queue = new LinkedList<>();

        queue.add(startPDGNode);
        visited.add(startPDGNode);

        while (!queue.isEmpty()) {
            PDGNode current = queue.poll();
            impactedLabels.add(current.getCfgNode().label());

            // Follow all outgoing edges (both control and data)
            Set<PDGEdge> outEdges = pdg.outgoingEdgesOf(current);
            for (PDGEdge edge : outEdges) {
                PDGNode target = pdg.getEdgeTarget(edge);
                if (!visited.contains(target)) {
                    visited.add(target);
                    queue.add(target);
                }
            }
        }

        return impactedLabels;
    }

    // Print the PDG structure.
    public void printPDG() {
        System.out.println("\n=== PDG Structure ===");

        List<PDGNode> nodes = new ArrayList<>(pdg.vertexSet());
        nodes.sort(Comparator.comparing(n -> n.getCfgNode().label()));

        for (PDGNode node : nodes) {
            System.out.print("Node: " + node.getCfgNode().label());
            System.out.print(" [Defs: " + node.getDefs() + ", Uses: " + node.getUses() + "]");
            System.out.println();

            Set<PDGEdge> outEdges = pdg.outgoingEdgesOf(node);
            for (PDGEdge edge : outEdges) {
                PDGNode target = pdg.getEdgeTarget(edge);
                System.out.println("  -> " + edge.getType() + " dep on: " + target.getCfgNode().label());
            }
        }
    }

    // Print CFG structure for debugging.
    public void printCFG() {
        System.out.println("\n=== CFG Structure ===");
        System.out.println("Entry: " + cfg.entry.label());
        System.out.println("Normal Exit: " + cfg.normalExit.label());
        System.out.println("Abrupt Exit: " + cfg.abruptExit.label());
        System.out.println("\nNodes:");

        for (Node node : cfg.nodes()) {
            System.out.println("  " + node.label());
            for (Edge edge : node.outEdges()) {
                String target = edge.target() != null ? edge.target().label() : "null";
                System.out.println("    -> " + edge.label() + " to " + target);
            }
        }
    }

    // Get the PDG graph.
    public Graph<PDGNode, PDGEdge> getGraph() {
        return pdg;
    }

    // Get the underlying CFG.
    public ControlFlowGraph getCFG() {
        return cfg;
    }

    //Helper class for tracking definitions
    private static class Definition {
        Node node;
        String variable;

        Definition(Node node, String variable) {
            this.node = node;
            this.variable = variable;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Definition)) return false;
            Definition that = (Definition) o;
            return node.equals(that.node) && variable.equals(that.variable);
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, variable);
        }
    }
}
