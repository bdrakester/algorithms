/**
 * The mutable data type KdTree represents a set of points in the unit square,
 * implemented as a 2d-tree
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class KdTree {
    private Node root;
    private int size;
    
    private static class Node {
        private Point2D point;
        private RectHV rect;
        private Node leftBottom;
        private Node rightTop;
        
        public Node(Point2D point) {
            this.point = point;
        }
    }
    
    /**
     * Construct an empty set of points 
     */
    public KdTree() {
        root = null;
        size = 0;
    }
    
    /**
     * Is the set empty? 
     * @return
     */
    public boolean isEmpty() {
        if (root == null) {    
            return true;
        }
        return false;
    }
    
    /**
     * Number of points in the set 
     * @return
     */
    public int size() {
        return size;
    }
    
    /**
     * Add the point to the set (if it is not already in the set)
     * @param p
     */
    public void insert(Point2D p) {
        // If this is the first node, set as root and rectangle is unit square
        if (size == 0) {
            root = new Node(p);
            root.rect = new RectHV(0,0,1,1);
        }
        else {
            root = insert(root, 0, p); 
        }
        size++;
    }
    
    private Node insert(Node node, int depth, Point2D p) {
        if (node == null) return new Node(p);
        
        // If an even depth, compare x coordinates
        if (depth % 2 == 0) {
            // If x coordinate is less, go left
            if (p.x() < node.point.x()) {
                node.leftBottom = insert(node.leftBottom,depth+1,p);
                // Setup rectangle of node below if needed 
                if (node.leftBottom.rect == null) {
                    //node.leftBottom.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.point.y());
                    node.leftBottom.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.point.x(), node.rect.ymax());
                }
            }
            // Else, go right
            else {
                node.rightTop = insert(node.rightTop,depth+1,p);
                // Setup rectangle of node below if needed
                if(node.rightTop.rect == null) {
                    //node.rightTop.rect = new RectHV(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.rect.ymax());
                    node.rightTop.rect = new RectHV(node.point.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
                }
            }
        }
        // Else an odd depth, compare y coordiantes
        else {
            // If y coordinate is less, go left
            if (p.y() < node.point.y()) {
                node.leftBottom = insert(node.leftBottom,depth+1,p);
                // Setup rectangle of node below if needed
                if (node.leftBottom.rect == null) {
                    //node.leftBottom.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.point.x(), node.rect.ymax());
                    node.leftBottom.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.point.y());
                }
            }
            // Else, go right
            else {
                node.rightTop = insert(node.rightTop,depth+1,p);
                // Setup rectangle of node below if neede
                if (node.rightTop.rect == null) {
                    //node.rightTop.rect = new RectHV(node.point.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
                    node.rightTop.rect = new RectHV(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.rect.ymax());
                }
            }
        }
        
        return node;
    }
    
    /**
     * Does the set contain point p? 
     * @param p the point 
     * @return true if the KdTree contains the point, else false.
     */
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException("Parameter p cannot be null.");
        }
        return contains(root, 0, p);
    }
    
    /**
     * Does the sub-tree rooted at node contain point p?
     * @param node root of the sub-tree being searched
     * @param depth the depth of node in the KdTree.
     * @param p the point.
     * @return true if the sub-tree rooted at node contains p, else false.
     */
    private boolean contains(Node node, int depth, Point2D p) {
        if (node == null) {
            return false;
        }

        if (node.point.equals(p)) {
            return true;
        }

        // If an even depth, use x-coor to determine sub-tree to search
        if (depth % 2 == 0) {
           if (p.x() < node.point.x()) {
               return contains(node.leftBottom,depth+1,p);
           }
           else {
               return contains(node.rightTop,depth+1,p);
           }
        }
        // Else an odd depth, use y-coor do determine sub-tree to search
        else {
            if (p.y() < node.point.y()) {
                return contains(node.leftBottom,depth+1,p);
            }
            else {
                return contains(node.rightTop,depth+1,p);
            }
        }
    }
    
    /**
     * Draw all points to standard draw 
     */
    public void draw() {
        draw(root, 0);
    }
    
    private void draw(Node node, int depth) {
        if(node == null) return;
        
        System.out.println("Drawing point: " + node.point.toString());
        System.out.println("  with rect = (" + node.rect.xmin() + ", " + node.rect.ymin() + ") , (" + node.rect.xmax() + ", " + node.rect.ymax() + ")");
        // Draw the point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.point.draw();
        StdDraw.setPenRadius();
        // Draw the dividing line from rectangle
        if (depth % 2 == 0) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
        }
        StdDraw.pause(2000);

        draw(node.leftBottom,depth+1);
        draw(node.rightTop, depth+1);
       
    }
    
    /**
     * All points that are inside the rectangle (or on the boundary) 
     * @param rect the query rectangle.
     * @return an interable of points inside the query rectangle.
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new java.lang.IllegalArgumentException("Parameter cannot be null.");
        }
        
        ArrayList<Point2D> rangePoints = new ArrayList<>();
        
        range(root, rect, 0, rangePoints);
        
        return rangePoints;
        
    }
    
    /**
     * Adds all points in the sub-tree rooted at node that are inside the 
     * rectangle (or on the boundary) of rect to rangePoints.
     * @param node the root of the sub-tree.
     * @param rect the query rectangle.
     * @param depth the depth of this node in the tree.
     * @param rangePoints the list storing all previously found points.
     */
    private void range(Node node, RectHV rect, int depth, ArrayList<Point2D> rangePoints) {
        if (node == null) return;
        if (rect.intersects(node.rect)) {
            if (rect.contains(node.point)) {
                rangePoints.add(node.point);
            }
            range(node.leftBottom, rect, depth+1, rangePoints);
            range(node.rightTop, rect, depth+1, rangePoints);
        }
    }
    
    /**
     * A nearest neighbor in the set to point p; null if the set is empty 
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException("Parameter cannot be null.");
        }
        
        return nearest(root, 0, p, root.point);
    }
    
    private Point2D nearest(Node node, int depth, Point2D queryPoint, Point2D nearest) {
        if (node == null) return nearest;
        
        // If the nearest point so far is closer than node's rectangle 
        if (node.rect.distanceTo(queryPoint) > nearest.distanceTo(queryPoint)) {
            // No need to search this sub-tree
            return nearest;
        }
        
        if (node.point.distanceTo(queryPoint) < nearest.distanceTo(queryPoint))){
            nearest = node.point;
        }
        // Choose the subtree that is on the same side of the splitting line 
        // as the query point.
        // If an even depth, 
    }
    
    /**
     * Unit testing of the methods (optional) 
     * @param args
     */
    public static void main(String[] args) {
        KdTree testTree = new KdTree();
        
        testTree.insert(new Point2D(0.7,0.2));
        testTree.insert(new Point2D(0.5,0.4));
        testTree.insert(new Point2D(0.2,0.3));
        testTree.insert(new Point2D(0.4,0.7));
        testTree.insert(new Point2D(0.9,0.6));
        
        testTree.draw();
        
        for (Point2D p : testTree.range(new RectHV(0.6,0.1,0.8,0.3))) {
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.setPenRadius(0.01);
            p.draw();
        }
        
    }
}
