/**
 * The PercolationStats class is used to estimate the percolation 
 * threshold via Monte Carlo simulation.
 *  
 * @author Brian Drake
 * 
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double[] sitesOpen; // The fraction of sites open for each trial.
	private Percolation perc; // The percolation model
	private int trials;
	
	/**
	 * Constructor, performs trials / independent experiments on an n-by-n grid 
	 * @param n dimensions of the grid, n-by-n.
	 * @param trials number of independent experiments to peform.
	 */
	public PercolationStats(int n, int trials) {
		if ( n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("n or trials value must be > 0.");
		}
		this.trials = trials;
		sitesOpen = new double[trials];
		int randRow, randCol;
		for (int i = 0; i < trials; i++) {
			//System.out.println("Trial # " + i);
			perc = new Percolation(n);
			while( !perc.percolates() ) {
				randRow = StdRandom.uniform(1,n+1);
				randCol = StdRandom.uniform(1,n+1);
				perc.open(randRow, randCol);
				//System.out.println("Opened " + randRow + " , " + randCol);
			}
			sitesOpen[i] = (double)perc.numberOfOpenSites() / (double)(n*n);
		}
		
	}

	/**
	 * Calculates the sample mean percolation threshold.
	 * @return the sample mean percolation threshold.
	 */
	public double mean() {
		return StdStats.mean(sitesOpen);
	}
	
	/**
	 * Calculates the sample standard deviation of percolation threshold.
	 * @return the sample standard deviation of percolation threshold.
	 */
	public double stddev() {
		return StdStats.stddev(sitesOpen);
	}
	 
	/**
	 * Calculates the low endpoint of 95% confidence interval.
	 * @return the low endpoint of 95% confidence interval.
	 */
	public double confidenceLo() {
		return mean() - (1.96 / Math.sqrt((double)trials));
	}
	/**
	 * Calculates the high endpoint of 95% confidence interval
	 * @return high endpoint of 95% confidence interval.
	 */
	public double confidenceHi() {
		return mean() + (1.96 / Math.sqrt((double)trials));
	}
	
	public static void main(String[] args) {
		int n, T;
		if ( args.length != 2) {
			n = 10;
			T = 5;
		}
		else {
			n = Integer.valueOf(args[0]);
			T = Integer.valueOf(args[1]);
		}
		System.out.println("Welcome to Percolation stats.");
		System.out.println("Running " + T + " trials.");
		System.out.println("Grid size = " + n + "-by-" + n);
		
		PercolationStats perc = new PercolationStats(n,T);
		System.out.println("mean = " + perc.mean());
		System.out.println("stddev = " + perc.stddev());
		System.out.println("95% confidence interval = [ " + perc.confidenceLo()  
				+ " , " + perc.confidenceHi() + " ]");
	}
}
