package ir.ac.aut.ceit.pervasive.fantastic;

import ir.ac.aut.ceit.pervasive.fantastic.dummy.DummyContent;
import ir.ac.aut.ceit.pervasive.fantastic.dummy.DummyContent.SensorItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Sensor detail screen. This fragment is
 * either contained in a {@link SensorListActivity} in two-pane mode (on
 * tablets) or a {@link SensorDetailActivity} on handsets.
 */
public class SensorDetailFragment extends Fragment implements
		SensorEventListener, LocationListener {
	Map<String, SensorReport> name2Report = new HashMap<String, SensorReport>();
	Map<String, SensorReport> name2Report4Gps = new HashMap<String, SensorReport>();

	// private final Sensor/Manager mSensorManager;
	// private final Sensor mAccelerometer;

	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.SensorItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public SensorDetailFragment() {
		// mSensorManager = (SensorManager)getSystemServic
		// mAccelerometer =
		// mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			setup();
		}
	}

	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_sensor_detail, container,
				false);
		// Show the dummy content as text in a TextView.
		show();
		return rootView;
	}

	/**
	 * set mItem in to root view to show.
	 */
	private void show() {
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.sensor_detail))
					.setText(mItem.name);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		Log.d("onAccuracyChanged, ", arg0 + ", " + arg1);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		String name = event.sensor.getName();
		SensorReport sensorReport = name2Report.get(name);
		float[] values = event.values;
		String valuesStr = "";
		for (float f : values) {
			valuesStr += f + ", ";
		}
		valuesStr = valuesStr.substring(0, valuesStr.length() - 2);
		sensorReport.setValues(valuesStr);
		name2Report.put(name, sensorReport);// TODO may be deleted
		updateSensorValues();
	}

	/**
	 * 1. fill map 2. register listener
	 */
	@SuppressWarnings("deprecation")
	private void setup() {
		String id = getArguments().getString(ARG_ITEM_ID);
		String result = "";
		if (id.equals("1")) {
			LocationManager locationManager = (LocationManager) getActivity()
					.getSystemService(Context.LOCATION_SERVICE);
			// Register the listener with the Location Manager to receive
			// location updates
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, this);
			SensorReport sensorReport = new SensorReport(
					"GPS", "gps", "");
			name2Report4Gps.put("gps", sensorReport);
			result = sensorReport.toString();

		} else if (id.equals("2")) {
			SensorManager sensorManager = (SensorManager) getActivity()
					.getSystemService(Context.SENSOR_SERVICE);
			List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

			for (Sensor sensor : sensors) {
				
				sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
				
				String type = "";
				if (Sensor.TYPE_ACCELEROMETER == sensor.getType())
					type = "Accelerometer";
				else if (Sensor.TYPE_AMBIENT_TEMPERATURE == sensor.getType())
					type = "Ambient Temperature";
				else if (Sensor.TYPE_GRAVITY == sensor.getType())
					type = "Gravity";
				else if (Sensor.TYPE_GYROSCOPE == sensor.getType())
					type = "Gyroscope";
				else if (Sensor.TYPE_LIGHT == sensor.getType())
					type = "Light";
				else if (Sensor.TYPE_LINEAR_ACCELERATION == sensor.getType())
					type = "Linear Acceleration";
				else if (Sensor.TYPE_MAGNETIC_FIELD == sensor.getType())
					type = "Magnetic Field";
				else if (Sensor.TYPE_PRESSURE == sensor.getType())
					type = "Pressure";
				else if (Sensor.TYPE_PROXIMITY == sensor.getType())
					type = "Proximity";
				else if (Sensor.TYPE_RELATIVE_HUMIDITY == sensor.getType())
					type = "Relative Humidity";
				else if (Sensor.TYPE_ROTATION_VECTOR == sensor.getType())
					type = "Rotation Vectore";
				else if (Sensor.TYPE_ORIENTATION == sensor.getType())
					type = "Orientation";
				else if (Sensor.TYPE_TEMPERATURE == sensor.getType())
					type = "Temprature";
				else
					type = "id(" + sensor.getType() + ")";
				String name = sensor.getName();
				SensorReport sensorReport = new SensorReport(type, name, "");
				name2Report.put(name, sensorReport);
				result += sensorReport.toString() + "\n";

			}
		} else if (id.equals("3")) {
			result = "This application reports you all your location information ("
					+ "containing Latitude(x), Longitude(y), Altitude(z), Speed(v)"
					+ ") and available sensors "
					+ "(containing Accelerometer, Ambient Temperature, Gravity, Gyroscope, "
					+ "Light, Linear Acceleration, Magnetic Field, Pressure, Proximity,"
					+ " Relative Humidity, Rotation Vectore, Orientation and Temprature) and their name then collect"
					+ " a context from their logs and send it to remote server."
					+ "server process this context and according a bayesian network "
					+ "in server side, proper commands are send to device.";
		} else if (id.equals("4")) {
			result = "Amirkabir University of Technology (AUT),"
					+ " Computer Engineering and Information Technology (CEIT) Department,"
					+ " Pervasive Course, by Dr. Sharifian." + "\n\n"
					+ "Student Name: Atefeh Zareh\n" + "Student Id: 92131006";
		} else if (id.equals("5")) {
			result = "Address: "
					+ "Amirkabir University of Technology, 424 Hafez Ave, Tehran, Iran\n"
					+ "Tel: +98 (21) 64540\n" + "P.O. Box: 15875-4413\n"
					+ "Mail: zareh@aut.ac.ir";
		}
		mItem = new SensorItem("1", result);
	}

	private void updateSensorValues() {
		String result = "";
		for (String name : name2Report.keySet()) {
			String out = name2Report.get(name).toString();
			result += out;
		}
		mItem = new SensorItem("1", result);
		show();
	}

	private void updateGpsValues() {
		String result = "";
		for (String name : name2Report4Gps.keySet()) {
			String out = name2Report4Gps.get(name).toString();
			result += out;
		}
		mItem = new SensorItem("1", result);
		show();
	}

	@Override
	public void onLocationChanged(Location location) {
		double x = location.getLatitude();
		double y = location.getLongitude();
		double z = location.getAltitude();
		double v = location.getSpeed();
		String values = "x=" + x + ", y=" + y + ", z=" + z + ", v=" + v;
		SensorReport sensorReport = name2Report4Gps.get("gps");
		sensorReport.setValues(values);
		name2Report4Gps.put("gps", sensorReport);
		updateGpsValues();
	}

	@Override
	public void onProviderDisabled(String arg0) {
	}

	@Override
	public void onProviderEnabled(String arg0) {
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	}
}
