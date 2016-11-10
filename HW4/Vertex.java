/*  cmf927@uw.edu, Changming Feng
 *  hsiaop@uw.edu, Preston Hsiao
 *  Programming Assignment #4: Graphs and Shortest Paths CSE373, Autumn 2015
 *  
 * Representation of a graph vertex
 */

import java.awt.List;
import java.util.ArrayList;

public class Vertex {
	private final String label;   // label attached to this vertex
	// the Vertex that points to this vertex in the shortest path
	public Vertex path;
	// the lowest cost from the given start vertex to this one
	public int cost;
	/**
	 * Construct a new vertex
	 * @param label the label attached to this vertex
	 */
	public Vertex(String label) {
		if(label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
	}

	/**
	 * Get a vertex label
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * A string representation of this object
	 * @return the label attached to this vertex
	 */
	public String toString() {
		return label;
	}

	//auto-generated: hashes on label
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	//auto-generated: compares labels
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
                    return other.label == null;
		} else {
		    return label.equals(other.label);
		}
	}


}
