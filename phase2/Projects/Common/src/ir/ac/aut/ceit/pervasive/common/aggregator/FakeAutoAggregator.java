
package ir.ac.aut.ceit.pervasive.common.aggregator;

import android.content.Context;
import android.os.Handler;

import ir.ac.aut.ceit.pervasive.common.accel.AccelReader;

import java.util.Map.Entry;
import java.util.Set;


/**
 * An extension of {@link AutoAggregator} which always provides pre-determined,
 * fake classifications.
 * 
 * @author Atefeh Zareh
 */
public class FakeAutoAggregator extends AutoAggregator {

    private static final String[] CLASSIFICATIONS = new String[] {
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/WALKING",
        "CLASSIFIED/WALKING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/SITTING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/WALKING",
        "CLASSIFIED/WALKING",
        "CLASSIFIED/WALKING",
        "CLASSIFIED/VEHICLE/BUS",
        "CLASSIFIED/VEHICLE/BUS",
        "CLASSIFIED/VEHICLE/BUS",
        "CLASSIFIED/VEHICLE/BUS",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/WALKING",
        "CLASSIFIED/WALKING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/SITTING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/WALKING",
        "CLASSIFIED/VEHICLE/BUS",
        "CLASSIFIED/VEHICLE/BUS",
        "CLASSIFIED/VEHICLE/BUS",
        "CLASSIFIED/VEHICLE/BUS",
        "CLASSIFIED/VEHICLE/BUS",
        "CLASSIFIED/VEHICLE/BUS",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/STANDING",
        "CLASSIFIED/IDLE/STANDING",
    };

    private int i = 0;

    public FakeAutoAggregator(final Context context, final Handler handler,
            final AccelReader reader, final Set<Entry<Float[], String>> model,
            final Runnable callback) {
        super(context, handler, reader, model, callback);
    }

    @Override
    public String getClassification() {
        return CLASSIFICATIONS[i % CLASSIFICATIONS.length];
    }

    @Override
    public void start() {
        i++;
        
        callback.run();
    }

    @Override
    public void stop() {
        // Do nothing
    }

}
