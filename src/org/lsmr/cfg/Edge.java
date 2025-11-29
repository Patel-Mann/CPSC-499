package org.lsmr.cfg;

import java.util.Set;

/**
 * Represents an edge in a graph. During construction of a graph, it can be that
 * edges are not fully initialized on a first pass.
 */
public class Edge {

	/**
	 * The legal labels to use on edges.
	 */
	public static enum EdgeLabel {
		/**
		 * Represents a default edge without no label to test for branching.
		 */
		BLANK,
		/**
		 * Represents a true branch.
		 */
		TRUE,
		/**
		 * Represents a false branch.
		 */
		FALSE,
		/**
		 * Represents a branch taken when an exception is thrown.
		 */
		THROWN,
		/**
		 * Represents a branch taken when an exception is thrown but delayed for completion in a finally block.
		 */
		THROWN_DELAYED,
		/**
		 * Represents a branch taken when an exception is definitively caught.
		 */
		CAUGHT,
		/**
		 * Represents a branch taken when the corresponding extended label is matched.
		 */
		CASE;

		public String toString() {
			switch(this) {
			case BLANK:
				return "";

			default:
				return super.toString();
			}
		}
	}

	private Node source;
	Node target;
	private EdgeLabel label;
	private String extendedLabel;
	private ControlFlowGraph graph;

	Edge(Node source, Node target, EdgeLabel label) {
		this(source, target, label, "");
	}

	Edge(Node source, Node target, EdgeLabel label, String extendedLabel) {
		if(source == null || label == null)
			throw new IllegalArgumentException();

		this.source = source;
		this.target = target;
		this.label = label;
		this.extendedLabel = extendedLabel;
	}

	/**
	 * Represents the source node for this edge.
	 * 
	 * @return The source node. Should not be null.
	 */
	public Node source() {
		return source;
	}

	/**
	 * Represents the target node for this edge.
	 * 
	 * @return The target node. May be null if it has not been fully initialized.
	 */
	public Node target() {
		return target;
	}

	/**
	 * Represents the kind of edge represented: BLANK if default.
	 * 
	 * @return The label of this edge. Should not be null.
	 */
	public EdgeLabel label() {
		return label;
	}

	/**
	 * Represents the extended label for edges whose labels cannot be purely
	 * represented via an enum constant.
	 * 
	 * @return The extended label of this edge. Should not be null; may be empty.
	 */
	public String extendedLabel() {
		return extendedLabel;
	}

	/**
	 * Represents the graph in which this edge occurs.
	 * 
	 * @return The graph in which this edge occurs. Should not be null unless the
	 *             edge is not fully initialized.
	 */
	public ControlFlowGraph graph() {
		return graph;
	}

	void setTarget(Node target) {
		if(target == null || target.graph() != graph)
			throw new IllegalArgumentException();

		this.target = target;

		if(target != null)
			target.addInEdge(this);
	}

	void setGraph(ControlFlowGraph graph) {
		if(this.graph != null) {
			if(graph == null)
				this.graph = null;
			else
				throw new IllegalStateException("Non-null graph cannot be swapped to another.  Erase it first");
		}
		else if(graph == null)
			throw new IllegalStateException("Null graph should not be set to null again");
		else
			this.graph = graph;

		if(!graph.containsNode(source))
			throw new IllegalStateException("Edge source not member of graph");

		if(target != null && !graph.containsNode(target))
			throw new IllegalStateException("Edge target not member of graph");
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 31 * result + source.hashCode();
		result = 31 * result + (target != null ? target.hashCode() : 0);

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Edge) {
			Edge other = (Edge)obj;

			if(label != null)
				return label.equals(other.label);

			if(source.equals(other.source)) {
				if(target == null) {
					if(other.target == null)
						return true;
				}
				else
					return target.equals(other.target);
			}
		}

		return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("edge");

		String ls = label.toString();

		if(ls != "") {
			sb.append(" ");
			sb.append(ls);

			if(extendedLabel != null) {
				sb.append(" ");
				sb.append(extendedLabel);
			}
		}

		sb.append(": (");
		sb.append(source.label());
		sb.append(", ");
		sb.append(target == null ? "null" : target.label());
		sb.append(")");

		return sb.toString();
	}
}
