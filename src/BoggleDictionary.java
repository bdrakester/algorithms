/**
 * The BoggleDictionary class implements the boggle dictionary as an TST with R squared branching at root.  
 * Since the possible entries are all the capital letters in the alphabet, R is 26.
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.TST;
import java.util.HashMap;

public class BoggleDictionary {
    private final HashMap<String, TST<Integer>> dictionary;

	/**
	 * Constructor - loads all Strings in the array words into the dictionary.
	 * @param words an array of all Strings to be added to dictionary.
	 */
	public BoggleDictionary(String[] words) {
	    dictionary = new HashMap<>();
	    
        for (String word : words) {
        	// BEGIN DEBUG
            // System.out.print("\nword = " + word + "  ");
        	// END DEBUG
            
        	if (word.length() > 2) {
        	    String key = String.valueOf(word.charAt(0)) + String.valueOf(word.charAt(1));
        	    // BEGIN DEBUG
        	    // System.out.print("key = " + key);
        	    // END DEBUG
        	    
        	    // If the key doesn't already exist
        	    if (!dictionary.containsKey(key)) {
        	        // Create the TST for this key
        	        dictionary.put(key, new TST<Integer>());
        	    }
        	    
        	    // BEGIN DEBUG
        	    // System.out.print("  Adding " + word.substring(2) + " to TST ...");
        	    // END DEBUG
        	    
        	    dictionary.get(key).put(word.substring(2), word.length());
        	}
        }
	}
	
	/**
	 * Does the dictionary contain word?
	 * @param word the word.
	 * @return True if the dictionary contains the word, else false.
	 */
	public boolean contains(String word) {
	    if (word.length() < 2) { return false; }
	    if (word.length() == 2) { return dictionary.containsKey(word); } 
	    String key = String.valueOf(word.charAt(0)) + String.valueOf(word.charAt(1));
	    if (!dictionary.containsKey(key)) { return false; }
	    return dictionary.get(key).contains(word.substring(2));
	}
	
	/**
	 * Does the dictionary contain a word with this prefix?
	 * @param prefix the prefix
	 * @return True if the dictionary contains 1 or more words with
	 * 	the prefix, else false.
	 */
	public boolean containsPrefix(String prefix) {
	    if (prefix.length() == 1) {
	        char[] c = {prefix.charAt(0), 'A'};
	        while (c[1] <= 'Z') {
	            if (dictionary.containsKey(String.valueOf(c))) { return true; }
	            c[1]++;
	        }
	        return false;
	    }
	    if (prefix.length() == 2) {
	        return dictionary.containsKey(prefix);
	    }
	    
	    else {
	        String key = String.valueOf(prefix.charAt(0)) + String.valueOf(prefix.charAt(1));
	        if (!dictionary.containsKey(key)) { return false; }
	        return dictionary.get(key).keysWithPrefix(prefix.substring(2)).iterator().hasNext();
	    }
		
	}
	
	public static void main(String[] args) {
		 In in = new In(args[0]);
	     String[] allWords = in.readAllStrings();
	     BoggleDictionary testDict = new BoggleDictionary(allWords); 

	     // Test the contains method
	     System.out.println("Testing the contains method...");
	     String[] testWords = {"HELP", "MOM", "TACO", "MONKEYS"};	     	     
	     for (String word : testWords) {
	    	 System.out.println("testDict.contains(" + word + ") = " + testDict.contains(word));
	     }
	        
	     String[] testPrefix = {"MONKEYS", "MONKEY", "MONK", "MONKA", "TAC", "MOM", "M", "T", "MO", "TA"};
	     for (String prefix : testPrefix) {
	         System.out.println("testDict.containsPrefix(" + prefix + ") = " + testDict.containsPrefix(prefix));
	     }
	     
	     /*
	     Integer[][] testArray = new Integer[26][26];
	     char c = 'A';
	     while (c <= 'Z') {
	    	 char d = 'A';
	    	 while (d <= 'Z') {
	    		 testArray[(int)c-65][(int)d-65] = (int)c+d;
	    	     System.out.print(c + " " + d  + " = ");
	    	     System.out.print("testArray[" + ((int)c-65) + "][" + ((int)d-65) + "] = ");
	    	     System.out.print(testArray[(int)c-65][(int)d-65]);
	    	     System.out.println();
	    	     d++;
	    	 }
	    	 c++;
	     }
	     */

	     
	}
}
