
package ir.ac.aut.ceit.pervasive.activityrecorder;

import ir.ac.aut.ceit.pervasive.activityrecorder.rpc.ActivityRecorderBinder;
import ir.ac.aut.ceit.pervasive.activityrecorder.rpc.Classification;
import ir.ac.aut.ceit.pervasive.common.ModelReader;
import ir.ac.aut.ceit.pervasive.common.accel.AccelReader;
import ir.ac.aut.ceit.pervasive.common.accel.AccelReaderFactory;
import ir.ac.aut.ceit.pervasive.common.accel.Sampler;
import ir.ac.aut.ceit.pervasive.common.aggregator.Aggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;

/**
 *
 * @author Atefeh Zareh
 */
public class RecorderService extends Service {

    private final ActivityRecorderBinder.Stub binder = new ActivityRecorderBinder.Stub() {

        public void submitClassification(String classification) throws RemoteException {
            //Log.i(getClass().getName(), "Received classification: " + classification);

            updateScores(classification);
        }

        public List<Classification> getClassifications() throws RemoteException {
            return classifications;
        }

        public boolean isRunning() throws RemoteException {
            return running;
        }

    };

    private final Runnable registerRunnable = new Runnable() {

        public void run() {
            //Log.i(getClass().getName(), "Registering");
            sampler.start();
            
            handler.postDelayed(registerRunnable, 30000);
        }

    };

    private final Runnable analyseRunnable = new Runnable() {

        public void run() {
            final Intent intent = new Intent(RecorderService.this, ClassifierService.class);
            intent.putExtra("data", sampler.getData());
            startService(intent);
        }

    };

    final Handler handler = new Handler();

    boolean running;
    final Aggregator aggregator = new Aggregator();
    AccelReader reader;
    Sampler sampler;
    public static Map<Float[], String> model;
    private final List<Classification> classifications = new ArrayList<Classification>();

    @Override
    public IBinder onBind(Intent arg0) {
        return binder;
    }

    @Override
    public void onStart(final Intent intent, final int startId) {
        super.onStart(intent, startId);

        running = true;

        reader = new AccelReaderFactory().getReader(this);
        sampler = new Sampler(handler, reader, analyseRunnable);

        init();
    }

    @SuppressWarnings("unchecked")
    public void init() {
        model = ModelReader.getModel(this, R.raw.basic_model);

        handler.postDelayed(registerRunnable, 1000);

        classifications.add(new Classification("", System.currentTimeMillis()));
    }

    void updateScores(final String classification) {
        aggregator.addClassification(classification);

        final String best = aggregator.getClassification();

        if (!classifications.isEmpty() && best.equals(classifications
                    .get(classifications.size() - 1).getClassification())) {
            classifications.get(classifications.size() - 1).updateEnd(System.currentTimeMillis());
        } else {
            classifications.add(new Classification(best, System.currentTimeMillis()));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (running) {
            running = false;

            if (sampler != null) {
                sampler.stop();
            }
            
            handler.removeCallbacks(registerRunnable);
        }
    }

}
