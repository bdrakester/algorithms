/**
 * The BurrowsWheeler class implements the Burrows-Wheeler data 
 * compression algorithm.
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    /**
     * Apply Burrows-Wheeler transform,
     * reading from standard input and writing to standard output 
     */
    public static void transform() {
        // BEGIN DEBUG
        // System.out.println("Begin transform ...");
        // END DEBUG
        
        String input = BinaryStdIn.readString();
        
        // BEGIN DEBUG
        // System.out.println("input = ");
        // System.out.println(input);
        // END DEBUG
        
        CircularSuffixArray csa = new CircularSuffixArray(input);
        char[] t = new char[csa.length()];
        int first = 0;
        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                first = i;
                t[i] = input.charAt(csa.length() - 1);
            }
            else {
                t[i] = input.charAt(csa.index(i) - 1);
            }
        }
        
        BinaryStdOut.write(first);
        // System.out.println(first);
        for (char c : t) {
            // System.out.println(c);
            BinaryStdOut.write(c);
        }
        BinaryStdOut.close();
    }

    /**
     * Apply Burrows-Wheeler inverse transform,
     * reading from standard input and writing to standard output
     */
    public static void inverseTransform() {
        // BEGIN DEBUG
        System.out.println("Begin inverseTransform ...");
        // END DEBUG
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        
        // BEGIN DEBUG
        System.out.println("first = " + first);
        System.out.println("t = " + t);
        // END DEBUG
        int[] next = new int[t.length()];
        
        // Count frequencies of each letter
        
        
    }

    /**
     * if args[0] is "-", apply Burrows-Wheeler transform
     * if args[0] is "+", apply Burrows-Wheeler inverse transform
     * @param args
     */
    public static void main(String[] args) {
        
        if (args[0].contentEquals("-")) {
            transform();
        }
        else if (args[0].contentEquals("+")) {
            inverseTransform();
        }
    }

}
