/**
 * The Solver class finds an optimal solution to the slider puzzle.
 * 
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import java.util.Comparator;

public class Solver {
	//private MinPQ<SearchNode> queue;
	private static final Comparator<SearchNode> BY_MANHATTAN = new ByManhattan();
	private SearchNode goal; // The SearchNode for the goal board
	
	private class SearchNode {
		public Board board; // the game board
		public int moves; // the number moves made to reach the board
		public SearchNode predecessor; // the preceding search node
		public int manhattan;  // the Manhattan priority of the board
		
		/**
		 * Construct a new SearchNode
		 * @param board the game board
		 * @param predecessor the preceding search node
		 * @param moves the number of moves made to reach board
		 */
		SearchNode(Board board, SearchNode predecessor, int moves) {
		    this.board = board;
		    this.predecessor = predecessor;
		    this.moves = moves;
		    this.manhattan = board.manhattan();
		}
		
		public void print() {
			int priority = manhattan + moves;
			
			System.out.println("priority = " + priority);
			System.out.println("moves = " + moves);
			System.out.println("manhattan = " + manhattan);
			System.out.println("predecessor = " + predecessor);
			System.out.println(board.toString());
			
		}
	}
	
	private static class ByManhattan implements Comparator<SearchNode> {
		public int compare(SearchNode v, SearchNode w) {
			return (v.manhattan + v.moves) - (w.manhattan + w.moves);
		}
	}
	
	/**
	 * Find a solution to the initial board using the A* algorithm.
	 * @param initial
	 */
    public Solver(Board initial) {
    	if (initial == null) {
    		throw new java.lang.IllegalArgumentException();
    	}
    	
    	// Two priority queues to run two searches in parallel
    	MinPQ<SearchNode> queue = new MinPQ<>(Solver.BY_MANHATTAN);
    	MinPQ<SearchNode> twinQueue = new MinPQ<>(Solver.BY_MANHATTAN);
    	
    	// Insert inital search node into each priority queue
    	SearchNode initialNode = new SearchNode(initial, null, 0);
    	//queue.insert(initialNode);
    	SearchNode twinInitialNode = new SearchNode(initial.twin(), null, 0);
    	//twinQueue.insert(twinInitialNode);
    	
    	/*
    	 *  Repeat on each queue until the goal board is dequeued
    	 *   delete from the priority queue the search node with the minimum priority
    	 *   insert onto the priority queue all neighboring search nodes 
    	 *   	(excluding it's predecessor)
    	 */
    	SearchNode current = initialNode;
    	SearchNode twinCurrent = twinInitialNode;
    	
    	// DEBUG
    	//System.out.println("initialNode:");
    	//initialNode.print();
    	//System.out.println("twinInitialNode:");
    	//twinInitialNode.print();
    	// END DEBUG
    	
    	//while(!current.board.isGoal()) {
    	while(true) {
    		if(current.board.isGoal()) {
    			break;
    		}
    		else if(twinCurrent.board.isGoal()) {
    			current.moves = -1; // Indicate the board is unsolvable
    			break;
    		}
    		
    		for (Board neighbor : current.board.neighbors()) {
    			// Do not insert if it's the predecessor board
    			// TODO- redo to avoid null comparison each time (only matches
    			//       the initialNode).
    			if (current.predecessor == null || !current.predecessor.board.equals(neighbor)) {
    				queue.insert(new SearchNode(neighbor, current, current.moves + 1));
    			}
    		}
    		
    		for (Board neighbor : twinCurrent.board.neighbors()) {
    			// Do not insert if it's the predecessor board
    			// TODO- redo to avoid null comparison each time (only matches
    			//       the initialNode).
    			if (twinCurrent.predecessor == null || !twinCurrent.predecessor.board.equals(neighbor)) {
    				twinQueue.insert(new SearchNode(neighbor, twinCurrent, twinCurrent.moves + 1));
    			}
    		}
    		
    		// DEBUG
    		//System.out.println("After inserting - Contents of Queue ...");
    		//for (SearchNode node : queue) {
    		//	node.print();
    		//}
    		// END DEBUG
    		current = queue.delMin();
    		twinCurrent = twinQueue.delMin();
    	}
    	
    	goal = current;
    }
        
    /**
     * Is the initial board solvable?
     * @return
     */
    public boolean isSolvable() {
    	return goal.moves > -1;
    	
    }
    
    /**
     * Min number of moves to solve initial board; -1 if unsolvable
     * @return
     */
    public int moves() {
    	return goal.moves;
    }
    
    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     * @return
     */
    public Iterable<Board> solution() {
        Stack<Board> moveStack = new Stack<>();
        
        SearchNode current = goal;
        while (current != null) {
        	moveStack.push(current.board);
        	current = current.predecessor;
        }
        
        return moveStack;    
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
	    if (!solver.isSolvable())
	        System.out.println("No solution possible");
	    else {
	        System.out.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            System.out.println(board);
	    }

	}
}
