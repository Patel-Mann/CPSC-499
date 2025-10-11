import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class ExprTool {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            // If no files given then read from stdin
            Scanner scanner = new Scanner(System.in);
            StringBuilder inputBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                inputBuilder.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            parseAndWalk(inputBuilder.toString(), "<stdin>");
        } else {
            // for Loop through all given file paths 
            for (String filePath : args) {
                try {
                    String inputText = new String(Files.readAllBytes(Paths.get(filePath)));
                    parseAndWalk(inputText, filePath);
                } catch (IOException e) {
                    System.err.println("Error reading file: " + filePath);
                }
            }
        }
    }
    private static void parseAndWalk(String inputText, String sourceName) {
        try {
            // Create char stream
            CharStream input = CharStreams.fromString(inputText, sourceName);
            Java8Lexer lexer = new Java8Lexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new ThrowingErrorListener());

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Java8Parser parser = new Java8Parser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new ThrowingErrorListener());

            ParseTree tree = parser.compilationUnit();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new ClassPrinter(sourceName), tree);

        } catch (RuntimeException e) {
            System.err.println("Parsing File Failed: " + e.getMessage());
        }
    }

    public static class ClassPrinter extends Java8ParserBaseListener {
        private final String sourceName;

        public ClassPrinter(String sourceName) {
            this.sourceName = sourceName;
        }

        @Override
        public void enterClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
            int line = ctx.getStart().getLine();
            int col = ctx.getStart().getCharPositionInLine();
            String class_name = "";

            if(ctx.normalClassDeclaration() != null){
                class_name = ctx.normalClassDeclaration().Identifier().getText();
            }else if(ctx.enumDeclaration() != null){
                class_name = ctx.normalClassDeclaration().Identifier().getText();
            }

            //String className = ctx.identifier().getText();
            System.out.printf("Class %s, file %s, line %d, column %d%n",
                              class_name, sourceName, line, col);
        }
    }

    // Custom error listener
    public static class ThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                                Object offendingSymbol,
                                int line, int charPositionInLine,
                                String msg,
                                RecognitionException e) {
            String sourceName = recognizer.getInputStream().getSourceName();
            if (sourceName == null || sourceName.isEmpty()) {
                sourceName = "<unknown>";
            }

            String formatted = String.format(
                " file %s, line %d, column %d, error-msg: %s",
                sourceName, line, charPositionInLine, msg
            );

            throw new ParseCancellationException(formatted);
        }
    }
}
