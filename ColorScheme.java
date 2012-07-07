package mandelbrot;

/**
 * A color scheme.
 * @author Eric Mosher
 */
public class ColorScheme {
    
    // Store the maximum iterations as a number to use in the calculations 
    // of color.
    private int maxIterations;
    
    /**
     * Construct a color scheme object
     * @param maxIterations for use in calculation
     */
    public ColorScheme(int maxIterations){
        this.maxIterations = maxIterations;
    }
            
    /**
     * Get the color of a point
     * @param i the number to color
     * @return the color in this color scheme
     */
    public int getColor(int i) {
        // A color scheme
        int a = (int) (255 * ((double) i) / (maxIterations / 4));
        return //( (2*a<<16) );
        // Other options of varying qualities...
                //( (255 * (i/15)) << 16 | (255 * (i/15)) );
                //((255 * (i/20)) << 16 | 0 | 0 );
                //((255 * (i/10)) << 16 | (255 * (i/10)) << 8 | (255 * (i/10)) );
                (65536 + i*256 + i/2+128);
                //( (0) | (2*a<<16) | (a<<8) | ((a*2)<<0) );
    }

}
