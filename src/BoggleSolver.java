/**
 * The immutable data type BoggleSover finds all valid words in give Boggle 
 * board, using a given dictionary. 
 * @author Brian Drake
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.TrieST;


public class BoggleSolver {
    private final TrieST<Integer> dictionary;
    private int rows;
    private int cols;
    // private boolean[][] marked;
    // private Die[] edgeTo;
    ArrayList<String> validWords;
    
    private class Die {
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
        this.dictionary = new TrieST<>();
        
        for (String word : dictionary) {
            this.dictionary.put(word, word.length());
        }
    }

    /**
     * Use DFS to enumerate all possible strings on the board
     */
    private void searchBoard() {
        /* Start at v = 0,0 
         * Mark v as visited
         * Recursively visit all w adjacent to v
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
       ArrayList<String> possible = new ArrayList<>();
       
       // TODO - Instead of DFS - do a similar search but using a List of die already 
       // visited instead ... also helps build the words ... start with first search, 
       // then how woudl the rest go ...
       
       for (int i = 0; i < rows; i++) {
           for (int j = 0; j < cols; j++) {
               // BEGIN DEBUG
               System.out.println("Starting Die = (" + i + ", " + j + "):");
               // END DEBUG
               ArrayList<Die> path = new ArrayList<>();
               searchBoard(path, new Die(i,j), board);
               
               
           }
       }
       return validWords; 
    }
    
    private void searchBoard(int row, int col, String current, BoggleBoard board) {
        System.out.println("searchBoard: current = " + current);
        for (Die die : adjacent(row, col)) {
            searchBoard(die.row, die.col, current + board.getLetter(row, col), board);
        }
    }
    
    /**
     * Perform depth first search on the board starting from row, col
     * @param board the BoggleBoard
     * @param row 
     * @param col
     * @param marked
     */
    private void depthFirstSearch(BoggleBoard board, int row, int col, boolean[][] marked) {
        // BEGIN DEBUG
        System.out.println("dfs (" + row + ", " + col + ") " + marked.toString());
        // END DEBUG
        marked[row][col] = true;
        for (Die die : adjacent(row, col)) {
            if (!marked[die.row][die.col]) {
                depthFirstSearch(board, die.row, die.col, marked);
            }
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
        
        
        String[] testWords = {"HELP", "MOM", "TACO", "MONKEYS"};
        for (String word : testWords) {
            System.out.println("solver.inDictionary(" + word + ") = " + solver.inDictionary(word));
        }
        
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
