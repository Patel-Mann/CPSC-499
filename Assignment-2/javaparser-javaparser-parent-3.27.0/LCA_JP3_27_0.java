import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ParserConfiguration.LanguageLevel;
import com.github.javaparser.ast.CompilationUnit;

public class LCA_JP3_27_0 {
    public static void main(String[] args) throws Exception{
        if (args.length == 0) {
            System.out.println("Usage: java LCA_JP3_27_0 <JavaSourceFile>");
            return;
        }

      
        ParserConfiguration config = new ParserConfiguration()
            .setLanguageLevel(LanguageLevel.JAVA_9);

        JavaParser parser = new JavaParser(config);

        String code = "module x {}";
        parser.parse(code).getResult().ifPresent(System.out::println);
    }
}
