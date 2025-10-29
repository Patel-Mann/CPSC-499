package CFGGraph;
import java.util.ArrayList;

import japa.parser.ast.stmt.Statement;

public class CFGNode {

    static int id_counter = 1;
    private final int ID;
    private String label;
    private ArrayList<Statement> statements;


    public CFGNode(String label) {
        this.label = label;
        this.ID = id_counter++;
        this.statements = new ArrayList<>();
    }

    public int getID() {
        return this.ID;
    }

    public String getLabel() {
        return this.label;
    }

    public ArrayList<Statement> getStatements() {
        return this.statements;
    }

    public void addStatement(Statement s) {
        statements.add(s);
    }

    @Override
    public String toString() {
        return label;
    }

}