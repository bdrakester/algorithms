/**
 * The FastCollinearPoints class finds all the line segments containing 4 or
 * more points.
 * 
 * @author Brian Drake
 *
 */

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> lineSegments;
    
    /**
	 * Finds all line segments containing 4 or more points
	 * @param points
	 */
	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.IllegalArgumentException();
		}
		
		lineSegments = new ArrayList<LineSegment>();
		Point[] workingPoints = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new java.lang.IllegalArgumentException("Null point.");
			}
			workingPoints[i] = points[i];
		}
		
		Point current;
		for (int p = 0; p < workingPoints.length - 1; p++) {
			// Sort the remaining points by natrual order
			Arrays.sort(workingPoints, p, workingPoints.length);
			current = workingPoints[p];
			
			// Throw exeptions if the a duplicate
			if (current.compareTo(workingPoints[p+1]) == 0) {
				throw new java.lang.IllegalArgumentException("Duplicate point.");
			}
			
			// Sort points greater than current based on slope with current
			Arrays.sort(workingPoints, p+1, workingPoints.length, current.slopeOrder());
			
			/*
			 * Find any groups of 3 or more points in the slope sorted order
			 * They should also be in natural order due to stability of 
			 * first sort.
			 */
			int r;
			for (int q = p+1; q < workingPoints.length -1; q++) {
				r = q + 1;
				while (r < workingPoints.length && current.slopeTo(workingPoints[q]) == current.slopeTo(workingPoints[r])) {
					r++;
				}
				// If there were 3 or more with the same slope
				if (r > q + 2) {
					lineSegments.add(new LineSegment(current, workingPoints[r-1]));
				}	
			}	
		}
	}
	
	/**
	 * Get the number of line segments.
	 * @return the number of line segments.
	 */
	public int numberOfSegments() {
		return lineSegments.size();
	}
	
	
	/**
	 * Get the line segments
	 * @return an array of the LineSegments
	 */
	public LineSegment[] segments() {
		LineSegment[] segments = new LineSegment[lineSegments.size()];
		
		for (int i = 0; i < segments.length; i++) {
			segments[i] = lineSegments.get(i);
		}
		
		return segments;
	}
	
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
	    // StdDraw.setPenRadius(0.01);
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
