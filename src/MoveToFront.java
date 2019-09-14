/**
 * The MoveToFront class implements the move-to-front encoding and decoding 
 * algorithm.
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import java.util.ArrayList;


// TODO - Got warnings saying I should use an array instead of arraylist for perf reasons.
public class MoveToFront {

    /**
     * Apply move-to-front encoding, reading from standard input and writing 
     * to standard output.
     * Running time must be O(n*R) or better.
     */
    public static void encode() {
        int R = 256;  // Alphabet size
        ArrayList<Character> sequence = new ArrayList<>(R);
        
        // Initialize the sequence 
        for (int i = 0; i < R; i++) { sequence.add((char) i);  }
         
        // Input loop - read one char at a time
        while (!BinaryStdIn.isEmpty()) {
            char input = BinaryStdIn.readChar();
  
            // Get index in sequence of input
            int index = sequence.indexOf(input);
  
            // Write index to BinaryStdOut.write()
            BinaryStdOut.write((char) index); 
            
            // Move input to front of sequence 
            sequence.remove(index);
            sequence.add(0, input);
        }
        BinaryStdOut.close();
        
    }

    /**
     * Apply move-to-front decoding, reading from standard input and writing 
     * to standard output.
     * Running time must be O(n*R) or better.
     */
    public static void decode() {
        int R = 256;  // Alphabet size
        ArrayList<Character> sequence = new ArrayList<>(R);
        
        // Initialize the sequence 
        for (int i = 0; i < R; i++) { sequence.add((char) i);  }

        // Input loop - read one char at a time
        while (!BinaryStdIn.isEmpty()) {
            char input = BinaryStdIn.readChar();
            
            // Treat input as integer
            // Write character at position input sequence
            char c = sequence.get((int) input);
            BinaryStdOut.write(c);
            
            // Move that character to the front
            sequence.remove((int) input);
            sequence.add(0, c);
        }
        BinaryStdOut.close();
    }

    /**
     * If args[0] is "-", apply move-to-front encoding
     * If args[0] is "+", apply move-to-front decoding
     * @param args
     */
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        }
        else if (args[0].equals("+")) {
            decode();
        }
        
    }

}