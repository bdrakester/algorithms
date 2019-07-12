/**
 * The Outcast class implments the immutable data type Outcast. 
 * It identifies outcasts in a WordNet by computing the sum 
 * of the distances between each nound and every other one. 
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.In;

public class Outcast {
    private final WordNet wordnet;
   
    /**
     * Constructor takes a WordNet object
     * @param wordnet
     */
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }
    
    /**
     * Given an array of WordNet nouns, return an outcast
     * @param nouns an array of WordNet nouns
     * @return the noun least related to the others
     */
    public String outcast(String[] nouns) {
        int max = 0;
        String outcast = null;
        for (String nounA : nouns) {
            int distance = 0;
            for (String nounB : nouns) {
                distance += wordnet.distance(nounA, nounB);
            }
            if (distance > max) {
                max = distance;
                outcast = nounA;
            }
        }
        return outcast;
    }
    
    /**
     * See test client below
     * @param args
     */
    public static void main(String[] args) {  
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            System.out.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
