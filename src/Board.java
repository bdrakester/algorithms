/**
 * The Board class represents a board for the the n-puzzle problem.
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Queue;

public class Board {
	private int[][] tiles; 
	private int[][] goal; 
	private int n; // dimension of the board
	private Board twin; // twin board 
	
	/**
	 * Construct a board from an n-by-n array of blocks, where 
	 * blocks[i][j] = block in row i, column j.
	 * @param blocks
	 */
    public Board(int[][] blocks) {
    	n = blocks.length;
    	tiles = new int[n][n];
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			tiles[i][j] = blocks[i][j];
    		}
    	}
    	
    	// Generate the goal state, saves time later.
    	goal = new int[n][n];
    	for (int i = 0; i < n; i++) {
    	    for (int j = 0; j < n; j++) {
    	    	goal[i][j] = n*i + j + 1;
    	    }
    	}
    	goal[n-1][n-1] = 0;
    	twin = null;
    }
    
    /**
     * Board dimension n
     * @return
     */
    public int dimension() {
    	return n;
    }
    
    /**
     * Number of blocks out of place
     * @return
     */
    public int hamming() {
    	int count = 0;
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (tiles[i][j] != 0 && tiles[i][j] != goal[i][j]) count++; 
    		}
    	}
    	
    	return count;
    }
    
    
    /**
     * Sum of Manhattan distances between blocks and goal
     * @return
     */
    public int manhattan() {
    	int total = 0;
    	// Iterate over every tile
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			// Don't count empty tile
    			if(tiles[i][j] == 0) continue;
    			// Compute the goal coordinates
    			int goalI = (tiles[i][j] - 1) / n;
    			int goalJ = tiles[i][j] - (n * goalI) - 1;
    			// Calculate distance between goal and actual coordinates
    			int distance = Math.abs(goalI - i) + Math.abs(goalJ - j);
    			// Add to the total
    			total += distance;
    		}
    	}
    	
    	return total;
    }
    
    /**
     * Returns the goal board for this grid.
     * 
     * @return
     */
    private Board goal() {
    	return new Board(goal);
    }
    
    /**
     * Is this board the goal board?
     * @return
     */
    public boolean isGoal() {
    	return this.equals(this.goal());
    }
    
    /**
     * A board that is obtained by exchanging any pair of blocks
     * @return
     */
    public Board twin() {
    	if (twin != null) {
    		return twin;
    	}
    	
    	twin = new Board(tiles);
    	// Pick two random tiles (can't be zero)
    	int i1 = StdRandom.uniform(n);
    	int j1 = StdRandom.uniform(n);
    	while(twin.tiles[i1][j1] == 0) {
    		i1 = StdRandom.uniform(n);
    		j1 = StdRandom.uniform(n);
    	}
    	int i2 = StdRandom.uniform(n);
    	int j2 = StdRandom.uniform(n);
    	while(twin.tiles[i2][j2] == 0 || (i2 == i1 && j2 == j1)) {
    		i2 = StdRandom.uniform(n);
    		j2 = StdRandom.uniform(n);
    	}
    	// DEBUG
    	//System.out.println("i1,j1 = " + i1 + "," + j1);
    	//System.out.println("i2,j2 = " + i2 + "," + j2);
    	// END DEBUG
    	// Swap the random tiles
    	int temp = twin.tiles[i1][j1];
    	twin.tiles[i1][j1] = twin.tiles[i2][j2];
    	twin.tiles[i2][j2] = temp;
    	
    	return twin;
    }
    
    /**
     * Does this board equal y?
     * @param y the other board
     * @return true if this board equals y; otherwise false.
     */
    public boolean equals(Object y) {
    	if (y == this) return true;
    	if (y == null) return false;
    	if (y.getClass() != this.getClass()) return false;
    	
    	Board that = (Board) y;
    	if (that.dimension() != n) return false;
    	
    	// Compare each tile
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if(this.tiles[i][j] != that.tiles[i][j]) return false;
    		}
    	}   	
    	// Every tile was the same, boards are equal
    	return true;
    }
    
    /**
     * All neighboring boards
     * @return
     */
    public Iterable<Board> neighbors() {
    	/*
    	 * Add all potential neighbor boards to a queue and return that.
    	 */
    	Queue<Board> neighbors = new Queue<>();
    	int[][] workingTiles = new int[n][n];
    	
    	// Create a copy of the board tiles, saving the 0 tile coordinates
    	int i0 = 0;
    	int j0 = 0;
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			workingTiles[i][j] = tiles[i][j];
    			if (tiles[i][j] == 0) { 
    				i0 = i;
    				j0 = j;
    			}
    		}
    	}
    	// DEBUG
    	//System.out.println("neighbors(): - found 0 at " + i0 + "," + j0);
    	// END DEBUG
    	
    	/* 
    	 * Add to queue the board resulting from swapping 0 with 
    	 *   tile above, if not on top row
    	 *   tiles below, if not on bottom row
   	     *   tile to left, if not on left edge
   	     *   tile on right, if not on right edge
    	 */
    	// If not on the top row
    	if (i0 > 0) {
    		// Swap the 0 tile with the one above
    		workingTiles[i0][j0] = workingTiles[i0-1][j0];
    		workingTiles[i0-1][j0] = 0;
    		
    		neighbors.enqueue(new Board(workingTiles));
    		
    		// Swap the 0 tile back into place
    		workingTiles[i0-1][j0] = workingTiles[i0][j0];
    		workingTiles[i0][j0] = 0;
    	}
    	// If not on the bottom row
    	if (i0 < n-1) {
    		// Swap the 0 tile with the one below
    		workingTiles[i0][j0] = workingTiles[i0+1][j0];
    		workingTiles[i0+1][j0] = 0;
    		
    		neighbors.enqueue(new Board(workingTiles));
    		
    		// Swap the 0 tile back into place
    		workingTiles[i0+1][j0] = workingTiles[i0][j0];
    		workingTiles[i0][j0] = 0;
    	}
    	// If not on the left edge
    	if (j0 > 0) {
    		// Swap the 0 tile with the one to the left
    		workingTiles[i0][j0] = workingTiles[i0][j0-1];
    		workingTiles[i0][j0-1] = 0;
    		
    		neighbors.enqueue(new Board(workingTiles));
    		
    		// Swap the 0 tile back into place
    		workingTiles[i0][j0-1] = workingTiles[i0][j0];
    		workingTiles[i0][j0] = 0;
    	}
    	// If not on the right edge
    	if (j0 < n-1) {
    		// Swap the 0 tile with the one to the right
    		workingTiles[i0][j0] = workingTiles[i0][j0+1];
    		workingTiles[i0][j0+1] = 0;
    		
    		neighbors.enqueue(new Board(workingTiles));
    		
    		// Swap the 0 tile back into place
    		workingTiles[i0][j0+1] = workingTiles[i0][j0];
    		workingTiles[i0][j0] = 0;
    	}
    	
    	return neighbors;
    }
    
    /**
     * String representation of this board (in the output format specified below)
     */
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append(n + "\n");
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			s.append(String.format("%2d ", tiles[i][j]));
    		}
    		s.append("\n");
    	}
    	return s.toString();
    }
   

    /**
     * Main method for testing
     * @param args
     */
	public static void main(String[] args) {

		// Create a board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board testBoard = new Board(blocks);
	    
	    System.out.println("testBoard: \n");
	    System.out.println(testBoard.toString());
	    int dim = testBoard.dimension();
	    System.out.println("testBoard.dimension() = " + dim);
	    int hamming = testBoard.hamming();
	    System.out.println("testBoard.hamming() = " + hamming);
	    int manhattan = testBoard.manhattan();
	    System.out.println("testBoard.manhattan() = " + manhattan);
	    
	    Board goalBoard = testBoard.goal();
	    System.out.println("\ngoalBoard: \n");
	    System.out.println(goalBoard.toString());
	    int goalHamming = goalBoard.hamming();
	    System.out.println("goalBoard.hamming() = " + goalHamming);
	    int goalManhattan = goalBoard.manhattan();
	    System.out.println("goalBoard.manhattan() = " + goalManhattan);
	    
	    Board twinBoard = testBoard.twin();
	    System.out.println("testBoard.twin(): \n");
	    System.out.println(twinBoard.toString());
	    System.out.println("testBoard: \n");
	    System.out.println(testBoard.toString());
	    
	    System.out.println("testboard.neighbors():");
	    for (Board neighbor : testBoard.neighbors()) {
	    	System.out.println(neighbor.toString());
	    }	    
	}
}
