

package ir.ac.aut.ceit.pervasive.common;

import android.os.Build;

/**
 * Base class for sensor factory which facilitates switching to fake sources
 * when running from an emulator.
 *
 * @author Atefeh Zareh
 */
public abstract class BaseFactory {

    protected boolean shouldUseFake() {
        return Build.PRODUCT.endsWith("sdk")
                || Build.MODEL.toLowerCase().contains("dev phone");
    }

}
