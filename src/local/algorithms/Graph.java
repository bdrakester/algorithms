package local.algorithms;

import java.util.HashMap;
import java.util.ArrayList;



/**
 * This class implements an undirected graph where each vertex is identified by an integer.
 * @author Brian Drake
 *
 */
public class Graph {
	private int numVertices;
	private int numEdges;
	private HashMap<Integer, ArrayList<Integer>> adjacencyList;
	private ArrayList<Edge> edges;
	
	/**
	 * This inner class implements an undirected edge
	 * @author Brian Drake
	 *
	 */
	private class Edge{
		int src;
		int dest;
		
		/**
		 * Constructor
		 * @param src
		 * @param dest
		 */
		Edge(int src, int dest) {
			this.src = src;
			this.dest = dest;
		}
		
		/**
		 * Overrides the equals method. Two edges are considered equal if it connects the same vertices, regardless of order.  
		 * That is edge with src = a and dest = b is considered equal and edge with src = b and dest = a. 
		 * @param o the edge being compared
		 * @return boolean true if the edges connect the same vertices
		 */
		@Override
		public boolean equals(Object o) {
			// If an object is compared with itself, return true
			if (o == this) {
				return true;
			}
			// If the  an instance of a different class, return false
			if ( !(o instanceof Edge) ) {
				return false;
			}
			
			Edge e = (Edge) o;
			return (src == e.src && dest == e.dest) || (src == e.dest && dest == e.src);	
		}
		
		/**
		 * Overrides the hashCode method.
		 * @return int the hashcode
		 */
		@Override
		public int hashCode() {
			int result = 17;
			result = 31 * result + src + dest;
			return result;
		}
	
	}
	
	/**
	 * Constructor
	 * Returns an empty graph. 
	 */
	public Graph() {
		adjacencyList = new HashMap<Integer, ArrayList<Integer>>();
		edges = new ArrayList<Edge>();
		numVertices = 0;
		numEdges = 0;
	}
	
