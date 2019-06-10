/**
 * The Solver class finds an optimal solution to the slider puzzle.
 * 
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	private MinPQ<SearchNode> queue;
	
	private class SearchNode {
		Board board; // the game board
		int moves; // the number moves made to reach the board
		SearchNode predecessor; // the preceding search node
		int manhattan;  // the Manhattan priority of the board
	}
	
	/**
	 * Find a solution to the initial board using the A* algorithm.
	 * @param initial
	 */
    public Solver(Board initial) {
    	
    }
    
    /**
     * Is the initial board solvable?
     * @return
     */
    public boolean isSolvable() {
    	return false;
    	
    }
    
    /**
     * Min number of moves to solve initial board; -1 if unsolvable
     * @return
     */
    public int moves() {
    	return 0;
    }
    
    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     * @return
     */
    public Iterable<Board> solution() {
        return null;	
    }


    /**
     * Solve a slider puzzle
     * @param args
     */
	public static void main(String[] args) {
		
		// Create initial board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	    
	    System.out.println(initial.toString());

	    
	    // Solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    //if (!solver.isSolvable())
	    //    StdOut.println("No solution possible");
	    //else {
	    //    StdOut.println("Minimum number of moves = " + solver.moves());
	    //    for (Board board : solver.solution())
	    //        StdOut.println(board);
	    //}

	}
}
