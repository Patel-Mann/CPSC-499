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

        FileInputStream in = new FileInputStream(new File(args[0]));
        CompilationUnit cu = JavaParser.parse(in);
        in.close();

        LocalClassVisitor visitor = new LocalClassVisitor();
        visitor.visit(cu, null);
    }
    
    static class LocalClassVisitor extends VoidVisitorAdapter<Void> {
        private boolean insideMethod = false;
        
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            insideMethod = true;
            super.visit(n, arg);
            insideMethod = false;
        }
        
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            System.out.println("Local class: " + n.getName());
            super.visit(n, arg);
        }
    }
}