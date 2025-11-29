
/**
 * PDG Node that wraps a CFG node (org.lsmr.cfg.Node) and adds def-use information.
 */
 package pdg;

import org.lsmr.cfg.Node;
import java.util.*;


public class PDGNode {
    private Node cfgNode;
    private Set<String> defs;
    private Set<String> uses;

    public PDGNode(Node cfgNode) {
        this.cfgNode = cfgNode;
        this.defs = new HashSet<>();
        this.uses = new HashSet<>();
    }

    public Node getCfgNode() {
        return cfgNode;
    }

    public Set<String> getDefs() {
        return defs;
    }

    public void setDefs(Set<String> defs) {
        this.defs = defs;
    }

    public Set<String> getUses() {
        return uses;
    }

    public void setUses(Set<String> uses) {
        this.uses = uses;
    }

    @Override
    public String toString() {
        return "PDGNode{" + cfgNode.label() +
               ", defs=" + defs + ", uses=" + uses + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PDGNode)) return false;
        PDGNode pdgNode = (PDGNode) o;
        return cfgNode.equals(pdgNode.cfgNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cfgNode);
    }
}
