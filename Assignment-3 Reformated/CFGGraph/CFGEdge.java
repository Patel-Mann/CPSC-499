package CFGGraph;
import org.jgrapht.graph.DefaultEdge;

public class CFGEdge extends DefaultEdge {
    //path defines if its a True or False or Null (when either T or F resulkt on the same node)
    private boolean path;
    private CFGNode from;
    private CFGNode to;
    private String label;

    public CFGEdge() {
        this(null, null);
    }
    public CFGEdge(CFGNode from, CFGNode to) {
        this.from = from;
        this.to = to;
        this.label = "";
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public CFGNode getFrom() {
        return this.from;
    }

    public CFGNode getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return label;
    }

    public void setPath(boolean path) { this.path = path; }
}