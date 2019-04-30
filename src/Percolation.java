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
	private int n;
	private int size;
	private int[] id;
	private boolean open[];
	
	/**
	 * Constructor
	 * @param n dimension of the square grid, it will be n-by-n. 
	 */
	public Percolation(int n) {
		this.n = n;
		size = n*n;
		id = new int[size];
		open = new boolean[size];
		
		// Set the id of each object to it self, set open state to false. 
		for(int i = 0; i < size; i++) {
			id[i] = i;
			open[i] = false;
		}
	}
	
	/**
	 * Convert 2-D row, col coordinates to an 1-D array index. 
	 * @param row
	 * @param col
	 * @return index in 1-D array
	 */
	private int xyTo1D(int row, int col) {
		return n*row + col;
	}
	
	/**
	 * Opens site (row, col) if it is not already open.
	 * @param row
	 * @param col
	 */
	public void open(int row, int col) {
		// UF methods: union
	
	}
	
	/**
	 * Is site (row, col) open?  
	 * @param row
	 * @param col
	 * @return true if the site is open
	 */
	public boolean isOpen(int row, int col) {
		// UF methods: 
		return false;
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
		Percolation p = new Percolation(5);
		
		System.out.println("Hello from Percolation!");
		
	}

}
