import org.lsmr.cfg.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.*;

public class CFGBuilder {

    private Map<Node, Integer> nodeToLine = new HashMap<>();

    /**
     * Build CFG from a Java file
     */
    public ControlFlowGraph buildFromFile(String filename) throws IOException {
        System.out.println("\n\tBuilding CFG from " + filename);

        // Parse the file
        CharStream input = CharStreams.fromFileName(filename);
        Java1_4Lexer lexer = new Java1_4Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java1_4Parser parser = new Java1_4Parser(tokens);

        parser.removeErrorListeners();

        ParseTree tree = parser.compilationUnit();

        // build CFG using professor's NodeBuilder
        NodeBuilder builder = new NodeBuilder();
        builder.visit(tree);

        List<ControlFlowGraph> cfgs = builder.getCFGs();

        if (cfgs.isEmpty()) {
            throw new RuntimeException("No methods found in file");
        }

        if (cfgs.size() > 1) {
            System.out.println("Note: File has " + cfgs.size() + " methods, using first method");
        }

        ControlFlowGraph cfg = cfgs.get(0);

        System.out.println("CFG built: " + cfg.nodes().size() + " nodes, " + cfg.edges().size() + " edges");

        return cfg;
    }

    public int getLineNumber(Node node) {
        return node.getLineNumber();
    }

    public List<Integer> getAllLineNumbers() {
        Set<Integer> lines = new TreeSet<>();
        for (Integer line : nodeToLine.values()) {
            if (line > 0)
                lines.add(line);
        }
        return new ArrayList<>(lines);
    }

    public List<Node> findNodesAtLine(ControlFlowGraph cfg, int lineNumber) {
        List<Node> result = new ArrayList<>();
        for (Node node : cfg.nodes()) {
            if (getLineNumber(node) == lineNumber) {
                result.add(node);
            }
        }
        return result;
    }

    public void printLineMapping(ControlFlowGraph cfg) {
        System.out.println("\n Line Number Mapping");
        Map<Integer, List<String>> lineToNodes = new TreeMap<>();

        for (Node node : cfg.nodes()) {
            int line = getLineNumber(node);
            if (line > 0) {
                lineToNodes.putIfAbsent(line, new ArrayList<>());
                lineToNodes.get(line).add(node.label());
            }
        }

        for (Map.Entry<Integer, List<String>> entry : lineToNodes.entrySet()) {
            System.out.println("Line " + entry.getKey() + ":");
            for (String label : entry.getValue()) {
                System.out.println("  " + label);
            }
        }
        System.out.println();
    }
}
