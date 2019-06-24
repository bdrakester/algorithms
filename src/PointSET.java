/**
 * The mutable data type PointSET represents a set of points in the unit square.
 *  
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.TreeSet;
import java.util.ArrayList;

public class PointSET {
    private TreeSet<Point2D> points;
	
	/**
	 * Construct an empty set of points. 
	 */
    public PointSET() {
        points = new TreeSet<>();
    }
    
    /**
     * Is the set emtpy?
     * @return
     */
    public boolean isEmpty() {
    	return points.isEmpty();
    }
    
    /**
     * The number of points in the set.
     * @return
     */
    public int size() {
    	return points.size();
    }

    /**
     * Add the point to the set (if it is not already in the set).
     * @param p
     */
    public void insert(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException("Parameter cannot be null.");
        }
        
    	points.add(p);
    }
    
    /**
     * Does the set contain point p? 
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException("Parameter cannot be null.");
        }
        return points.contains(p);	 
    }
    
    /**
     * Draw all points to standard draw. 
     */
    public void draw() {
    	for (Point2D point : points ) {
    	    point.draw();
    	}
    }
   
    /**
     * All points that are inside the rectangle (or on the boundary) 
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new java.lang.IllegalArgumentException("Parameter cannot be null.");
        }
        
        ArrayList<Point2D> insidePoints = new ArrayList<>();
        
        for (Point2D point : points) {
            if (rect.contains(point)) {
                insidePoints.add(point);
            }
        }
        
    	return insidePoints;
    }
    
    /**
     * A nearest neighbor in the set to point p; null if the set is empty.
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException("Parameter cannot be null.");
        }
        
        if (this.isEmpty()) {
            return null;
        }
        
        double minDist = Double.POSITIVE_INFINITY;
        Point2D nearest = null;
        for (Point2D point : points) {
            if (point.equals(p)) continue;
            if (p.distanceTo(point) < minDist) {
                nearest = point;
                minDist = p.distanceTo(point);
            }
        }
        
        return nearest;
    }

    /**
     * Main method for unit testing.
     * @param args
     */
    public static void main(String[] args) {
        PointSET testSet = new PointSET();
        
        // Draw all the test points in blue
        testSet.insert(new Point2D(0.5,0.5));
        testSet.insert(new Point2D(0.25,0.25));
        testSet.insert(new Point2D(0.99,0.99));
        testSet.insert(new Point2D(0.99,0.2));
        testSet.insert(new Point2D(0.33,0.33));
        testSet.insert(new Point2D(0.66,0.33));
	    StdDraw.setPenRadius(0.01);
	    StdDraw.setPenColor(StdDraw.BLUE);
	    testSet.draw();
	    StdDraw.pause(2000);
	    
	    // Draw the rectangle in green
	    RectHV rect = new RectHV(0.2,0.2,0.6,0.6);
	    StdDraw.setPenColor(StdDraw.GREEN);
	    rect.draw();
	    StdDraw.pause(2000);
	    
	    // Test the range method by redrawing points returned in green
	    for (Point2D p : testSet.range(rect)) {
	        p.draw();
	    }
	    StdDraw.pause(4000);
	    
	    // Erase the rectangle
	    StdDraw.setPenColor(StdDraw.WHITE);
	    rect.draw();
	    
	    // Re-draw all test points in blue
	    StdDraw.setPenColor(StdDraw.BLUE);
	    testSet.draw();
	    
	    // Test nearest method by drawing a new point and nearest in green
	    StdDraw.setPenColor(StdDraw.GREEN);
	    Point2D p = new Point2D(0.20,0.25);
	    p.draw();
	    StdDraw.pause(2000);
	    testSet.nearest(p).draw();
	    StdDraw.pause(2000);
	    
	    Point2D p2 = new Point2D(0.68,0.4);
	    p2.draw();
	    StdDraw.pause(2000);
	    testSet.nearest(p2).draw();
	    
   }
}