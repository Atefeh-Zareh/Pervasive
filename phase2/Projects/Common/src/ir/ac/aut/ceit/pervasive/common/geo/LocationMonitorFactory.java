

package ir.ac.aut.ceit.pervasive.common.geo;

import ir.ac.aut.ceit.pervasive.common.BaseFactory;
import android.content.Context;

/**
 * A factory which can create relevant {@link LocationMonitor} instances.
 * 
 * @author Atefeh Zareh
 */
public class LocationMonitorFactory extends BaseFactory {

    public LocationMonitor getMonitor(final Context context) {
        return shouldUseFake() ? new FakeLocationMonitor() : new RealLocationMonitor(context);
    }

}
