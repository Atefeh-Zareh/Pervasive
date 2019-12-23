
package ir.ac.aut.ceit.pervasive.common;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Extracts basic features and applies a K-Nearest Network algorithm to an
 * array of data in order to determine the classification. The data consists
 * of two interleaved data sets, and each set has two features extracted -
 * the range and the mean.
 * 
 * @author Atefeh Zareh
 */
public class Classifier {

    private final Set<Map.Entry<Float[], String>> model;

    public Classifier(final Set<Entry<Float[], String>> model) {
        this.model = model;
    }

    public String classify(final float[] data) {
        final float oddTotal = data[5], evenTotal = data[2];
        final float oddMin = data[3], oddMax = data[4];
        final float evenMin = data[0], evenMax = data[1];

        final float[] points = {
            Math.abs(evenTotal / 128),
            Math.abs(oddTotal / 128),
            evenMax - evenMin,
            oddMax - oddMin
        };

        float bestDistance = Float.MAX_VALUE;
        String bestActivity = "UNCLASSIFIED/UNKNOWN";

        for (Map.Entry<Float[], String> entry : model) {
            float distance = 0;

            for (int i = 0; i < points.length; i++) {
                distance += Math.pow(points[i] - entry.getKey()[i], 2);
            }

            if (distance < bestDistance) {
                bestDistance = distance;
                bestActivity = entry.getValue();
            }
        }

        return bestActivity;
    }
}
