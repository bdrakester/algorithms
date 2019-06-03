/**
 * The FastCollinearPoints class finds all the line segments containing 4 or
 * more points.
 * 
 * @author Brian Drake
 *
 */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
	private ArrayList<LineSegment> lineSegments;
	private Point[] slopeOrdered;
	
	/**
	 * Finds all line segments containing 4 or more points
	 * @param points
	 */
	public FastCollinearPoints(Point[] points) {
		if (points == null) {
			throw new java.lang.IllegalArgumentException();
		}
		
		lineSegments = new ArrayList<LineSegment>();
		
		// Sort the points by their natural order
		Arrays.sort(points);
		
		// Create a duplicate array of the points for sorting by slope
		slopeOrdered = new Point[points.length];
		for (int i = 0; i < slopeOrdered.length; i++) {
			if(points[i] == null || (i > 0 && points[i].compareTo(points[i-1]) == 0)) {
				throw new java.lang.IllegalArgumentException();
			}
			
			slopeOrdered[i] = points[i];
		}
		// DEBUG
		System.out.println("points: ");
		for (Point point : points) {
			System.out.print(point);
		}
		System.out.println("\nslopeOrered: ");
		for (Point point : slopeOrdered) {
			System.out.print(point);
		}
		System.out.println();
		
		// END DEBUG
		
		// For every point current
		for (int p = 0; p < points.length; p++) {
			// DEBUG
			System.out.println("p = " + p + " slopeOrdered[p] = " + slopeOrdered[p]);
			// END DEBUG
			
			// Sort the remaining points by their slope relative to p 
			Arrays.sort(slopeOrdered, p+1, slopeOrdered.length, points[p].slopeOrder());
			
			/* Check if any 3 (or more) adajcent points in the sorted order
			 * have equal slopes with respect to p.  
			 */
			int r;
			for (int q = p+1; q < slopeOrdered.length - 1; q++) {
				r = q + 1;
				while(r < slopeOrdered.length && points[p].slopeTo(points[q]) == points[p].slopeTo(points[r])) {
					r++;
				}
				// If 3 or more adjacent points have equal slope
				if (r > q + 2) {
					lineSegments.add(new LineSegment(points[p], points[r]));
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
		// TODO Auto-generated method stub

	}

}
