/**
 * Edge in the PDG representing either control or data dependence.
 */
package pdg;

import org.jgrapht.graph.DefaultEdge;

public class PDGEdge extends DefaultEdge {

    public enum EdgeType {
        CONTROL,  // Control dependence
        DATA      // Data dependence
    }

    private EdgeType type;

    public PDGEdge(EdgeType type) {
        this.type = type;
    }

    public EdgeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
