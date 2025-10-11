import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;

import static com.github.javaparser.ParserConfiguration.LanguageLevel.JAVA_8;

public class LCA_JP3_27_0 {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java LCA_JP3_27_0 <JavaSourceFile>");
            return;
        }

        // Configure parser for Java 8
        ParserConfiguration config = new ParserConfiguration();
        config.setLanguageLevel(JAVA_8);
        JavaParser parser = new JavaParser(config);

        // Parse the file
        FileInputStream in = new FileInputStream(new File(args[0]));
        CompilationUnit cu = parser.parse(in).getResult().get();
        in.close();

        // Visit and find local classes
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
            System.out.println("Local class: " + n.getNameAsString());
            super.visit(n, arg);
        }
    }
}