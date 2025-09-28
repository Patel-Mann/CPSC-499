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

    // Reusable parsing + walking
    private static void parseAndWalk(String inputText, String sourceName) {
        try {
            // Create char stream
            CharStream input = CharStreams.fromString(inputText, sourceName);

            // Lexer
            ExprLexer lexer = new ExprLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new ThrowingErrorListener());

            // Tokens
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Parser
            ExprParser parser = new ExprParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new ThrowingErrorListener());

            // Parse compilation unit
            ParseTree tree = parser.compilationUnit();

            // Walk
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new ClassPrinter(sourceName), tree);

        } catch (RuntimeException e) {
            System.err.println("Parsing failed: " + e.getMessage());
        }
    }

    // Listener to print class declarations with line/column
    public static class ClassPrinter extends ExprParserBaseListener {
        private final String sourceName;

        public ClassPrinter(String sourceName) {
            this.sourceName = sourceName;
        }

        @Override
        public void enterClassDeclaration(ExprParser.ClassDeclarationContext ctx) {
            int line = ctx.getStart().getLine();
            int col = ctx.getStart().getCharPositionInLine();
            String className = ctx.identifier().getText();
            System.out.printf("Class %s, file %s, line %d, column %d%n",
                              className, sourceName, line, col);
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
                " file %s, line %d, column %d%n%s",
                sourceName, line, charPositionInLine, msg
            );

            throw new ParseCancellationException(formatted);
        }
    }
}