	/**
	 * Copy constructor
	 * @param graph the graph to be copied
	 * Returns a deep copy of graph
	 */
	public Graph(Graph graph) {
		this.numVertices = graph.numVertices;
		this.numEdges = graph.numEdges;
		
		// Replicate the adjacency list
		this.adjacencyList = new HashMap<Integer, ArrayList<Integer>>();
		for(int vertex : graph.adjacencyList.keySet()) {
			this.adjacencyList.put(vertex, new ArrayList<Integer>(graph.adjacencyList.get(vertex)));
		}
		
		// Replicate the edges
		this.edges = new ArrayList<Edge>();
		for(Edge edge : graph.edges) {
			edges.add(new Edge(edge.src, edge.dest));
		}
	}
	
	
	/**
	 * Returns true if the graph contains the vertex 
	 * @param vertex the integer vertex
	 * @return Boolean true if the vertex exists in the graph
	 */
	public boolean containsVertex(int vertex) {
		if ( adjacencyList.containsKey(vertex) ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns true if the graph contains an edge between vertices src and dest.
	 * @param src
	 * @param dest
	 * @return boolean true if the edge exists
	 */
	public boolean containsEdge(int src, int dest) {
		if(edges.contains(new Edge(src, dest))) {
			return true;
		}
		else {
			return false;
		}	
		
	}
	
	/**
	 * Returns the number of vertices
	 * @return
	 */
	public int getNumVertices() {
		return numVertices;
	}
	
	/**
	 * Returns the number of edges in the graph.
	 * @return Integer
	 */
	public int getNumEdges() {
		return numEdges;
	}
	
	/**
	 * Add a vertex to the graph, returns true if successful.
	 * @param vertex
	 * @return Boolean
	 */
	public boolean addVertex(int vertex) {
		// If the vertex already exists, return false
		if( containsVertex(vertex) ) {
			return false;
		}
		
		// Add the vertex with an empty list of adjancecies
		adjacencyList.put(vertex,new ArrayList<Integer>());
		numVertices++;
		return true;	
	}
	
	/**
	 * Add an edge to the graph, creates vertices if they don't exist already.
	 * @param src Integer
	 * @param dest Integer
	 * @return Boolean true if edge was added to the graph
	 */
	public boolean addEdge(int src, int dest) {
		// If either vertex does not exist, create them
		if ( !containsVertex(src) ) {
			addVertex(src);
		}
		if ( !containsVertex(dest) ) {
			addVertex(dest);
		}
		
		// Add the vertices to the adjacency lists
		adjacencyList.get(src).add(dest);
		adjacencyList.get(dest).add(src);
		
		
		// Add to the edges list 
		//int[] vertices = {src,dest};
		//edges.add(vertices);
		edges.add(new Edge(src,dest));
		numEdges++;
		
		return true;
	}
	
	/**
	 * Removes a single edge from the graph.
	 * @param src the source vertex of the edge
	 * @param dest the destination vertex of the edge
	 * @return Boolean true if the the edge was removed
	 */
	public boolean removeEdge(int src, int dest) {
		// If the edge does not exist
		if (!(containsEdge(src,dest))) {
			return false;
		}
		
		// Remove from the adjacency lists
		adjacencyList.get(src).remove(Integer.valueOf(dest));
		adjacencyList.get(dest).remove(Integer.valueOf(src));
		
		// Remove from the edges list
		edges.remove(new Edge(src,dest));
		numEdges--;
		return true;
	}
	
	/**
	 * Removes a vertex, and any edges connected to it from the graph.
	 * @param vertex the vertex to be removed.
	 * @return true if the vertex was removed successfully.
	 */
	public boolean removeVertex(int vertex) {
		
		return false;
	}
	
	/**
	 * Updates and edge in the graph.  
	 * @param src the original source of the vertex
	 * @param dest the original destination the vertex 
	 * @param newSrc the new source of the vertex
	 * @param newDest the new destination of the vertex
	 * @return
	 */
	public boolean updateEdge(int src, int dest, int newSrc, int newDest) {
		return false;
	}
		

	/**
	 * Print the graph
	 */
	public void printGraph() {
		System.out.println("numVertices = " + numVertices + " numEdges = " + numEdges);
		System.out.println("Adjacency List: ");
		for(int vertex : adjacencyList.keySet()) {
			System.out.print(vertex + " -> ");
			for (int edge : adjacencyList.get(vertex)) {
				System.out.print(edge + " ");
			}
			System.out.println("");
		}
		
		System.out.println("Edges:");
		/*
		for(int i=0; i < edges.size(); i++) {
			//System.out.println(edges.get(i)[0] + " " + edges.get(i)[1]);
		}
		*/
		for(Edge edge : edges) {
			System.out.println(edge.src + " " + edge.dest);
		}
		
	}
	
	/**
	 * This method uses the randomized contraction algorithm to find the minimum cut in the graph.
	 * @return
	 */
	public int minimumCut() {
		/*
		 * While there are more than 2 vertices:
		 * 		Pick a remaining edge (u,v) uniformly at random
		 *      Merge (or "contract") u and v into a single vertex
		 *      remove self-loops
		 * return cut represented by final 2 vertices
		 */
		Graph workingGraph = new Graph(this);
		
		// BEGIN DEBUG
		
		workingGraph.contract();
		System.out.println("Inside minimutCut after contract:");
		workingGraph.printGraph();

		// END DEBUG
		
		/*
		while(workingGraph.getNumVertices() > 2) {
			workingGraph.contract();
		}
		*/
		return workingGraph.getNumEdges();
		
	}

	
	/**
	 * Performs and edge contraction:   
	 * 	Pick a remaining edge (u,v) uniformly at random
	 * 	Merge (or "contract") u and v into a single vertex
	 * 	Remove self-loops
	 */
	private void contract() {
		// TODO : Redo with better methods such as removeEdge, addEdge, updateEdge
		int randomEdge, src, dest;
		
		// TODO: Pick random integer between 1 and # edges
		randomEdge = 3; // Picks edge 1 4
		randomEdge--;
		
		
		// Random edge = src, dest 
		//src = edges.get(randomEdge)[0];
		//dest = edges.get(randomEdge)[1];
		// TODO - fix below to query Edges
		src = 1;
		dest = 4;
		
		// BUG TO FIX: Need to update Edges list as well - could do it as I go
		// or at the end loop through.  Either way - probably want an updateEdgeList (u,v,newU,newV) method.
		
		
		// Remove dest from src adj list
		adjacencyList.get(src).remove(Integer.valueOf(dest));
		// Remove src from dest adj list
		adjacencyList.get(dest).remove(Integer.valueOf(src));
		// For each vertex v in dest adj list - add new edge src -> v
		for(int v : adjacencyList.get(dest)) {
			adjacencyList.get(src).add(v);
			// Also update edges list src, dest to src, v
			//updateEdgeList(src, dest, src, v);
		}
		// For each vertex v in adjacency list - if dest exists replace with src
		for (int v: adjacencyList.keySet()) {
			while(adjacencyList.get(v).remove(Integer.valueOf(dest))) {
				adjacencyList.get(v).add(src);
				// Also update edges list v, dest to v, src
				//updateEdgeList(v, dest, v, src);
			}
		}
		// Remove dest key from adjacency list
		adjacencyList.remove(dest);	
		// remove the random edge from edges list
		edges.remove(randomEdge);
		// decrease numEdges, decrease numVertices
		numEdges--;
		numVertices--;
		// remove self loops from adjacency
		while (adjacencyList.get(src).contains(src)) {
			adjacencyList.get(src).remove(Integer.valueOf(src));
		}	
		
		/*
		// remove self loops from edges list
		for (int i = 0; i < edges.size(); i++) {
			if(edges.get(i)[0] == src && edges.get(i)[1] == src) {
				edges.remove(i);
				numEdges--;
			}
		}
		*/
		
	}
}
