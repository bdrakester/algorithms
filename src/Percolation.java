/**
 * The Percolation class estimates the value of the percolation threshold 
 * via Monte Carlo simulation.
 *  
 * @author Brian Drake
 * 
 */
//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	// The dimensions of the square grid, n x n.
	private int n;
	// The total number of sites.
	private int size; 
	// The UF data structure representing the grid of sites.
	private WeightedQuickUnionUF sites; 
	// State of each site, true if it is open.
	private boolean open[];  
	
	/**
	 * Constructor
	 * @param n dimension of the square grid, it will be n-by-n. 
	 */
	public Percolation(int n) {
		this.n = n;
		size = n*n;
		sites = new WeightedQuickUnionUF(size);
		open = new boolean[size];
		
		// Set the open state to false for each site 
		for(int i = 0; i < size; i++) {
			open[i] = false;
		}
	}
	
	/**
	 * Convert 2-D coordinates (row, col) to a 1-D array index. The row and 
	 * col indices are integers between 1 and n. The upper-left site is (1,1).
	 * @param row
	 * @param col
	 * @return index in 1-D array
	 */
	private int xyTo1D(int row, int col) {
		return n*(row -1) + (col - 1);
	}
	
	/**
	 * Returns true if the (row, col) is a valid index
	 * @param row
	 * @param col
	 * @return true if (row, col) is a valid index
	 */
	private boolean validIndices(int row, int col) {
		int index = xyTo1D(row, col);
		
		if (index >= 0 && index < size) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * Opens site (row, col) if it is not already open.
	 * @param row
	 * @param col
	 */
	public void open(int row, int col) {
		// UF methods: union
		
		// Validate the indices
		if( !validIndices(row,col) ) {
			throw new IllegalArgumentException("Invalid indices");
		}
		
		// Mark the site as open
		int index = xyTo1D(row,col);
		open[index] = true;
		
		/* 
		 * Perform WeightedQuickUnionUF operations that link the site 
		 * No it's open neighbors: left, right, above, below.		 	
		 *   left = row, col-1
		 *   right = row, col+1
		 *   above = row-1, col
		 *   below = row+1, col  
		 */
		
		// Connect to left neighbor if it exists and is open
		if( validIndices(row,col-1) && isOpen(row,col-1) ) {
			sites.union(index,xyTo1D(row,col-1));
		}
		// Connect to the right nieghbor if it exists and is open
		if( validIndices(row,col+1) && isOpen(row,col+1) ) {
			sites.union(index,xyTo1D(row,col+1));
		} 
		// Connect to the above nieghbor if it exists and is open
		if( validIndices(row-1,col) && isOpen(row-1,col) ) {
			sites.union(index,xyTo1D(row-1,col));
		} 
		// Connect to the below nieghbor if it exists and is open
		if( validIndices(row+1,col) && isOpen(row+1,col) ) {
			sites.union(index,xyTo1D(row+1,col));
		} 
	}
	
	/**
	 * Is site (row, col) open?  
	 * @param row
	 * @param col
	 * @return true if the site is open
	 */
	public boolean isOpen(int row, int col) {
		int index = xyTo1D(row,col);
		
		return open[index];
	}
	
	/**
	 * Is site (row, col) full? 
	 * @param row
	 * @param col
	 * @return true if the site is full.
	 */
	public boolean isFull(int row, int col) {
		// UF Methods: connected
		return false;
	}
	
	/**
	 * Number of open sites.
	 * @return the number of open sites.
	 */
	public int numberOfOpenSites() {
		return 0;
	}
	
	/**
	 * Does the system percolate?
	 * @return true if the system percolates.
	 */
	public boolean percolates() {
		// UF methods: connected
		return false;
	}

	public static void main(String[] args) {
		boolean debug = true;
		Percolation p = new Percolation(5);
		
		System.out.println("Hello from Percolation!");
		
		if(debug) {
			System.out.println("Testing xyTo1D method...");
			int[][] testCases = {
					{1,1,0},
					{5,5,24},
					{3,2,11},
					{1,4,3},
					{2,5,9},
					{3,1,10}
			};
			
			int result; 
			int numFailures = 0;
			for (int i = 0; i < testCases.length; i++) {
				result = p.xyTo1D(testCases[i][0], testCases[i][1]);
				if (result == testCases[i][2]) {
					System.out.println("Test Case: (" + testCases[i][0] + ", " + testCases[i][1] + ") = " + result + " ... passed!");   
				}
				else {
					System.out.println("Test Case: (" + testCases[i][0] + ", " + testCases[i][1] + ") = " + result + " ... failed! Expected  " + testCases[i][2]);
					numFailures++;
				}
			}
			if(numFailures == 0) {
				System.out.println("All xyTo1D tests passed!\n");
			}
			else {
				System.out.println(numFailures + " xyTo1D tests failed.\n");
			}
			
			System.out.println("Text validIndices method...");
			// The first two elements are the index, the third is 1 if it's a valid index, or 0 if not
			int [][] testCasesI = {
					{1,1,1},
					{5,5,1},
					{3,2,1},
					{0,3,0},
					{6,3,0},
					{10,9,0}
			};
			boolean resultI;
			numFailures = 0;
			for (int i = 0; i < testCasesI.length; i++) {
				resultI = p.validIndices(testCasesI[i][0], testCasesI[i][1]);
				System.out.print("Test Case: (" + testCasesI[i][0] + ", " + testCasesI[i][1] + ") = " + resultI);
				if( (resultI && testCasesI[i][2] == 1) || (!resultI && testCasesI[i][2] == 0)) {
					System.out.println(" ... passed!");
				}
				else {
					System.out.println(" ... failed.");
					numFailures++;
				}
			}
			if(numFailures == 0) {
				System.out.println("All validIndices tests passed!\n");
			}
			else {
				System.out.println(numFailures + " validIndices tests failed.\n");
			}
			
			System.out.println("Test open and constructor methods...");
			Percolation testP = new Percolation(10);
			testP.open(1,1);
			testP.open(1,2);
			boolean connected = testP.sites.connected(testP.xyTo1D(1,1),testP.xyTo1D(1,2));
			if (connected) {
				System.out.println("Sites are connected! Passed!");
			}
			
			
			
		}
		
		
	}
}
