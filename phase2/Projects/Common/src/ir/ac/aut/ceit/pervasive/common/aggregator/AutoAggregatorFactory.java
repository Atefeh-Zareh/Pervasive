

package ir.ac.aut.ceit.pervasive.common.aggregator;

import android.content.Context;
import android.os.Handler;

import ir.ac.aut.ceit.pervasive.common.BaseFactory;
import ir.ac.aut.ceit.pervasive.common.accel.AccelReader;

import java.util.Map.Entry;
import java.util.Set;


/**
 * Creates an {@link AutoAggregator}.
 * 
 * @author Atefeh Zareh
 */
public class AutoAggregatorFactory extends BaseFactory {

    public AutoAggregator getAutoAggregator(final Context context, final Handler handler,
            final AccelReader reader, final Set<Entry<Float[], String>> model,
            final Runnable callback) {
        if (shouldUseFake()) {
            return new FakeAutoAggregator(context, handler, reader, model, callback);
        } else {
            return new AutoAggregator(context, handler, reader, model, callback);
        }
    }

}
