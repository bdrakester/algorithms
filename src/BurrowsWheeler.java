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
        // System.out.println("Begin inverseTransform ...");
        // END DEBUG
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        
        // BEGIN DEBUG
        // System.out.println("first = " + first);
        // System.out.println("t = " + t);
        // END DEBUG
        int[] next = new int[t.length()];
        
        // Use key-index counting to sort t[] to get first column f of 
        // sorted suffixes
        int R = 256; // 16 * 16 possible values for a byte
        
        int N = t.length();
        char[] f = new char[N];
        
        // Count frequencies of each letter
        int[] count = new int[R+1];
        for (int i = 0; i < N; i++) {
            // BEGIN DEBUG
            // System.out.println("t[" + i + "] = " + t.charAt(i) + "\t(int)t[i] = " + (int)t.charAt(i));
            // END DEBUG
            count[(int)t.charAt(i)+1]++;
        }
        
        // BEGIN DEBUG
        // System.out.println("After counting frequencies ...");
        // for(int i = 0; i < count.length; i++) {
        //    System.out.println("count[" + i + "] = " + count[i]);
        // }
        // END DEBUG
        
        // Compute frequency cumulates
        for (int r = 0; r < R; r++) {
            count[r+1] += count[r];
        }
        
        // BEGIN DEBUG
        // System.out.println("After counting cumulates ...");
        // for (int i = 0; i < count.length; i++) {
        //    System.out.println("count[" + i + "] = " + count[i]);
        // }
        // END DEBUG
        
        // Access cumulates using key as index to move items, 
        // after loop f contains first column of sorted suffixes.
        for (int i = 0; i < N; i++) {
            f[count[(int)t.charAt(i)]++] = t.charAt(i);
        }
        
        // BEGIN DEBUG
        // for (int i = 0; i < f.length; i++) {
        //    System.out.println("f[" + i + "] = " + f[i]);
        // }
        // END DEBUG
        
        // Construct next array
        // TODO - more efficient way?  
        //        Perhaps populate next while doing key-index count?
        //        Or in previous loops creating an indexOf thing
        boolean[] addedYet = new boolean[N];  
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!addedYet[j]) {
                    if (t.charAt(j) == f[i] ) {
                        next[i] = j;
                        addedYet[j] = true;
                        break;
                    }
                }
            }
        }
        
        // BEGIN DEBUG
        // System.out.println("next[] = ...");
        // for (int i = 0; i < next.length; i++) {
        //     System.out.println("next[" + i + "] = " + next[i]);
        // }
        // END DEBUG
        
        char[] original = new char[N];
        int current = first;
        for (int i = 0; i < N; i++) {
            original[i] = f[current];
            current = next[current];
        }
        
        for (char c : original) {
            BinaryStdOut.write(c);
        }
        BinaryStdOut.close();

        
    }
    
    /**
     * if args[0] is "-", apply Burrows-Wheeler transform.
     * if args[0] is "+", apply Burrows-Wheeler inverse transform
     * @param args command line arguments.
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
