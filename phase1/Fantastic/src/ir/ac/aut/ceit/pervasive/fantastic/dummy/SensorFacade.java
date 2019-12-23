package ir.ac.aut.ceit.pervasive.fantastic.dummy;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * @author Atefeh Zareh May 1, 2014
 */
public class SensorFacade extends Activity {

	public void getAllAvailableSensors() {
		Log.d("Zareh", "1");
		SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Log.d("Zareh", "2");
		List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		Log.d("Zareh", "3");
		for (Sensor sensor : sensors) {
			Log.d("Zareh", sensor.getName() );
		}
		Log.d("Zareh", "end");
	}
}
