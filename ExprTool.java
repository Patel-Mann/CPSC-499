import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.*;

public class ExprTool {
    public static void main(String[] args) throws Exception {
        String inputText;
        String sourceName = "<stdin>";

        if (args.length > 0) {
            try {
                inputText = new String(Files.readAllBytes(Paths.get(args[0])));
                sourceName = args[0];  // set source name to file path
            } catch (IOException e) {
                System.err.println("Error reading file: " + args[0]);
                return;
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            StringBuilder inputBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                inputBuilder.append(scanner.nextLine()).append("\n");
            }
            inputText = inputBuilder.toString();
            scanner.close();
        }

        // Create char stream with source name
        CharStream input = CharStreams.fromString(inputText, sourceName);

        // Create lexer
        ExprLexer lexer = new ExprLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ThrowingErrorListener());

        // Create token stream
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create parser
        ExprSyntactic parser = new ExprSyntactic(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ThrowingErrorListener());

        try {
            // Use compilationUnit as the start rule
            ParseTree tree = parser.compilationUnit();

            // Walk the tree and print class declarations
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new ClassPrinter(sourceName), tree);

        } catch (RuntimeException e) {
            System.err.println("Parsing failed: " + e.getMessage());
        }
    }

    // Listener to print class declarations with line/column
    public static class ClassPrinter extends ExprSyntacticBaseListener {
        private final String sourceName;

        public ClassPrinter(String sourceName) {
            this.sourceName = sourceName;
        }

        @Override
        public void enterClassDeclaration(ExprSyntactic.ClassDeclarationContext ctx) {
            int line = ctx.getStart().getLine();
            int col = ctx.getStart().getCharPositionInLine();
            // Get the class name from the identifier rule
            String className = ctx.identifier().getText();
            System.out.printf(" file %s, class %s at line %d, column %d%n",
                              sourceName, className, line, col);
        }
    }

    // Custom error listener for cleaner error handling
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