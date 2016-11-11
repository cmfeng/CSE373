
/*  cmf927@uw.edu, Changming Feng
 *  hsiaop@uw.edu, Preston Hsiao
 *  Programming Assignment #4: Graphs and Shortest Paths CSE373, Autumn 2015
 *  
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */

import java.util.*;

public class MyGraph implements Graph {
	// Map storing the graph from Vertex to all of its edges
	private final Map<Vertex, Set<Edge>> m;

	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges. throws an IllegalArguementException if the
	 * edge weight is negative, if the vertex source or destination is not in
	 * our list of vertices, or if there are edges that have the same sources
	 * and destinations, but different weights.
	 * 
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		m = new HashMap<Vertex, Set<Edge>>();
		for (Vertex vv : v) {
			if (!containsVertex(vv)) {
				m.put(new Vertex(vv.getLabel()), new HashSet<Edge>());
			}
		}

		for (Edge ee : e) {
			if (ee.getWeight() < 0) {
				throw new IllegalArgumentException();
			}

			if (!containsVertex(ee.getSource()) || !containsVertex(ee.getDestination())) {
				throw new IllegalArgumentException();
			}

			for (Edge eee : m.get(ee.getSource())) {
				if (eee.getDestination().equals(ee.getDestination()) && eee.getWeight() != ee.getWeight()) {
					throw new IllegalArgumentException();
				}
			}

			m.get(ee.getSource())
					.add(new Edge(existVertex(ee.getSource()), existVertex(ee.getDestination()), ee.getWeight()));
		}
	}

	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	public Collection<Vertex> vertices() {

		Set<Vertex> s = new HashSet<Vertex>();

		for (Vertex v : m.keySet()) {
			s.add(new Vertex(v.getLabel()));
		}

		return s;
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	public Collection<Edge> edges() {

		Set<Edge> s = new HashSet<Edge>();

		for (Vertex v : m.keySet()) {
			for (Edge e : m.get(v)) {
				s.add(new Edge(new Vertex(e.getSource().getLabel()), new Vertex(e.getDestination().getLabel()),
						e.getWeight()));
			}
		}

		return s;
	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v
	 *            one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException
	 *             if v does not exist.
	 */
	public Collection<Vertex> adjacentVertices(Vertex v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException();
		}

		Set<Vertex> s = new HashSet<Vertex>();

		for (Edge e : m.get(existVertex(v))) {
			s.add(new Vertex(e.getDestination().getLabel()));
		}

		return s;
	}

	/**
	 * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
	 * graph. Assumes that we do not have negative cost edges in the graph.
	 * 
	 * @param a
	 *            one vertex
	 * @param b
	 *            another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException
	 *             if a or b do not exist.
	 */
	public int edgeCost(Vertex a, Vertex b) {
		if (!containsVertex(a) || !containsVertex(b)) {
			throw new IllegalArgumentException();
		}

		for (Edge e : m.get(existVertex(a))) {
			if (e.getDestination().equals(existVertex(b))) {
				return e.getWeight();
			}
		}

		return -1;
	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a
	 *            the starting vertex
	 * @param b
	 *            the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	public Path shortestPath(Vertex a, Vertex b) {
		if (!containsVertex(a) || !containsVertex(b)) {
			throw new IllegalArgumentException();
		}
		
		// if a and b are the same vertex, returns a Path containing just one vertex and a cost of 0
		if (a.equals(b)) {
			ArrayList<Vertex> list = new ArrayList<Vertex>();
			list.add(a);
			return new Path(list, 0);
		}

		for (Vertex v : m.keySet()) {
			v.cost = Integer.MAX_VALUE;
			v.path = null;
		}

		Vertex start = existVertex(a);
		start.cost = 0;

		Set<Vertex> unknown = new HashSet<Vertex>();
		// adds each vertex to the list of unknown vertices
		for (Vertex v : m.keySet()) {
			unknown.add(v);
		}

		Path p = shortestPath(start, start, existVertex(b), new HashSet<Vertex>(), unknown);
		
		return p;
	}

	// private pair method that finds and returns the shortest path from a to b
	private Path shortestPath(Vertex start, Vertex current, Vertex end, Set<Vertex> known, Set<Vertex> unknown) {
		//base case: when all vertices are known or the current vertex is null
		if (unknown.isEmpty() || current == null) {
			Stack<Vertex> s = new Stack<Vertex>();
			int endCost = end.cost;
			Vertex finish = end;

			while (end.path != null && !end.path.equals(start)) {
				s.push(end.path);
				end = end.path;
			}

			if (end.path == null) {
				return null;
			}

			ArrayList<Vertex> list = new ArrayList<Vertex>();

			list.add(new Vertex(start.getLabel()));
			while (!s.isEmpty()) {
				list.add(new Vertex(s.pop().getLabel()));
			}
			list.add(new Vertex(finish.getLabel()));

			return new Path(list, endCost);
		}

		known.add(current);
		unknown.remove(current);

		int lowestCost = Integer.MAX_VALUE;
		Vertex lowestVertex = null;
		
		// updates the vertices with shortest path and lowest cost
		for (Edge e : m.get(current)) {
			int c1 = current.cost + e.getWeight();
			int c2 = e.getDestination().cost;

			if (c1 < c2) {
				e.getDestination().cost = c1;
				e.getDestination().path = current;
			}
		}
		
		// finds the next unknown vertex with the lowest cost
		for (Vertex v : unknown) {
			if (v.cost < lowestCost) {
				lowestCost = v.cost;
				lowestVertex = v;
			}
		}

		return shortestPath(start, lowestVertex, end, known, unknown);

	}

	// private helper method that returns whether or not a vertex is in the list of vertices
	private boolean containsVertex(Vertex v) {
		for (Vertex vv : m.keySet()) {
			if (vv.equals(v)) {
				return true;
			}
		}

		return false;
	}

	// private helper method that returns the vertex in the list of vertices that corresponds with the given vertex v
	private Vertex existVertex(Vertex v) {
		for (Vertex vv : m.keySet()) {
			if (vv.equals(v)) {
				return vv;
			}
		}

		return null;
	}

}
