/**
 * The CircularSuffixArray class. 
 * 
 * "To efficiently implement the key component in the Burrows–Wheeler 
 * transform, you will use a fundamental data structure known as the 
 * circular suffix array, which describes the abstraction of a sorted 
 * array of the n circular suffixes of a string of length n."
 * 
 * @author Brian Drake
 *
 */

// TODO - Potential concern - how am I comparing characters for sorting?  Should
//          I be using a compareTo() method for char or String?

import java.util.Arrays;
import edu.princeton.cs.algs4.In;

public class CircularSuffixArray {
    final private String data;  // The original string
    private CircularSuffix[] suffixes;  // Array of circular suffixes
    
    /**
     * The inner class CircularSuffix represents a suffix
     * of String data by storing the index of the 
     * first character in that suffix.
     */
    private class CircularSuffix implements Comparable<CircularSuffix>{
        final private int first; // Index of the first character in suffix.
        
        /**
         * Constructor
         * @param n the index of the first character in the String
         */
        public CircularSuffix(int n) {
            first = n;
        }
        
        /**
         * Return the index of the first character in the suffix.
         * @return index of the first character.
         */
        public int index() {
            return first;
        }
        
        public int compareTo(CircularSuffix that) {
            int i = first;
            int j = that.first;
            
            // Loop through length data comparing circular suffix characters
            for (int count = 0; count < data.length(); count++) {
                if (data.charAt(i) < data.charAt(j)) { return -1; }
                if (data.charAt(i) > data.charAt(j)) { return 1; } 
                
                // Increment i and j
                i = (i + 1) % data.length();
                j = (j + 1) % data.length();     
            }
            // Made it through the loop, suffixes are equal
            return 0;
        }
        
        /**
         * Return the string representation of the suffix.
         * @return string containing the circular suffix.
         */
        public String toString() {
            StringBuilder result = new StringBuilder(data.length());
            for (int i = 0; i < data.length(); i++) {
                result.append( data.charAt( (first + i) % data.length() ) );
            }
            return result.toString();
        }
    }
    
    
    /**
     * Constructor for Circular suffix array of String s.
     * @param s the input string.
     */
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }
        data = s;
        suffixes = new CircularSuffix[data.length()];
        for (int i = 0; i < data.length(); i++) {
            suffixes[i] = new CircularSuffix(i);
        }
        
        // BEGIN DEBUG
        // System.out.println("Suffixes original ...");
        // for(int i = 0; i < data.length(); i++) {
        //    System.out.println(suffixes[i].toString());
        // }
        // END DEBUG
        
        Arrays.sort(suffixes);
        
        // BEGIN DEBUG
        // System.out.println("Sorted suffixes ...");
        // for(int i = 0; i < data.length(); i++) {
        //    System.out.println(suffixes[i].toString());
        // }
        // END DEBUG
    }

    /**
     * Length of the string. 
     * @return the length of the original string.
     */
    public int length() {
       return data.length();
    }

    /**
     * Returns index of the original suffix that appears ith in the sorted 
     * suffix array.
     * @param i the index of the suffix
     * @return the index of the ith sorted suffix.
     */
    public int index(int i) {
        if (i < 0 || i >= data.length()) {
            throw new IllegalArgumentException("Argument is outside range.");
        }
        return suffixes[i].index();
    }

    /**
     * Unit testing (required)
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Unit tests for CircularSuffixArray ...");
        System.out.println("Input file = " + args[0]);
        
        In in = new In(args[0]);
        String input = in.readAll();
                
        System.out.println("input = " + input);
        System.out.println("\nCreating CircularSuffixArray testArray ...");
        CircularSuffixArray testArray = new CircularSuffixArray(input);
        
        System.out.println("testArray.length() = " + testArray.length());
        
        System.out.println("testArray: ");
        for (int i = 0; i < testArray.length(); i++) {
            System.out.println("i = " + i + "   index(i) = " + testArray.index(i));
        }
        
    }

}