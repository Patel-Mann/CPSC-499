package CFGGraph;
import org.jgrapht.graph.DefaultEdge;

public class CFGEdge extends DefaultEdge {
    //path defines if its a True or False or Null (when either T or F resulkt on the same node)
    private boolean path;
    private CFGNode from;
    private CFGNode to;

    public CFGEdge() {
        this(null, null);
    }
    public CFGEdge(CFGNode from, CFGNode to) {
        this.from = from;
        this.to = to;
    }

    public void setPath(boolean path) { this.path = path; }
}