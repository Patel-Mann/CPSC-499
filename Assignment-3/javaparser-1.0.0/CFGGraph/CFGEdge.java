package CFGGraph;
import org.jgrapht.graph.DefaultEdge;

public class CFGEdge extends DefaultEdge {
    //path defines if its a True or False or Null (when either T or F resulkt on the same node)
    private boolean path;

    public CFGEdge(boolean path) {
        this.path = path;
    }


}