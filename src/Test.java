import org.lsmr.cfg.ControlFlowGraph;
import org.lsmr.cfg.Node;
import org.lsmr.cfg.Edge;
import pdg.PDG;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        System.out.println("=== PDG Test ===\n");

        // Create a simple CFG manually
        ControlFlowGraph cfg = new ControlFlowGraph("testMethod");

        // Create nodes
        Node n1 = cfg.buildNode("int x = 5");
        Node n2 = cfg.buildNode("int y = x + 10");
        Node n3 = cfg.buildNode("int z = y * 2");
        Node n4 = cfg.buildNode("System.out.println(z)");

        // Connect nodes: entry -> n1 -> n2 -> n3 -> n4 -> exit
        cfg.buildEdge(cfg.entry, n1, Edge.EdgeLabel.BLANK);
        cfg.buildEdge(n1, n2, Edge.EdgeLabel.BLANK);
        cfg.buildEdge(n2, n3, Edge.EdgeLabel.BLANK);
        cfg.buildEdge(n3, n4, Edge.EdgeLabel.BLANK);
        cfg.buildEdge(n4, cfg.normalExit, Edge.EdgeLabel.BLANK);

        System.out.println("CFG created with " + cfg.nodes().size() + " nodes");

        // Create PDG from CFG
        PDG pdg = new PDG(cfg);

        // Print CFG structure
        pdg.printCFG();

        // Print PDG structure
        pdg.printPDG();

        // Test impact analysis
        System.out.println("\n=== Impact Analysis ===");

        String[] testNodes = {"int x = 5", "int y = x + 10", "int z = y * 2"};

        for (String nodeLabel : testNodes) {
            Set<String> impacted = pdg.computeForwardSlice(nodeLabel);
            System.out.println("\nChanging '" + nodeLabel + "' impacts:");
            for (String label : impacted) {
                if (!label.startsWith("*")) {  // Skip special nodes
                    System.out.println("  - " + label);
                }
            }
        }

        System.out.println("\n=== Test Complete ===");
    }
}
