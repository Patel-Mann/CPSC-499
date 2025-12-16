import org.lsmr.cfg.*;
import pdg.PDG;
import java.io.*;
import java.util.*;

/**
 * PDG Tool: Change Impact Analysis
 * Usage: java PDGTool <java-file> [line-number]
 */
public class CIA {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java PDGTool <java-file> [line-number]");
            System.exit(1);
        }

        String filename = args[0];
        Integer targetLine = args.length > 1 ? Integer.parseInt(args[1]) : null;

        try {
            analyzePDG(filename, targetLine);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void analyzePDG(String filename, Integer targetLine) throws IOException {
        System.out.println("PDG Analysis Tool");
        System.out.println("File: " + filename);
        if (targetLine != null) {
            System.out.println("Target line: " + targetLine);
        }

        // Step 1: Build CFG
        CFGBuilder cfgBuilder = new CFGBuilder();
        ControlFlowGraph cfg = cfgBuilder.buildFromFile(filename);

        // Step 2: Build PDG
        PDG pdg = new PDG(cfg);

        // Step 3: Show results
        System.out.println("\n=== Available Lines ===");

        List<Integer> lines = cfg.getAllLineNumbers();
        System.out.println("Lines with statements: " + lines);

        // If specific line requested, compute impact
        if (targetLine != null) {
            computeImpact(cfg, pdg, cfgBuilder, targetLine);
        } else {
            // Show all dependencies
            System.out.println("\n=== All Dependencies ===");
            pdg.printPDG();
            cfgBuilder.printLineMapping(cfg);
        }

    }

    private static void computeImpact(ControlFlowGraph cfg, PDG pdg,
            CFGBuilder cfgBuilder, int targetLine) {
        System.out.println("\n=== Impact Analysis for Line " + targetLine + " ===");

        // Find nodes at target line
        List<Node> nodesAtLine = cfgBuilder.findNodesAtLine(cfg, targetLine);

        if (nodesAtLine.isEmpty()) {
            System.out.println("WARNING: No executable statement at line " + targetLine);
            return;
        }

        System.out.println("\nStatement(s) at line " + targetLine + ":");
        for (Node node : nodesAtLine) {
            System.out.println("  " + node.label());
        }

        // Compute forward slice
        Set<String> allImpacted = new HashSet<>();
        for (Node node : nodesAtLine) {
            Set<String> impacted = pdg.computeForwardSlice(node.label());
            allImpacted.addAll(impacted);
        }

        // Convert to line numbers
        Set<Integer> impactedLines = new TreeSet<>();
        for (Node node : cfg.nodes()) {
            if (allImpacted.contains(node.label())) {
                int line = cfgBuilder.getLineNumber(node);
                if (line > 0) {
                    impactedLines.add(line);
                }
            }
        }

        System.out.println("\n=== IMPACTED LINES ===");
        if (impactedLines.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (int line : impactedLines) {
                System.out.println("  Line " + line);
            }
            System.out.println("\nTotal: " + impactedLines.size() + " lines impacted");
        }
    }
}
