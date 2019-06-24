/**
 * The mutable data type KdTree represents a set of points in the unit square,
 * implemented as a 2d-tree
 * 
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
        private final Point2D point;
        private RectHV rect; 
        private Node leftBottom;
        private Node rightTop;
        
        public Node(Point2D point) {
            this.point = point;
        }
    }
    
    /**
     * Construct an empty set of points. 
     */
    public KdTree() {
        root = null;
        size = 0;
    }
    
    /**
     * Is the set empty? 
     * @return true if the KdTree is empty, otherwise false.
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * Number of points in the KdTree.
     * @return the number of points in the KdTree.
     */
    public int size() {
        return size;
    }
    
    /**
     * Add the point to the KdTree (if it is not already in the KdTree).
     * @param p
     */
    public void insert(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException("Parameter p cannot be null.");
        }
        
        // If this is the first node, set as root and rectangle is unit square
        if (size == 0) {
            root = new Node(p);
            root.rect = new RectHV(0, 0, 1, 1);
        }
        else {
            root = insert(root, 0, p); 
        }
        size++;
    }
    
    /**
     * Add a point to the KdTree rooted at node.
     * @param node the root of the KdTree
     * @param depth the depth in the KdTree
     * @param p the point to be added
     * @return the Node added to the KdTree
     */
    private Node insert(Node node, int depth, Point2D p) {
        if (node == null) return new Node(p);
        
        // If an even depth, compare x coordinates
        if (depth % 2 == 0) {
            // If x coordinate is less, go left
            if (p.x() < node.point.x()) {
                node.leftBottom = insert(node.leftBottom, depth+1, p);
                // Setup rectangle of node below if needed 
                if (node.leftBottom.rect == null) {
                    node.leftBottom.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.point.x(), node.rect.ymax());
                }
            }
            // Else, go right
            else {
                node.rightTop = insert(node.rightTop, depth+1, p);
                // Setup rectangle of node below if needed
                if (node.rightTop.rect == null) {
                    node.rightTop.rect = new RectHV(node.point.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
                }
            }
        }
        // Else an odd depth, compare y coordiantes
        else {
            // If y coordinate is less, go left
            if (p.y() < node.point.y()) {
                node.leftBottom = insert(node.leftBottom, depth+1, p);
                // Setup rectangle of node below if needed
                if (node.leftBottom.rect == null) {
                    node.leftBottom.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.point.y());
                }
            }
            // Else, go right
            else {
                node.rightTop = insert(node.rightTop, depth+1, p);
                // Setup rectangle of node below if neede
                if (node.rightTop.rect == null) {
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
               return contains(node.leftBottom, depth+1, p);
           }
           else {
               return contains(node.rightTop, depth+1, p);
           }
        }
        // Else an odd depth, use y-coor do determine sub-tree to search
        else {
            if (p.y() < node.point.y()) {
                return contains(node.leftBottom, depth+1, p);
            }
            else {
                return contains(node.rightTop, depth+1, p);
            }
        }
    }
    
    /**
     * Draw all points to standard draw. 
     */
    public void draw() {
        draw(root, 0);
    }
    
    /**
     * Draw all points in the KdTree rooted at node to standard draw.
     * @param node the root of the KdTree
     * @param depth the depth of node in the KdTree
     */
    private void draw(Node node, int depth) {
        if (node == null) return;
        
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

        draw(node.leftBottom, depth+1);
        draw(node.rightTop, depth+1);
       
    }
    
    /**
     * All points that are inside the rectangle (or on the boundary).
     * @param rect the query rectangle.
     * @return an interable of all points inside the query rectangle.
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
     * A nearest neighbor in the KdTree to point p; null if the set is empty 
     * @param p the query point
     * @return a nearest neighbor to point p 
     */
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException("Parameter cannot be null.");
        }
        
        return nearest(root, 0, p, root.point);
    }
    
    /**
     * Returns the nearest neighbor to queryPoint in the KdTree rooted at
     * node, must be closer than nearest.
     * @param node the root of the KdTree 
     * @param depth the depth of node in the KdTree
     * @param queryPoint the query point
     * @param nearest the previously discovered nearest point 
     * @return the nearest neighbor to queryPoint
     */
    private Point2D nearest(Node node, int depth, Point2D queryPoint, Point2D nearest) {
        if (node == null) return nearest;
        
        // If the nearest point so far is closer than this node's rectangle 
        if (node.rect.distanceSquaredTo(queryPoint) > nearest.distanceSquaredTo(queryPoint)) {
            // No need to search this sub-tree
            return nearest;
        }
        
        // If current node is closer, set it as nearest
        if (node.point.distanceSquaredTo(queryPoint) < nearest.distanceSquaredTo(queryPoint)) {
            nearest = node.point;
        }
        
        // Search the subtree that is on the same side of the splitting line  
        // as the query point first.
        
        // If an even depth, use x-coor to determine sub-tree to search first
        if (depth % 2 == 0) {
            // If x-coor to the left of current node, search left sub-tree first
            if (queryPoint.x() < node.point.x()) {
                nearest = nearest(node.leftBottom, depth+1, queryPoint, nearest);
                nearest = nearest(node.rightTop, depth+1, queryPoint, nearest);
            }
            // X-coor is equal or to the right, search right sub-tree first
            else {
                nearest = nearest(node.rightTop, depth+1, queryPoint, nearest);
                nearest = nearest(node.leftBottom, depth+1, queryPoint, nearest);
            }
        }
        // Odd depth, use y-coor to determine sub-tree to search first
        else {
            // If y-coor below current node, search left sub-tree first
            if (queryPoint.y() < node.point.y()) {
                nearest = nearest(node.leftBottom, depth+1, queryPoint, nearest);
                nearest = nearest(node.rightTop, depth+1, queryPoint, nearest);
            }
            // Y-coor is equal or above, search right sub-tree first
            else {
                nearest = nearest(node.rightTop, depth+1, queryPoint, nearest);
                nearest = nearest(node.leftBottom, depth+1, queryPoint, nearest);
            }
        }
        return nearest;
    }
    
    /**
     * Unit testing of the methods (optional) 
     * @param args
     */
    public static void main(String[] args) {
        KdTree testTree = new KdTree();
        
        testTree.insert(new Point2D(0.7, 0.2));
        testTree.insert(new Point2D(0.5, 0.4));
        testTree.insert(new Point2D(0.2, 0.3));
        testTree.insert(new Point2D(0.4, 0.7));
        testTree.insert(new Point2D(0.9, 0.6));
        
        testTree.draw();
        
        for (Point2D p : testTree.range(new RectHV(0.6, 0.1, 0.8, 0.3))) {
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.setPenRadius(0.01);
            p.draw();
        }
        
    }
}
