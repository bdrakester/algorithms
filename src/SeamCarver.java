/**
 * The SeamCarver class implements the Seam Carving for content aware 
 * image resizing algorithm.
 * 
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {
    private final Picture picture;
    
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
        int blue = belowPixel.getBlue() - belowPixel.getBlue();
        
        return (red * red) + (green * green) + (blue * blue);
        
    }

    /**
     * sequence of indices for horizontal seam
     * @return
     */
    public int[] findHorizontalSeam() {
        return null;
    }

    /**
     * sequence of indices for vertical seam
     * @return
     */
    public int[] findVerticalSeam() {
        // Create 2d array of pixel energies
 
        return null;
    }

    /**
     * remove horizontal seam from current picture
     * @param seam
     */
    public void removeHorizontalSeam(int[] seam) {
        
    }

    /**
     * remove vertical seam from current picture
     * @param seam
     */
    public void removeVerticalSeam(int[] seam) {
        
    }

    /**
     * unit testing (optional)
     * @param args
     */
    public static void main(String[] args) {
        Picture picture = new Picture(args[0]);
        
        SeamCarver sc = new SeamCarver(picture);
        
        System.out.println(picture.toString());
    }
}
