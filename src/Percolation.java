/**
 * The Percolation class estimates the value of the percolation threshold via Monte Carlo simulation.
 *  
 * @author Brian Drake
 * 
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private int[][] id;
	
	/**
	 * Constructor - Create an n-by-n grid, with all sites blocked.
	 * @param n dimension of the square grid, it will be n-by-n. 
	 */
	public Percolation(int n) {
		id = new int[n][n];
		// Set the id of each object to it self.
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				id[i][j] = n*i + j;
			}
		}
		
	}
	
	/**
	 * Opens site (row, col) if it is not already open.
	 * @param row
	 * @param col
	 */
	public void open(int row, int col) {
		
	}
	
	/**
	 * Is site (row, col) open?  
	 * @param row
	 * @param col
	 * @return true if the site is open
	 */
	public boolean isOpen(int row, int col) {
		return false;
	}
	
	/**
	 * Is site (row, col) full? 
	 * @param row
	 * @param col
	 * @return true if the site is full.
	 */
	public boolean isFull(int row, int col) {
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
		return false;
	}

	public static void main(String[] args) {
		Percolation p = new Percolation(5);
		
		System.out.println("Hello from Percolation!");
		
	}

}
