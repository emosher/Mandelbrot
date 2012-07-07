package mandelbrot;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/** MandelbrotRenderer.java
 * The thread class that will do the work of making images of the Mandelbrot set.
 * Much of the computation is or is based on Dr. Matt Lang's code.
 * @author Eric Mosher, Dr. Matt Lang
 */
public class MandelbrotRenderer implements Runnable {

    // The size of all images
    private int width = 1920;
    private int height = 1080;

    // The number of maximum iterations
    private int maxIterations;

    // Divergence radius
    private double radius = 2;
    private double divergence = radius * radius;

    // Offsets between image[0][0] and center point
    private int xOffset = -(width - 1)/2;
    private int yOffset = (height - 1)/2;

    private int black = 0;

    private MbrotParameter[] mbrots;

    private ColorScheme color;

    /**
     * This class needs a list of parameters to iterate over, this is taken as
     * an array of MbrotParameters.
     * @param imgs the images to produce
     * @param color the color scheme to use
     */
    public MandelbrotRenderer(MbrotParameter[] imgs, ColorScheme color, 
            int maxIterations) {
        mbrots = imgs;
        this.color = color;
        this.maxIterations = maxIterations;
    }

    /**
     * This class will be used as a thread, so we must use run() to do our work.
     */
    public void run() {
        int iteration, point;
        double a, b, aOld, x, y;
        int[] colorPalette = new int[maxIterations];
        // Fill the color palette
        for (int i = 0; i < maxIterations; i++) {
            colorPalette[i] = color.getColor(i);
        }

        // For each element in the list of MbrotParameters...
        for (MbrotParameter currMbrot : mbrots){
            // Do the work to create each image
            // Create a new image
            BufferedImage img = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            File out = new File(currMbrot.fName);

            // For each pixel
            for (int r = 0; r < height; r++)
            {
                for (int c = 0; c < width; c++)
                {
                    // Calculate the point the pixel actually represents
                    x = currMbrot.xCenter + (xOffset + c)
                            / currMbrot.resolution;
                    y = currMbrot.yCenter + (yOffset - r)
                            / currMbrot.resolution;

                    iteration = 0;
                    a = x;
                    b = y;

                    // While the point is within the divergence radius and
                    // the iteration bound
                    while (a*a + b*b <= divergence && iteration < maxIterations)
                    {
                        // Calculate the sequence
                        aOld = a;
                        a = a*a - b*b + x;

                        b = 2*aOld*b + y;
                        iteration ++;
                    }

                    /*
                     * If the point looks as if it will diverge, color it black.
                     * Otherwise, give it a color from our color palette.
                     */
                    if (iteration == maxIterations)
                        point = black;
                    else {
                        point = colorPalette[iteration];
                    }
                    // Set the color of the pixel
                    img.setRGB(c, r, point);
                }
            }

            // Write the image
            try {
                ImageIO.write(img, "png", out);
            }
            catch (Exception e) {
                System.out.println("Oops.");
            }
        }
    }
}
