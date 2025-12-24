import org.lsmr.cfg.*;
import pdg.PDG;
import java.io.*;
import java.util.*;

/**
 * Change Impact Analysis (CIA) Tool
 *
 * This tool performs program dependence graph (PDG) analysis on Java source files
 * to identify which lines of code are impacted by changes to a specific line.
 *
 * The analysis works by:
 * 1. Building a Control Flow Graph (CFG) from the source code
 * 2. Constructing a Program Dependence Graph (PDG) with control and data dependencies
 * 3. Computing forward slices to determine impact
 *
 * Usage: java CIA <java-file> [line-number]
 */
public class CIA {
  /**
   * Main entry point for the Change Impact Analysis tool.
   * Validates arguments and initiates the PDG analysis.
   *
   * @param args Command line arguments: [0] = filename, [1] = optional line number
   */
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

  /**
   * Performs PDG analysis on the specified Java file.
   *
   * This method orchestrates the complete analysis workflow:
   * 1. Builds the Control Flow Graph (CFG)
   * 2. Constructs the Program Dependence Graph (PDG)
   * 3. Either displays all dependencies or computes impact for a specific line
   *
   * @param filename Path to the Java source file to analyze
   * @param targetLine Optional line number to analyze for change impact (null for all dependencies)
   * @throws IOException If file cannot be read or parsed
   */
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

    /**
    * Computes and displays the change impact for a specific line of code.
    *
    * Change impact is determined by computing a forward slice from the target line,
    * which identifies all statements that could be affected by modifying the target.
    * This includes both direct dependencies (data flow) and indirect dependencies
    * (control flow).
    *
    * @param cfg The Control Flow Graph of the program
    * @param pdg The Program Dependence Graph containing dependency information
    * @param cfgBuilder The CFG builder for line number mapping
    * @param targetLine The line number to analyze for change impact
    */
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

        // Compute forward slice: all nodes reachable via dependencies
        // The forward slice represents all statements that could be affected
        // by changes to the target line
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
