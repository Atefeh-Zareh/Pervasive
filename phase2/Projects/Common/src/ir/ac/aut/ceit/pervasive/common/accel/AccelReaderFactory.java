
package ir.ac.aut.ceit.pervasive.common.accel;

import android.content.Context;

/**
 * A factory which can produce an appropriate {@link AccelReader}.
 *
 * @author Atefeh Zareh
 */
public class AccelReaderFactory {

    public AccelReader getReader(final Context context) {
        return new RealAccelReader(context);
    }

}
