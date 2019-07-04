/**
 * The WordNet class implements the immutable 
 * @author Brian Drake
 *
 */
public class WordNet {
    
    /**
     * Constructor takes the name of the two input files
     * @param synsets the name of the synsets file
     * @param hypernyms the name of the hypernyms file
     */
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null ) {
            throw new java.lang.IllegalArgumentException("Arguments can not be null.");
        }
        
    }

    /**
     * Returns all WordNet nouns
     * @return
     */
    public Iterable<String> nouns() {
        return null;
    }

    /**
     * Is the word a WordNet noun?
     * @param word
     * @return
     */
    public boolean isNoun(String word) {
        if (word == null) {
            throw new java.lang.IllegalArgumentException("Argument can not be null.");
        }
        
        return false;
    }

    /**
     * Distance between nounA and nounB (defined below).
     * @param nounA
     * @param nounB
     * @return
     */
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new java.lang.IllegalArgumentException("Argument can not be null.");
        }
        return 0;
    }

    /**
     * A synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
     * in a shortest ancestral path (defined below).
     * @param nounA
     * @param nounB
     * @return
     */
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new java.lang.IllegalArgumentException("Argument can not be null.");
        }
        return null;
    }

    /**
     * Unit tests for the WordNet class
     * @param args
     */
    public static void main(String[] args) {
      

    }

}
