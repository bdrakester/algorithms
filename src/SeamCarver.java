/**
 * The SeamCarver class implements the Seam Carving for content aware 
 * image resizing algorithm.
 * 
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;
import java.util.ArrayList;

public class SeamCarver {
    private Picture picture;
    private double[][] energy;
    private double[][] distTo;
    private Pixel[][] edgeTo;
    
    private class Pixel {
        int x;
        int y;
        
        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
    
    /**
     * Create a seam carver object based on the given picture. 
     * @param picture the picture to be "seam carved"
     */
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }
        
        // Create a copy of the picture
        this.picture = new Picture(picture);
        
    }

    /**
     * Returns the current picture.
     * @return the current picture.
     */
    public Picture picture() {
        return picture;
        
    }

    /**
     * Returns the width of current picture.
     * @return the width of the current picture.
     */
    public int width() {
        return picture.width();
    }

    /**
     * Returns the height of current picture.
     * @return the height of the current picture.
     */
    public int height() {
        return picture.height();
    }

    /**
     * Energy of pixel at column x and row y.
     * @param x the column of the pixel.
     * @param y the row of the pixel.
     * @return the energy of the pixel at x,y.
     */
    public double energy(int x, int y) {
        if (x < 0 || x > width() - 1) {
            throw new IllegalArgumentException("x argument outside range.");
        }
        if (y < 0 || y > height() - 1) {
            throw new IllegalArgumentException("y argument outside range.");
        }
        // If the pixel is on the border, energy is 1000
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 1000;
        }
        
        int xGrad = xGradientSquared(x,y);
        int yGrad = yGradientSquared(x,y);
        
        return Math.sqrt((double) (xGrad + yGrad));
    }
    
    /**
     * Compute the square of the x-gradient of the pixel at x,y.
     * @param x the column of the pixel.
     * @param y the row of the pixel.
     * @return the square of the x-gradient of the pixel at x,y.
     */
    private int xGradientSquared(int x, int y) {
        // The differences in R,G,B between (x+1,y) and (x-1,y)
        Color rightPixel = picture.get(x + 1, y);
        Color leftPixel = picture.get(x - 1, y);
        
        int red = rightPixel.getRed() - leftPixel.getRed();
        int green = rightPixel.getGreen() - leftPixel.getGreen();
        int blue = rightPixel.getBlue() - leftPixel.getBlue();
        
        return (red * red) + (green * green) + (blue * blue);
    }
    
    /**
     * Compute the square of the y-gradient of the pixel at x,y.
     * @param x the column of the pixel.
     * @param y the row of the pixel.
     * @return the square of the y-gradient of the pixel at x,y.
     */
    private int yGradientSquared(int x, int y) {
        // The differenes in R,G,b between (x,y+1) and (x,y-1) 
        Color belowPixel = picture.get(x, y + 1);
        Color abovePixel = picture.get(x, y - 1);
        
        int red = belowPixel.getRed() - abovePixel.getRed();
        int green = belowPixel.getGreen() - abovePixel.getGreen();
        int blue = belowPixel.getBlue() - abovePixel.getBlue();
        
        return (red * red) + (green * green) + (blue * blue);
        
    }

    /**
     * Sequence of indices for horizontal seam.  Tranpose the picture,
     * call find VerticalSeam() then tranpose it back.
     * @return an array of length width of the picture such that entry y 
     *  is the row number of the of the pixel to be removed from column y 
     *  of the image.
     */
    public int[] findHorizontalSeam() {
        // Tranpose the picture
        tranpose();
        
        // Find vertical seam in transposed picture
        int[] path = findVerticalSeam();
        
        // Set picture back to original
        tranpose();
       
        return path;
    }
    
    /**
     * Tranpose the picture.  Each [col, row] element of the transposed
     * picture gets the value of the [row, col] element of the original.
     */
    private void tranpose() {
        // Tranpose the picture
        Picture original = this.picture;
        this.picture = new Picture(height(), width());

        // Each [col, row] element of the transposed picture gets the value of 
        // the [row, col] element of the original one.
        for (int row = 0; row < width(); row++) {
            for (int col = 0; col < height(); col++) {
                this.picture.set(row, col, original.get(col, row));    
            }
        }       
    }
    
    /**
     * Retruns the sequence of indices for vertical seam.  
     * Considers pixels in topological order by starting from top row
     * moving left to right.
     * @return an array of length height of the picture such that entry y 
     *  is the column number of the of the pixel to be removed from row y 
     *  of the image.
     */
    public int[] findVerticalSeam() {
        // Create 2d array of pixel energies
        energy = new double[width()][height()];
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                energy[col][row] = this.energy(col, row);
            }
        }
        
        // Initialize distTo and edgeTo arrays.
        distTo = new double[width()][height()];
        edgeTo = new Pixel[width()][height()];
        for (int col = 0; col < width(); col++) {
            distTo[col][0] = 1000;
            edgeTo[col][0] = null;
        }
        for (int row = 1; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                distTo[col][row] = Double.POSITIVE_INFINITY; 
            }
        }
        
        // Consider pixels in topological order
        for (int row = 0; row < height(); row ++) {
            for (int col = 0; col < width(); col++) {
                // Relax each 'edge' to neighboring pixel
                for (Pixel p : adjacent(col, row)) {
                    // Relax it
                    relax(new Pixel(col,row), p);
                }
            }
        }
        
        // Find smallest distTo in the last row
        int lastCol = 0;
        double shortest = Double.POSITIVE_INFINITY;
        for (int col = 0; col < width(); col++) {
            if (distTo[col][height() - 1] < shortest ) {
                lastCol = col;
                shortest = distTo[col][height() - 1];
            }
        }
        
        // Return an array reconcstructed using edgeTo array
        int[] path = new int[height()];
        path[height() - 1] = lastCol;
        for (int row = height() - 2; row >= 0; row--) {
            path[row] = edgeTo[path[row+1]][row+1].x;    
        }
        
        return path;
    }
    
    /**
     * Return the Pixels reachable from the pixel at (x, y) in the Picture.
     * @param x the x coordinate of the pixel.
     * @param y the y coordiante of the pixel.
     * @return an iterable of Pixels reachable from the pixel at (x, y). 
     */
    private Iterable<Pixel> adjacent(int x, int y) {
        if (x < 0 || x > width() - 1) {
            throw new IllegalArgumentException("x argument outside range.");
        }
        if (y < 0 || y > height() - 1) {
            throw new IllegalArgumentException("y argument outside range.");
        }
        
        ArrayList<Pixel> pixels = new ArrayList<>();
        
        // If it's the bottom row
        if (y == height() - 1) {
            return pixels;
        }
  
 
        if (x > 0) {
            pixels.add(new Pixel(x - 1, y + 1));
        }
 
        pixels.add(new Pixel(x, y + 1));
        
        if (x < width() - 1) {
            pixels.add(new Pixel(x + 1, y + 1));
        }
        
        return pixels;
        
    }
    
    /**
     * Relax the edge from Pixel source to dest. If the current
     * shortest path to dest is larger than the distance to source
     * pixel plus dest's energy, update the distTo and edgeTo arrays
     * with new shortest path.
     * @param source the source pixel
     * @param dest the destination pixel
     */
    private void relax(Pixel source, Pixel dest) {
        if (distTo[dest.x][dest.y] > distTo[source.x][source.y] + energy[dest.x][dest.y]) {
            distTo[dest.x][dest.y] = distTo[source.x][source.y] + energy[dest.x][dest.y];
            edgeTo[dest.x][dest.y] = source;
        }
    }
    
    /**
     * Remove horizontal seam from current picture. 
     * @param seam n array of length width of the picture such that entry y 
     *  is the row number of the of the pixel to be removed from column y 
     *  of the image.
     */
    public void removeHorizontalSeam(int[] seam) {
        // Create a new picture with same width and height minus 1 pixel.
        Picture newPicture = new Picture(width(), height() - 1);
        
        // Iterate over each column copying pixels to the new picture
        for (int col = 0; col < width(); col++) {
            // For each row before the seam
            for (int row = 0; row < seam[col]; row++) {
                newPicture.set(col, row, picture.get(col, row));
            }
            // For each row after the seam
            for (int row = seam[col]; row < newPicture.height(); row++) {
                newPicture.set(col, row, picture.get(col, row+1));
            }
        }
        
        picture = newPicture;
    }

    /**
     * Remove a vertical seam from current picture.
     * @param seam an array of length height of the picture such that entry y 
     *  is the column number of the of the pixel to be removed from row y 
     *  of the image. 
     */
    public void removeVerticalSeam(int[] seam) {
        // Create a new picture with same height and width minus 1 pixel.
        Picture newPicture = new Picture(width() - 1, height());
        
        // Iterate over each row copying pixels to new picture
        for (int row = 0; row < height(); row++) {
            // For each column before the seam
            for(int col = 0; col < seam[row]; col++) {
                newPicture.set(col, row, picture.get(col, row));
            }
            // For each column after the seam
            for (int col = seam[row]; col < newPicture.width(); col++) {
                newPicture.set(col, row, picture.get(col+1, row));
            }
        }
        picture = newPicture;
    }

    /**
     * unit testing (optional)
     * @param args
     */
    public static void main(String[] args) {
        Picture picture = new Picture(args[0]);
        
        SeamCarver sc = new SeamCarver(picture);
        
        System.out.println(picture.toString());
        //sc.findVerticalSeam();
        
        System.out.println("\nTesting adjacent method...\n");
        
        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++) {
                System.out.print("(" + col + ", " + row + ").adjacent = ");
                for (Pixel p : sc.adjacent(col, row)) {
                    System.out.print(p.toString() + " ");
                }
                System.out.println();
            }
        }
        
        sc.findVerticalSeam();
        
    }
}
