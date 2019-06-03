/**
 * The BruteCollinearPoints class examines 4 points at a time and checks
 * whether they all lie on the same line segment, returning all such line 
 * segments.
 * 
 * @author Brian Drake
 *
 */
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> lineSegments;

    /**
	 * Finds all line segments containing 4 points
	 * @param points
	 */
	public BruteCollinearPoints(Point[] points)   {
		if (points == null) {
			throw new java.lang.IllegalArgumentException("Parameter points can not be null.");
		}
		
		lineSegments = new ArrayList<LineSegment>();
		Point[] sortedPoints = new Point[points.length];
		
		
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new java.lang.IllegalArgumentException("Null point.");
			}
			sortedPoints[i] = points[i];
		}
		
		// Sort the points
		Arrays.sort(sortedPoints);
		
		/* Iterate over every combination of 4 points.
		 * Throws a java.lang.IllegalArgumentException if any point in the 
		 * array is a duplicate.
		 */
		for (int p = 0; p < sortedPoints.length - 3; p++) {
			for (int q = p+1; q < sortedPoints.length - 2; q++) {
				if (sortedPoints[q].compareTo(sortedPoints[p]) == 0) {
					throw new java.lang.IllegalArgumentException("Duplicate point.");
				}
				for (int r = q+1; r < sortedPoints.length - 1; r++) {
					if (sortedPoints[r].compareTo(sortedPoints[q]) == 0) {
						throw new java.lang.IllegalArgumentException("Duplicate point.");
					}
					for (int s = r+1; s < sortedPoints.length; s++) {
						if (sortedPoints[s].compareTo(sortedPoints[r]) == 0) {
							throw new java.lang.IllegalArgumentException("Duplicate point.");
						}
						// If the points are collinear
						if (sortedPoints[p].slopeTo(sortedPoints[q]) == sortedPoints[p].slopeTo(sortedPoints[r])) {
							if (sortedPoints[p].slopeTo(sortedPoints[q]) == sortedPoints[p].slopeTo(sortedPoints[s])) {
								// System.out.println("Adding line (p,q,r,s) = " + sortedPoints[p] + sortedPoints[q] + sortedPoints[r] + sortedPoints[s]);
								lineSegments.add(new LineSegment(sortedPoints[p], sortedPoints[s]));						
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * The number of line segments
	 * @return the number of line segments
	 */
	public int numberOfSegments() {
		return lineSegments.size();
	}
	
	/**
	 * Return the line segments
	 * @return an array containing the line segements
	 */
	public LineSegment[] segments() {
		LineSegment[] segments = new LineSegment[lineSegments.size()];
		
		for (int i = 0; i < segments.length; i++) {
			segments[i] = lineSegments.get(i);
		}
		
		return segments;
	}
	
	/**
	 * The main method for testing the BruteCollinearPoints class takes 
	 * a file name as a command line argument, prints to standard 
	 * output the line segments discovered and draws to standard draw
	 * the line segments.
	 * The first line of the file should be an integer n, followed by n pairs
	 * of integers, between 0 and 32, 767, representing n points.  
	 * 
	 * @param args command line argurments - should include a file name
	 */
	public static void main(String[] args) {
		
		// read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.setPenColor(StdDraw.BLUE);
	    StdDraw.setPenRadius(0.01);
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
		
	}
}
