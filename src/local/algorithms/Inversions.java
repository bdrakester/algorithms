package local.algorithms;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class has methods that count the number of inversions in a list of 
 * integers.
 * @author Brian Drake
 */
public class Inversions {
    ArrayList<Integer> list;
    ArrayList<Integer> sortedList;
    long numInversions;
    
    /**
     * 
     * @param filename 
     */
    public Inversions(String filename){
        list = this.loadFile(filename);
        sortedList =  new ArrayList<>();
        numInversions = 0L;
    }
    
    /**
     * 
     * @param input 
     */
    public Inversions(ArrayList<Integer> input){
        list = input;
        sortedList = new ArrayList<>();
        numInversions = 0L;
    }
    
    /**
     * 
     * @return 
     */
    public Long getNumInversions(){
        return numInversions;
    }
    
    /**
     * Method sorts counts the number of inversions in ArrayList input
     * @return int The number of inversions
     */
    public long sortAndCount(){
        int size = list.size();
        
        /* BEGIN DEBUG  
        System.out.println("");
        System.out.println("Begin sortAndcount ...");
        System.out.println("list = " + list);
        System.out.println("size = " + size);
        /* END DEBUG */
        
        if(size == 1){
            numInversions = 0;
            sortedList.addAll(list);
            return 0;
        }
       
        ArrayList<Integer> firstHalfList =  new ArrayList<>(list.subList(0, size/2));
        ArrayList<Integer> secondHalfList = new ArrayList<>(list.subList(size/2,size));
        
        Inversions firstHalf = new Inversions(firstHalfList);
        Inversions secondHalf = new Inversions(secondHalfList);
        
        /* BEGIN DEBUG 
        System.out.println("firstHalf = " + firstHalf.list);
        System.out.println("secondHalf = " + secondHalf.list);
        /* END DEBUG */
        
        firstHalf.sortAndCount();
        secondHalf.sortAndCount();
        numInversions = firstHalf.numInversions + secondHalf.numInversions;
        
        /* BEGIN DEBUG 
        System.out.print("firstHalf.sortedList = " + firstHalf.sortedList);
        System.out.println("\tfirstHalf.numInversions = " + firstHalf.numInversions);
        System.out.print("secondHalf.sortedList = " + secondHalf.sortedList);
        System.out.println("\tsecondHalf.numInversions = " + secondHalf.numInversions);
        System.out.println("firstHalf + secondHalf numInversions = " + numInversions);
        /* END DEBUG */
        
        // Merge the two sorted halfs and count split inversions
        int i = 0;
        int j = 0;
        for(int k = 0; k < size; k++){
            if( firstHalf.sortedList.get(i) < secondHalf.sortedList.get(j) ){
                sortedList.add(firstHalf.sortedList.get(i));
                i++;
                
                // If reached end of firstHalf
                if (i == firstHalf.sortedList.size() ){
                    // Add the remaining items from secondHalf
                    while( j < secondHalf.sortedList.size() ){
                        sortedList.add(secondHalf.sortedList.get(j));
                        j++;
                    }
                    break;    
                }
            } 
            else{
                sortedList.add(secondHalf.sortedList.get(j));
                j++;
                // Increment by number of elements remaining in firstHalf
                numInversions = numInversions + (firstHalf.list.size() - i);
                
                // If reached end of secondHalf
                if (j == secondHalf.sortedList.size() ){
                    // Add the remaining items from the first half
                    while( i < firstHalf.sortedList.size() ){
                        sortedList.add(firstHalf.sortedList.get(i));
                        i++;
                    }
                    break;
                }
            }
        }    
        /* BEGIN DEBUG 
        System.out.println("Returning numInversions = " + numInversions);
        /* END DEBUG */
        return numInversions;
    }
    
    /**
     * This methods returns the contents of filename in an array
     * @param filename The name of the file
     * @return ArrayList An ArrayList of integers loaded from input file
     */
    private ArrayList<Integer> loadFile(String filename){
        ArrayList<Integer> input = new ArrayList<>();
        
        try {
            Scanner scanner = new Scanner(new File(filename));
            while(scanner.hasNextInt()){
                input.add(scanner.nextInt());
            }
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening file " + filename);
        }
        
        return input;
    }
    
    /**
     * Convert ArrayList of integers to an array
     * @param input
     * @return int[] An array containing copy of ArrayList
     */
    /*
    private int[] toIntArray(ArrayList<Integer> input){
        int size = input.size();
        int[] output = new int[size];
        
        for(int i = 0; i < size; i++){
            output[i] = input.get(i);
        }
        
        return output;
    }
    */
    
    /**
     * 
     * @param input
     * @param expectedResult 
     * @return  
     */
    public static boolean testInversions(ArrayList<Integer> input, long expectedResult){
        Inversions objInv = new Inversions(input);
        objInv.sortAndCount();
        
        System.out.println("Test Case: " + input);
        System.out.println("Result: " + objInv.getNumInversions());
        System.out.println("Expected Result: " + expectedResult );
        if (objInv.getNumInversions() == expectedResult){
            System.out.println("Success!");
            return true;
                  
        }
        else{
            System.out.println("Failure :(");
            return false;
        }
        
    }
    
    public static void main(String[] args){
        String filename;
        
        // If no filename passed as command line argument, run test casess.
        if(args.length != 1){           
            HashMap<ArrayList<Integer>, Long> testCases = new HashMap<ArrayList<Integer>, Long>();
            testCases.put(new ArrayList<Integer>(Arrays.asList(1,3,5,2,4,6)), 3L);
            testCases.put(new ArrayList<Integer>(Arrays.asList(1,5,3,2,4)), 4L);
            testCases.put(new ArrayList<Integer>(Arrays.asList(5,4,3,2,1)), 10L);
            testCases.put(new ArrayList<Integer>(Arrays.asList(1,6,3,2,4,5)), 5L);
            testCases.put(new ArrayList<Integer>(Arrays.asList(9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0)), 56L);
            testCases.put(new ArrayList<Integer>(Arrays.asList(37, 7, 2, 14, 35, 47, 10, 24, 44, 17, 34, 11, 16, 48, 1, 39, 6, 33, 43, 26, 40, 4, 28, 5, 38, 41, 42, 12, 13, 21, 29, 18, 3, 19, 0, 32, 46, 27, 31, 25, 15, 36, 20, 8, 9, 49, 22, 23, 30, 45)), 590L);
            testCases.put(new ArrayList<Integer>(Arrays.asList(4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54)), 2372L);
            testCases.put(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6)), 0L);
            testCases.put(new ArrayList<Integer>(Arrays.asList(2,1,3,4)), 1L);
            testCases.put(new ArrayList<Integer>(Arrays.asList(1,2,4,3)), 1L);
            testCases.put(new ArrayList<Integer>(Arrays.asList(3,4,1,2)), 4L);
       
            int success = 0;
            int failure = 0;
            for (ArrayList<Integer> input : testCases.keySet()){
                if( testInversions(input, testCases.get(input)) ){
                    success++;
                }
                else{
                    failure++;
                }
            
            }
            System.out.println("");
        
        
            System.out.println("Successes: " + success + "  Failures: " + failure);
            System.out.println("");
            //System.out.println("Error: need to provide input file");
            //System.exit(0);    
        }
        
        else{
            filename = args[0];
            Inversions objInv = new Inversions(filename);
            objInv.sortAndCount();
        
            System.out.println("\nNumber of Inversions = " + objInv.getNumInversions());
        }
    }
}

