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
		size = n*n + 2;
		sites = new WeightedQuickUnionUF(size);
		open = new boolean[size];
		
		// Set virtual top and bottom sites to open
		open[0] = true;
		open[size-1] = true;
		// Set the open state to false for each site 
		for(int i = 1; i < size-1; i++) {
			open[i] = false;
		}
		
		/*
		 *  Connect every site in top row to virtual top site and
		 *  connect every bottom row site to the virtual bottom site.
		 */
		for(int i = 1; i <= n; i++) {
			sites.union(0, i); 
			sites.union(size-1, xyTo1D(n,i));
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
		int index = n*(row - 1) + col;
		return index;
	}
	
	/**
	 * Returns true if the (row, col) is a valid index
	 * @param row
	 * @param col
	 * @return true if (row, col) is a valid index
	 */
	private boolean validIndices(int row, int col) {
		if (row >= 1 && row <= n && col >= 1 && col <= n) {
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
		// Validate the indices
		if( !validIndices(row,col) ) {
			throw new IllegalArgumentException("Invalid indices");
		}
		
		// Mark the site as open
		int index = xyTo1D(row,col);
		open[index] = true;
		
		/* 
		 * Perform WeightedQuickUnionUF operations that link the site 
		 * to it's open neighbors: left, right, above, below.		 	
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
		// Validate the indices
		if ( !validIndices(row,col) ) {
			throw new IllegalArgumentException("Invalid indices");
		}
		
		int index = xyTo1D(row,col);
		
		return open[index];
	}
	
	/**
	 * Is site (row, col) full?  A full site is an open site that can be 
	 * connected to an open site in the top row via a chain of 
	 * neighboring open sites.
	 * @param row
	 * @param col
	 * @return true if the site is full.
	 */
	public boolean isFull(int row, int col) {
		// Validate the indices
		if ( !validIndices(row,col) ) {
			throw new IllegalArgumentException("Invalid indices");
		}
		
		// If the site is not open, it can't be full
		if ( !isOpen(row,col) ) {
			return false;
		}
		
		// If connected to to virtual top site 
		int site = xyTo1D(row,col);
		if( sites.connected(site,0) ) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Number of open sites.
	 * @return the number of open sites.
	 */
	public int numberOfOpenSites() {
		int openSites = 0;
		for (int i = 1; i < size -1; i++) {
			if (open[i]) {
				openSites++;
			}
		}
		return openSites;
	}
	
	/**
	 * Does the system percolate? A system percolates is there is a full site
	 * in the bottom row.
	 * @return true if the system percolates.
	 */
	public boolean percolates() {
		//If virtual bottom site is connected to the virtual top site.
		if (sites.connected(0, size-1)) {
			return true;
		}
		
		return false;
	}

	public static void main(String[] args) {
		boolean debug = true;
		Percolation p = new Percolation(5);
		
		System.out.println("Hello from Percolation!");
		
		if(debug) {
			System.out.println("Testing xyTo1D method...");
			int[][] testCases = {
					{1,1,1},
					{5,5,25},
					{3,2,12},
					{1,4,4},
					{2,5,10},
					{3,1,11}
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
				System.out.println("Sites are connected! Passed!\n");
			}
			
			System.out.println("Testing isOpen...");
			testP.open(2,2);
			testP.open(3,4);
			// The first two elements are the index, the third is 1 in it's open, or 0 if not.
			int[][] testCases2 = {
					{2,2,1},
					{3,4,1},
					{6,6,0},
					{8,1,0}
			};
			boolean result2;
			numFailures = 0;
			for (int i = 0; i < testCases2.length; i++) {
				result2 = testP.isOpen(testCases2[i][0], testCases2[i][1]);
				System.out.print("Test Case: (" + testCases2[i][0] + ", " + testCases2[i][1] + ") = " + result2);
				if( (result2 && testCases2[i][2] == 1) || (!result2 && testCases2[i][2] == 0)) {
					System.out.println(" ... passed!");
				}
				else {
					System.out.println(" ... failed.");
					numFailures++;
				}
			}
			if(numFailures == 0) {
				System.out.println("All isOpen tests passed!\n");
			}
			else {
				System.out.println(numFailures + " isOpen tests failed.\n");
			}
			
			System.out.println("Testing isFull...");
			testP.open(4,4);
			// The first two elements are the index, the third is 1 in it's open, or 0 if not.
			int[][] testCases3 = {
					{2,2,1},
					{3,4,0},
					{1,2,1},
					{8,1,0},
					{1,2,1},
					{4,4,0}
			};
			numFailures = 0;
			for (int i = 0; i < testCases3.length; i++) {
				result2 = testP.isFull(testCases3[i][0], testCases3[i][1]);
				System.out.print("Test Case: (" + testCases3[i][0] + ", " + testCases3[i][1] + ") = " + result2);
				if( (result2 && testCases3[i][2] == 1) || (!result2 && testCases3[i][2] == 0)) {
					System.out.println(" ... passed!");
				}
				else {
					System.out.println(" ... failed.");
					numFailures++;
				}
			}
			if(numFailures == 0) {
				System.out.println("All isFull tests passed!\n");
			}
			else {
				System.out.println(numFailures + " isFull tests failed.\n");
			}
			
			
			
			
			
		}
		
		
	}
}
