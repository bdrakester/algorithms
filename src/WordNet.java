/**
 * The WordNet class implements the immutable data type WordNet.
 * @author Brian Drake
 *
 */

import java.util.HashMap;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;


public class WordNet {
    private int numSynsets; // the number of synsets
    // Maps nouns to the synset id or list of ids it appears in 
    private final HashMap<String, ArrayList<Integer>> nouns;
    // Maps synset IDs to nouns
    private final HashMap<Integer, String> ids;
    private final Digraph hypernymsDG;
    private final SAP sap;
    
    /**
     * Constructor takes the name of the two input files
     * @param synsets the name of the synsets file
     * @param hypernyms the name of the hypernyms file
     */
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException("Arguments can not be null.");
        }
        numSynsets = 0;
        nouns = new HashMap<>();
        ids = new HashMap<>();
        parseSynsets(synsets);
        
        hypernymsDG = new Digraph(numSynsets);
        parseHypernyms(hypernyms);
        sap = new SAP(hypernymsDG);
    }
    
    /**
     * Read and parse the synsets file
     * @param synsets the name of the synsets file
     */
    private void parseSynsets(String synsets) {
        // BEGIN DEBUG
        // System.out.println("Parsing synsets file ... " + synsets);
        // END DEBUG
        In in = new In(synsets);
        
        // Parse the synsets
        while (in.hasNextLine()) {         
            String line = in.readLine();
            String[] values = line.split(",");
            int synsetID = Integer.parseInt(values[0]);
            String synset = values[1];
            
            // Add synset to the ids HashMap
            ids.put(synsetID, synset);
            numSynsets++; 
            
            String[] inputNouns = synset.split(" ");
            // String definition = values[2];
            /*
            // BEGIN DEBUG
            System.out.println("synsetID = " + synsetID);
            System.out.println("synset = " + synset);
            for (int i = 0; i < inputNouns.length; i++) {
                System.out.println("inputNouns[" + i + "] = " + inputNouns[i]);
            }
            System.out.println("definition = " + definition);
            // END DEBUG
            */
            for (String inputNoun : inputNouns) {
                // If the key already exists, append ID to the list
                if (nouns.containsKey(inputNoun)) {
                    nouns.get(inputNoun).add(synsetID);    
                }
                // The key does not exist, create a new list and add id
                else {
                    ArrayList<Integer> newIDList = new ArrayList<>();
                    newIDList.add(synsetID);
                    nouns.put(inputNoun, newIDList);
                }
            }  
        }
    }
    
    /**
     * Read and parse the hypernyms files
     * @param hypernyms the name of the hypernyms file
     */
    private void parseHypernyms(String hypernyms) {
        // BEGIN DEBUG
        // System.out.println("Parsing hypernyms file ... " + hypernyms);
        // END DEBUG
        In in = new In(hypernyms);
        int numRoots = 0;
        
        // Parse the hypernyms
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] values = line.split(",", 2);
            int synsetID = Integer.parseInt(values[0]);
            
            // BEGIN DEBUG
            // System.out.println("synsetID = " + synsetID);
            // END DEBUG
            
            if (values.length > 1) {
                values = values[1].split(",");
                for (String value : values) {
                    hypernymsDG.addEdge(synsetID, Integer.parseInt(value));
                    // BEGIN DEBUG
                    // System.out.println("Add edge " + synsetID + " -> " + Integer.parseInt(value));
                    // END DEBUG
                }
            }
            else {
                numRoots++;
            }
        }
        
        if (numRoots != 1) {
            throw new IllegalArgumentException("hypernmyms file does not correspond to a rooted DAG.");
        }
        DirectedCycle cycle = new DirectedCycle(hypernymsDG);
        if (cycle.hasCycle()) {
            throw new IllegalArgumentException("hypernmyms file does not correspond to a rooted DAG.");
        }
        
        
    }
    
    /**
     * Returns all WordNet nouns
     * @return an iterable of Strings containg every noun in the WordNet
     */
    public Iterable<String> nouns() {
        return nouns.keySet();
    }
    
    /**
     * Is the word a WordNet noun?
     * @param word
     * @return
     */
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }
        
        return nouns.containsKey(word);
    }
    
    /**
     * Distance between nounA and nounB.
     * @param nounA
     * @param nounB
     * @return
     */
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Both arguments must exist in the WordNet.");
        }
        
        // Get sysnset ID(s) for each noun
        ArrayList<Integer> synsetIDsA = nouns.get(nounA);
        ArrayList<Integer> synsetIDsB = nouns.get(nounB);
        
        // BEGIN DEBUG
        // System.out.println("synsetIDsA = " + synsetIDsA);
        // System.out.println("synsetIDsB = " + synsetIDsB);
        // END DEBU
        
        // SAP sap = new SAP(hypernymsDG);
        // If both have just one synsetID
        if (synsetIDsA.size() == 1 && synsetIDsB.size() == 1) {
            return sap.length(synsetIDsA.get(0), synsetIDsB.get(0));
        }
        // Else one or both have more than one synsetID
        else {
            return sap.length(synsetIDsA, synsetIDsB);
        }
    }

    /**
     * A synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
     * in a shortest ancestral path.
     * @param nounA
     * @param nounB
     * @return
     */
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }
        
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Both arguments must exist in the WordNet.");
        }
        
        // Get sysnset ID(s) for each noun
        ArrayList<Integer> synsetIDsA = nouns.get(nounA);
        ArrayList<Integer> synsetIDsB = nouns.get(nounB);
        
        // SAP sap = new SAP(hypernymsDG);
        // If both have just one synsetID
        if (synsetIDsA.size() == 1 && synsetIDsB.size() == 1) {
            return ids.get(sap.ancestor(synsetIDsA.get(0), synsetIDsB.get(0)));
        }
        // Else one or both have more than one synsetID
        else {
            return ids.get(sap.ancestor(synsetIDsA, synsetIDsB));
        }
    }

    /**
     * Unit tests for the WordNet class
     * @param args
     */
    public static void main(String[] args) {
        WordNet myWordNet = new WordNet(args[0], args[1]);
        
        
        System.out.println("myWordNet.nouns() = ");
        for (String s : myWordNet.nouns()) {
            System.out.println(s);
        }
        
        while (!StdIn.isEmpty()) {
            String word1 = StdIn.readString();
            String word2 = StdIn.readString();
            
            System.out.println(word1 + " : isNoun = " + myWordNet.isNoun(word1));
            if (myWordNet.isNoun(word1)) {
                System.out.println("synset IDs = " + myWordNet.nouns.get(word1));
                for (int id : myWordNet.nouns.get(word1)) {
                    System.out.println("id : " + id + " = " + myWordNet.ids.get(id));
                }
            }
            
            System.out.println(word2 + " : isNoun = " + myWordNet.isNoun(word2));
            if (myWordNet.isNoun(word2)) {
                System.out.println("synset IDs = " + myWordNet.nouns.get(word2));
                for (int id : myWordNet.nouns.get(word2)) {
                    System.out.println("id : " + id + " = " + myWordNet.ids.get(id));
                }
            }
            
            System.out.println("distance = " + myWordNet.distance(word1, word2));
            System.out.println("sap = " + myWordNet.sap(word1, word2));
        }
    }

}
