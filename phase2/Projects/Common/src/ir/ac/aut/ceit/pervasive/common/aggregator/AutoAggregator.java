
package ir.ac.aut.ceit.pervasive.common.aggregator;

import android.content.Context;
import android.os.Handler;

import ir.ac.aut.ceit.pervasive.common.Classifier;
import ir.ac.aut.ceit.pervasive.common.accel.AccelReader;
import ir.ac.aut.ceit.pervasive.common.accel.Sampler;

import java.util.Map.Entry;
import java.util.Set;


/**
 * An {@link Aggregator} which automatically samples data from an
 * {@link AccelReader} using a {@link Sampler}, and then classifies it using
 * a {@link Classifier}.
 * 
 * @author Atefeh Zareh
 */
public class AutoAggregator extends Aggregator implements Runnable {
    
    protected final Sampler sampler;
    protected final Classifier classifier;
    protected final Runnable callback;

    public AutoAggregator(final Context context, final Handler handler,
            final AccelReader reader, final Set<Entry<Float[], String>> model,
            final Runnable callback) {
        this.sampler = new Sampler(handler, reader, this);
        this.classifier = new Classifier(model);
        this.callback = callback;
    }

    /** {@inheritDoc} */
    public void run() {
        addClassification(classifier.classify(sampler.getData()));
        callback.run();
    }

    public void stop() {
        sampler.stop();
    }

    public void start() {
        sampler.start();
    }

}
