

package ir.ac.aut.ceit.pervasive.common.geo;

/**
 * A dummy location monitor for use in emulator testing.
 * 
 * @author Atefeh Zareh
 */
public class FakeLocationMonitor implements LocationMonitor {
   
    private int count = 0;
    private double[][] points = new double[][] {
        {51.481386d, -0.084667d}, // Place 1
        {51.481386d, -0.084667d},
        {51.481386d, -0.084667d},

        {51.491386d, -0.082667d},
        {51.501386d, -0.081667d},

        {51.517676d, -0.07997d}, // Place 2
        {51.517676d, -0.07997d},
        {51.517676d, -0.07997d},

        {51.514386d, -0.08300d},
        {51.511386d, -0.08500d},
        {51.511386d, -0.09000d},
        {51.511386d, -0.10000d},
        {51.511386d, -0.12000d},
        {51.511386d, -0.14000d},
        {51.511386d, -0.17000d},

        {51.498725d, -0.17950d}, // Place 3
        {51.498725d, -0.17950d},
        {51.498725d, -0.17950d},

        {51.481386d, -0.084667d}, // Place 1
        {51.481386d, -0.084667d},
        {51.481386d, -0.084667d},

        {51.491386d, -0.082667d},
        {51.501386d, -0.081667d},

        {51.517676d, -0.07997d}, // Place 2
        {51.517676d, -0.07997d},
        {51.517676d, -0.07997d},

        {51.514386d, -0.08300d},
        {51.511386d, -0.08500d},
        {51.511386d, -0.09000d},
        {51.511386d, -0.10000d},
        {51.511386d, -0.12000d},
        {51.511386d, -0.14000d},
        {51.511386d, -0.17000d},

        {51.481386d, -0.084667d}, // Place 1
        {51.481386d, -0.084667d},
        {51.481386d, -0.084667d},
    };

    public float getAccuracy() {
        return 0f;
    }

    public double getLat() {
        return points[count++ % points.length][0];
    }

    public double getLon() {
        return points[count % points.length][1];
    }

}
