import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.*;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.File;

public class LCA_JP1_0_0 {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java LCA_JP1_0_0 <JavaSourceFile>");
            return;
        }

        // Parse the given Java file
        FileInputStream in = new FileInputStream(new File(args[0]));
        CompilationUnit cu = JavaParser.parse(in);

        // Traverse the AST and print local classes
        new VoidVisitorAdapter<Void>() {
            // boolean insideMethod = false;
            
            //
            //resource <- 

            @Override
            public void visit(MethodDeclaration n, Void arg) {
                super.visit(n, arg);
            }

            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                if (true) {
                    System.out.println("Found local class: " + n.getName());
                }
                super.visit(n, arg);
            }
        }.visit(cu, null);
    }
}
