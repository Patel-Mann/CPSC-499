import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.*;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

public class LCA_JP1_0_0 {

    public static void main(String[] args) throws Exception {
        // Checks if file is provided as command line argument
        if (args.length == 0) {
            System.out.println("Usage: java LCA_JP1_0_0 <JavaSourceFile>");
            return;
        }

        // Makes sure file exits and can be read
        File sourFile = new File(args[0]);
        if (!sourFile.exists() || !sourFile.isFile() || !sourFile.canRead()) {
            System.err.println("Error: File can't be read or it doesn't exist: " + args[0]);
            return;
        }

        try (FileInputStream in = new FileInputStream(sourFile))
        // Parse the given Java file
        {
            CompilationUnit cu;
            try {
                cu = JavaParser.parse(in);
            } catch (Exception e) {
                System.err.println("Error: Failed to parse file");
                e.printStackTrace();
                return;
            }
            // Traverse the AST and print local classes
            new VoidVisitorAdapter<Void>() {

                @Override
                public void visit(MethodDeclaration n, Void arg) {
                    super.visit(n, arg);
                }

                @Override
                public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                    System.out.println("Found class: " + n.getName());
                    super.visit(n, arg);
                }
            }.visit(cu, null);
        } catch (IOException e) {
            System.err.println("Error: Unable to read the file.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Uneaxpected error occured.");
            e.printStackTrace();
        }
    }
}
