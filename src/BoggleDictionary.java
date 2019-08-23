/**
 * The BoggleDictionary class implements the boggle dictionary as an TST with R squared branching at root.  
 * Since the possible entries are all the capital letters in the alphabet, R is 26.
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.TST;
import java.util.ArrayList;

public class BoggleDictionary {
	// private final TST<Integer> dictionary;
	// private final TST<Integer>[][] dictionary;
	private ArrayList<ArrayList<TST<Integer>>> dictionary;
	
	/**
	 * Constructor
	 * @param dictionary
	 */
	public BoggleDictionary(String[] words) {
		// this.dictionary = new TST<>();
		dictionary = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			dictionary.add(i, new ArrayList<>());
		}
				
        for (String word : words) {
        	System.out.print("\nword = " + word + "  ");
            // this.dictionary.put(word, word.length());
        	
        	if (word.length() > 2) {
        		System.out.print("... Adding TST<Integer> at [" + ((int)word.charAt(0)-65) + "][" + ((int)word.charAt(1)-65) + "] " );
        		try {
        			System.out.print("Inside try... ");
        		    dictionary.get((int)(word.charAt(0)-65)).get((int)(word.charAt(1)-65)).put(word.substring(2), word.length()-2);
        		    System.out.print("Worked within try ...");
        		}
        		finally {
        			System.out.print("Inside finally ... ");
        			dictionary.get((int)(word.charAt(0)-65)).add((int)(word.charAt(1)-65), new TST<Integer>());
        		    dictionary.get((int)(word.charAt(0)-65)).get((int)(word.charAt(1)-65)).put(word.substring(2), word.length()-2);
        		    System.out.print("Worked within finally ...");
        		}
        	}
        }
	}
	
	/**
	 * Does the dictionary contain word?
	 * @param word the word.
	 * @return True if the dictionary contains the word, else false.
	 */
	public boolean contains(String word) {
		// return dictionary.contains(word);
		return false;
	}
	
	/**
	 * Does the dictionary contain a word with this prefix?
	 * @param prefix the prefix
	 * @return True if the dictionary contains 1 or more words with
	 * 	the prefix, else false.
	 */
	public boolean containsPrefix(String prefix) {
		// return dictionary.keysWithPrefix(prefix).iterator().hasNext();
		return false;
		
	}
	
	public static void main(String[] args) {
		 In in = new In(args[0]);
	     String[] allWords = in.readAllStrings();
	     BoggleDictionary testDict = new BoggleDictionary(allWords); 

	     // Test the contains method
	     String[] testWords = {"HELP", "MOM", "TACO", "MONKEYS"};	     	     
	     for (String word : testWords) {
	    	 System.out.println("testDict.contains(" + word + ") = " + testDict.contains(word));
	     }
	        
	     String[] testPrefix = {"MONKEYS", "MONKEY", "MONK", "MONKA", "TAC", "MOM"};
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
