/**
 * The mutable data type PointSET represents a set of points in the unit square.
 *  
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.TreeSet;
import java.util.ArrayList;

public class PointSET {
    private final TreeSet<Point2D> points;

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
    	for (Point2D point : points) {
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
            // if (point.equals(p)) continue;
            if (p.distanceSquaredTo(point) < minDist) {
                nearest = point;
                minDist = p.distanceSquaredTo(point);
            }
        }
        
        return nearest;
    }

    /**
     * Main method for unit testing.
     * @param args
     */
    public static void main(String[] args) {
        // initialize the data structure with points from file
        String filename = args[0];
        In in = new In(filename);
        PointSET brute = new PointSET();
        
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.insert(p);
        }
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        brute.draw();
        StdDraw.pause(2000);
        StdDraw.setPenColor(StdDraw.RED);
        Point2D qp = new Point2D(0.0, 0.25);
        qp.draw();
        StdDraw.pause(2000);
        Point2D qpNear = brute.nearest(qp);
        qpNear.draw();
        
	    
   }
}