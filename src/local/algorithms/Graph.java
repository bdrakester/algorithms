package local.algorithms;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class implements an undirected graph where each vertex is identitified by an integer.
 * @author Brian Drake
 *
 */
public class Graph {
	private int numVertices;
	private int numEdges;
	private HashMap<Integer, ArrayList<Integer>> adjacencyList;
	private ArrayList<int[]> edges;
	
	/**
	 * Constructor
	 * Returns an empty graph. 
	 */
	public Graph() {
		adjacencyList = new HashMap<Integer, ArrayList<Integer>>();
		edges = new ArrayList<int[]>(); 
		numVertices = 0;
		numEdges = 0;
	}
	
	/**
	 * Copy constructor
	 * @param graph
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
		int[] vertices;
		this.edges = new ArrayList<int[]>();
		for(int i = 0; i < graph.edges.size(); i++) {
			vertices = new int[2];
			vertices[0] = graph.edges.get(i)[0];
			vertices[1] = graph.edges.get(i)[1];
			this.edges.add(vertices);
		}
	}
	
	
	/**
	 * Returns true if the graph contains the vertex 
	 * @param vertex Integer the vertex
	 * @return Boolean
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
		int[] vertices = {src,dest};
		edges.add(vertices);
		numEdges++;
		
		return true;
	}
	
	/**
	 * Updates an edge in the edge list.
	 * @param src
	 * @param dest
	 * @param newSrc
	 * @param newDest
	 * @return boolean True if successfully updated
	 */
	private boolean updateEdgeList(int src, int dest, int newSrc, int newDest) {
		
		int[] lookup = {src,dest};
		for(int[] edge: edges) {
			if(Arrays.equals(edge, lookup)) {
				edge[0] = newSrc;
				edge[1] = newDest;
			}
		}
		
		return true;
		
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
		for(int i=0; i < edges.size(); i++) {
			System.out.println(edges.get(i)[0] + " " + edges.get(i)[1]);
		}
		
	}
	
	/**
	 * This method uses the randomized contraction algorithm to find the minumum cut in the graph.
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
		src = edges.get(randomEdge)[0];
		dest = edges.get(randomEdge)[1];
		
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
			updateEdgeList(src, dest, src, v);
		}
		// For each vertex v in adjacency list - if dest exists replace with src
		for (int v: adjacencyList.keySet()) {
			while(adjacencyList.get(v).remove(Integer.valueOf(dest))) {
				adjacencyList.get(v).add(src);
				// Also update edges list v, dest to v, src
				updateEdgeList(v, dest, v, src);
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
		// remove self loops from edges list
		for (int i = 0; i < edges.size(); i++) {
			if(edges.get(i)[0] == src && edges.get(i)[1] == src) {
				edges.remove(i);
				numEdges--;
			}
		}
	}
}
