package mandelbrot;

/**
 * This class will be the parameters for the MandelbrotRenderer class.  This
 * will wrap the data that we will need for each picture.  So we can think of
 * one instance of this class as being a picture BEFORE it is rendered.
 * @author Eric Mosher
 */
public class MbrotParameter {
    // The name of the image
    public String fName;
    // The resolution
    public double resolution;
    // The center of the picture
    public double xCenter;
    public double yCenter;

    /**
     * Construct an outline of a picture, to use for rendering.
     * @param fName the name of the file
     * @param resolution the resolution of this image
     * @param xCenter the x value of the center
     * @param yCenter the y value of the center
     */
    public MbrotParameter(String fName, double resolution, double xCenter,
            double yCenter) {
        this.fName = fName;
        this.resolution = resolution;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
    }

}
