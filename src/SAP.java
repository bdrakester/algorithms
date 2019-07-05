
/**
 * The SAP class implements the immutable data type SAP.
 * 
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;

public class SAP {
    private Digraph digraph;
    private HashMap<Integer, HashMap<Integer, Integer>> lengths;
    private HashMap<Integer, HashMap<Integer, Integer>> ancestors;
    
    /**
     * Constructor takes a digraph (not necessarily a DAG)
     * @param G
     */
    public SAP(Digraph G) {
        digraph = new Digraph(G);
        lengths = new HashMap<>();
        ancestors = new HashMap<>();        
    }
    
    /**
     * Length of shortest ancestral path between v and w; -1 if no such path
     * @param v a vertex in the digraph
     * @param w a vertex in the digraph
     * @return the lenght of the shortest ancestral path between v and w
     */
    public int length(int v, int w) {
        // If not already, compute shortest ancestral path between v and w
        if (!lengths.containsKey(v) || !lengths.get(v).containsKey(w)) {
            computeSAP(v, w);    
        }
        
        return lengths.get(v).get(w);
    }
    
    /**
     * A common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     * @param v
     * @param w
     * @return
     */
    public int ancestor(int v, int w) {
        // If not already, computer shortest ancestral path between v and w
        if (!ancestors.containsKey(v) || !ancestors.get(v).containsKey(w)) {
            computeSAP(v, w);    
        }
        
        return ancestors.get(v).get(w);
    }
    
    /**
     * Computes shortest ancestoral path between v and w.  Stores the ancestor
     * and length.  Stores both as -1 if no such path.
     * @param v
     * @param w
     */
    private void computeSAP(int v, int w) {
        // Run BFS (G, v) - gets shortest path from v to all other vertices
        BreadthFirstDirectedPaths vBFS = new BreadthFirstDirectedPaths(digraph, v);
        
        // Run BFS (G, w) - gets shortest path from w to all other vertices
        BreadthFirstDirectedPaths wBFS = new BreadthFirstDirectedPaths(digraph, w);
        
        // Initialize minimum to max value
        int minimum = Integer.MAX_VALUE;
        int ancestor = -1;
        // For each vertex i
        for (int i = 0; i < digraph.V(); i++) {
            // If there's a path from v to i and path from w to i
            if (vBFS.hasPathTo(i) && wBFS.hasPathTo(i)) {
              // Compute the ancestral path length
              int currentLength = vBFS.distTo(i) + wBFS.distTo(i);
              // If it's less than the minimum learned so far, set new minimum
              if (currentLength < minimum) {
                  minimum = currentLength;
                  ancestor = i;
              }
            }
        }
        
        // Initialize ancestors value HashMap if not already 
        if (!ancestors.containsKey(v)) {
            HashMap<Integer, Integer> val = new HashMap<>();
            ancestors.put(v, val);
        }
        
        // Initialize lengths value HashMap if not already
        if (!lengths.containsKey(v)) {
            HashMap<Integer, Integer> val = new HashMap<>();
            lengths.put(v, val);
        }
        
        // If a shortest ancestor was found 
        if (ancestor != -1) {    
            // Add to ancestors HashMap
            ancestors.get(v).put(w, ancestor);
            // Add to lengths HashMap
            lengths.get(v).put(w, minimum);
        }
        
        // No shortest ancestor, set to -1
        else {
            // Add to ancestors HashMap
            ancestors.get(v).put(w, -1);
            // Add to lengths HashMap
            lengths.get(v).put(w, -1);
        }
        
    }

    /**
     * Length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     * @param v
     * @param w
     * @return
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int vKey = computeKey(v);
        int wKey = computeKey(w);
        // If not already, compute shortest ancestral path between v and w
        if (!lengths.containsKey(vKey) || !lengths.get(vKey).containsKey(wKey)) {
            computeSAP(v, w);    
        }
        
        return lengths.get(vKey).get(wKey);
    }

    /**
     * A common ancestor that participates in shortest ancestral path; -1 if no such path
     * @param v
     * @param w
     * @return
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int vKey = computeKey(v);
        int wKey = computeKey(w);
        // If not already, computer shortest ancestral path between v and w
        if (!ancestors.containsKey(vKey) || !ancestors.get(vKey).containsKey(wKey)) {
            computeSAP(v, w);    
        }
        
        return ancestors.get(vKey).get(wKey);
    }

    private void computeSAP(Iterable<Integer> v, Iterable<Integer> w) {
        // Run BFS (G, v) - gets shortest path from v to all other vertices
        BreadthFirstDirectedPaths vBFS = new BreadthFirstDirectedPaths(digraph, v);
        // Run BFS (G, w) - gets shortest path from w to all other vertices
        BreadthFirstDirectedPaths wBFS = new BreadthFirstDirectedPaths(digraph, w);
        
        // Initialize minimum to max value
        int minimum = Integer.MAX_VALUE;
        int ancestor = -1;
        // For each vertex i
        for (int i = 0; i < digraph.V(); i++) {
            // If there's a path from v to i and path from w to i
            if (vBFS.hasPathTo(i) && wBFS.hasPathTo(i)) {
              // Compute the ancestral path length
              int currentLength = vBFS.distTo(i) + wBFS.distTo(i);
              // If it's less than the minimum learned so far, set new minimum
              if (currentLength < minimum) {
                  minimum = currentLength;
                  ancestor = i;
              }
            }
        }
        
        // Compute key for the iterables
        int vKey = computeKey(v);
        int wKey = computeKey(w);
        // Initialize ancestors value HashMap if not already 
        if (!ancestors.containsKey(vKey)) {
            HashMap<Integer, Integer> val = new HashMap<>();
            ancestors.put(vKey, val);
        }
        
        // Initialize lengths value HashMap if not already
        if (!lengths.containsKey(vKey)) {
            HashMap<Integer, Integer> val = new HashMap<>();
            lengths.put(vKey, val);
        }
        
        // If a shortest ancestor was found 
        if (ancestor != -1) {    
            // Add to ancestors HashMap
            ancestors.get(vKey).put(wKey, ancestor);
            // Add to lengths HashMap
            lengths.get(vKey).put(wKey, minimum);
        }
        
        // No shortest ancestor, set to -1
        else {
            // Add to ancestors HashMap
            ancestors.get(vKey).put(wKey, -1);
            // Add to lengths HashMap
            lengths.get(vKey).put(wKey, -1);
        }
        
    }
    
    /**
     * Compute an integer key representing the interable of vertices v
     * @param v an iterable of vertices
     * @return an integer representing a HashMap key for v
     */
    private int computeKey(Iterable<Integer> v) {
        // Add all the vertices plus 2
        int sum = 2;
        for (int i : v) {
            sum += i;
        }
        
        // Return the negative value
        return 0 - sum;
        
    }
    
    /**
     * Do unit testing of this class
     * @param args
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        
        StdOut.printf("G = %s", G.toString());
        
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
        
        // Now read in sets of vertices for testing iterable
        
        
    }


}
