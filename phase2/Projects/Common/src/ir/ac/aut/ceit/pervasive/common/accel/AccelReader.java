
package ir.ac.aut.ceit.pervasive.common.accel;

/**
 * Interface implemented by classes which can sample accelerometer data.
 * 
 * @author Atefeh Zareh
 */
public interface AccelReader {

    void startSampling();

    void stopSampling();

    float[] getSample();

}
