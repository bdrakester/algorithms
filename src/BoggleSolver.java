/**
 * The immutable data type BoggleSover finds all valid words in give Boggle 
 * board, using a given dictionary. 
 * @author Brian Drake
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.TST;


public class BoggleSolver {
    // private final TST<Integer> dictionary;
    private final BoggleDictionary dictionary;
    private int rows;
    private int cols;
    private boolean[][] marked;
    private BoggleBoard board;
    private HashSet<String> validWords;
    
    private static class Die {
        int row;
        int col;
        
        public Die(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        public String toString() {
            return "Die at (" + row + ", " + col + ")"; 
        }
        
    }

    /**
     * Initializes the data structure using the given array of strings as the
     * dictionary. (You can assume each word in the dictionary contains only 
     * the uppercase letters A through Z.)
     * @param dictionary
     */
    public BoggleSolver(String[] dictionary) {
    	// this.dictionary = new TST<>();
    	this.dictionary = new BoggleDictionary(dictionary);
        
    	/*
        for (String word : dictionary) {
            this.dictionary.put(word, word.length());
        }
        */
    }
    
    /**
     * Return all the Die reacheble from a Die at row, col.
     * @param row the row of the Die.
     * @param col the column of the Die.
     * @return an Iterable of all Die reachable from the Die at row, col.
     */
    private Iterable<Die> adjacent(int row, int col) {
        ArrayList<Die> dice = new ArrayList<>();
        
        // If not top row, add dice above
        if (row > 0) {
            // If not left edge
            if (col > 0) {
                dice.add(new Die(row - 1, col - 1));
            }
            dice.add(new Die(row - 1, col));
            // If not right edge
            if (col < cols - 1) {
                dice.add(new Die(row - 1, col + 1));
            }
        }
        // If not on left edge, add die to left
        if (col > 0) {
            dice.add(new Die(row, col - 1));
        }
        // If not on right edge, add die to right
        if (col < cols - 1) {
            dice.add(new Die(row, col + 1));
        }
        
        // If not bottom row, add dice below
        if (row < rows - 1) {
            // If not left edge
            if (col > 0) {
                dice.add(new Die(row + 1, col - 1));
            }
            dice.add(new Die(row + 1, col));
            // If not right edge
            if (col < cols -1) {
                dice.add(new Die(row + 1, col + 1));
            }
        }
        
        return dice;    
    }
    
    /**
     * Returns the set of all valid words in the given Boggle board, as an Iterable.
     * @param board
     * @return
     */
    public Iterable<String> getAllValidWords(BoggleBoard board) {
       rows = board.rows();
       cols = board.cols();
       this.board = board;
       marked = new boolean[rows][cols];
       validWords = new HashSet<>();
       
       // Using each die as a starting point, use DFS to traverse the 
       // board and generate all potential words.
       for (int i = 0; i < rows; i++) {
           for (int j = 0; j < cols; j++) {
        	   char first = this.board.getLetter(i, j);
        	   if (first == 'Q') {
        		   searchBoard(i, j, "QU");
        	   }
        	   else {
        		   searchBoard(i, j, String.valueOf(this.board.getLetter(i, j)));
        	   }
           }
       }
       
       return validWords; 
    }
    
    /**
     * Use DFS to enumerate all potential words on the board, starting at row, col
     * @param row the row of the starting die
     * @param col the column of the starting die
     * @param path the potential word generated so far
     */
    private void searchBoard(int row, int col, String path) {
    	// BEGIN DEBUG
    	// System.out.print("Inside searchBoard ... ");
    	// System.out.print("row = " + row);
    	// System.out.print(" col = " + col);
    	// System.out.println(" path = " + path);
    	// END DEBUG
    	
    	// If it's a valid word.
    	if (path.length() > 2 && inDictionary(path)) {
    		validWords.add(path);
    	}
    	
    	// If the path so far is a prefix to a word in the dictionary
    	if (dictionary.containsPrefix(path)) {
    		// 	Mark the die at (row, col) as visited
    		marked[row][col] = true;
    	
    		// Recursively visit all unvisited adjacent dice
    		for (Die die : adjacent(row, col)) {
    			if (!marked[die.row][die.col]) {
    				char next = board.getLetter(die.row, die.col);
    				if (next == 'Q') {
    					searchBoard(die.row, die.col, path + next + 'U');
    				}
    				else {
    					searchBoard(die.row, die.col, path + next);
    				}
    			}
    		}
    	
    		// Unmark the die as visited before returning
    		marked[row][col] = false;
    	}
    }
    
    /**
     * Returns the score of the given word if it is in the dictionary, 
     * zero otherwise.  (You can assume the word contains only the 
     * uppercase letters A through Z.)
     * @param word
     * @return
     */
    public int scoreOf(String word) {
        if (inDictionary(word) && word.length() > 2) {
            if (word.length() < 5) { return 1; }
            else if (word.length() == 5) { return 2; }
            else if (word.length() == 6) { return 3; }
            else if (word.length() == 7) { return 5; }
            else { return 11; }
        }
        else { return 0; }
    }
    
    /**
     * Is a word in the dictionary?  Returns true if word is in the dictionary.
     * @param word the word to look up
     * @return True if word is in the dictionary, else false.
     */
    private boolean inDictionary(String word) {
        return dictionary.contains(word);
    }
    
    /**
     * Unit tests.
     * @param args
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        
        /*
        String[] testWords = {"HELP", "MOM", "TACO", "MONKEYS"};
        for (String word : testWords) {
            System.out.println("solver.inDictionary(" + word + ") = " + solver.inDictionary(word));
        }
        
        String[] testPrefix = {"MONKEYS", "MONKEY", "MONK", "MONKA"};
        for (String prefix : testPrefix) {
        	System.out.print("solver.dictionary.keysWithPrefix(" + prefix + ") = ");
        	if(!solver.dictionary.keysWithPrefix(prefix).iterator().hasNext()) {
        		System.out.println("no prefix matches");
        	}
        	else {
        		System.out.println(solver.dictionary.keysWithPrefix(prefix));
        	}
        	
        }
        */
        // Get familiar with BoggleBoard class
        // System.out.println("board = \n" + board.toString());
        /*
        System.out.println("board.rows() = " + board.rows());
        System.out.println("board.cols() = " + board.cols());
        
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                System.out.println("board.getLetter(" + i + ", " + j + ") = " + board.getLetter(i, j));
            }
        }
        */      
        
        for (String word : solver.getAllValidWords(board)) {
            System.out.println(word);
            score += solver.scoreOf(word);
        }
        System.out.println("\nScore = " + score);
        
        // solver.getAllValidWords(new BoggleBoard(5,5));
    }
}
