

package ir.ac.aut.ceit.pervasive.common.geo;

/**
 * Interface implemented by classes which can monitor the user's location.
 * 
 * @author Atefeh Zareh
 */
public interface LocationMonitor {

    float getAccuracy();

    double getLat();
    
    double getLon();

}
