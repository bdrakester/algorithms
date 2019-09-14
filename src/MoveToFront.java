/**
 * The MoveToFront class implements the move-to-front encoding and decoding 
 * algorithm.
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    /**
     * Apply move-to-front encoding, reading from standard input and writing to standard output
     * Running time must be O(n*R) or better
     */
    // TODO - use IndexMinPQ - logarithmic time! log n < O(n+R)!
    // after reading a char - make that the minimum ? requires updating all of them?
    // Just need my own linked list - move to front is easy - update pointers
    // or use Java LinkedList - it even has these methods!
    public static void encode() {
        int R = 256;
        char[] sequence = new char[R];
        
        // Initialize the sequence
        for (int i = 0; i < R; i++) { sequence[i] = (char)i; }
        
        // for (int i = 0; i < R; i++) { System.out.println("seq[" + i + "] = " + (int)sequence[i]); }
        
        // Input loop - read one char at a time
        while (!BinaryStdIn.isEmpty()) {
            char input = BinaryStdIn.readChar();
            BinaryStdOut.write(sequence[input]);
            // Move to front 
            
            
            
            
            
        }
        
    }

    /**
     * Apply move-to-front decoding, reading from standard input and writing to standard output
     */
    public static void decode() {

    }

    /**
     * If args[0] is "-", apply move-to-front encoding
     * If args[0] is "+", apply move-to-front decoding
     * @param args
     */
    public static void main(String[] args) {
        if (args[0].contentEquals("-")) {
            encode();
        }
        else if (args[0].contentEquals("+")) {
            decode();
        }
        
    }

}