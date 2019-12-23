
package ir.ac.aut.ceit.pervasive.activityrecorder;

import ir.ac.aut.ceit.pervasive.activityrecorder.rpc.ActivityRecorderBinder;
import ir.ac.aut.ceit.pervasive.common.Classifier;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

/**
 *
 * @author Atefeh Zareh
 */
public class ClassifierService extends Service implements Runnable {

    ActivityRecorderBinder service = null;
    String classification;

    private ServiceConnection connection = new ServiceConnection() {

        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            service = ActivityRecorderBinder.Stub.asInterface(arg1);

            try {
                service.submitClassification(classification);
            } catch (RemoteException ex) {

            }
            
            stopSelf();
        }

        public void onServiceDisconnected(ComponentName arg0) {
            Toast.makeText(ClassifierService.this, R.string.error_disconnected, Toast.LENGTH_LONG);
        }
    };

    private float[] data;

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        //Log.i(getClass().getName(), "Starting classifier");

        data = intent.getFloatArrayExtra("data");

        new Thread(this).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbindService(connection);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    public void run() {
        classification = new Classifier(RecorderService.model.entrySet()).classify(data);

        //Log.i(getClass().getName(), "Classification: " + classification);

        bindService(new Intent(this, RecorderService.class), connection, BIND_AUTO_CREATE);
    }

}
