

package ir.ac.aut.ceit.pervasive.common.accel;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;

/**
 * An accelerometer reader which reads real data from the device's
 * accelerometer.
 *
 * @author Atefeh Zareh
 */
public class RealAccelReader implements AccelReader {

    private final SensorEventListener accelListener = new SensorEventListener() {

        /** {@inheritDoc} */
//        @Override
        public void onSensorChanged(final SensorEvent event) {
            values = new float[]{
                event.values[SensorManager.DATA_Y],
                event.values[SensorManager.DATA_Z]
            };
        }

        /** {@inheritDoc} */
//        @Override
        public void onAccuracyChanged(final Sensor sensor, final int accuracy) {
            // Don't really care
        }

    };

    float[] values = new float[]{0, 0};
    private SensorManager manager;
    private PowerManager.WakeLock wl;

    public RealAccelReader(final Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Activity recorder");
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        wl.acquire();
    }

    public void startSampling() {
        manager.registerListener(accelListener,
                manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void stopSampling() {
        manager.unregisterListener(accelListener);
    }

    public float[] getSample() {
        return values;
    }

    @Override
    protected void finalize() throws Throwable {
        wl.release();
    }

}
