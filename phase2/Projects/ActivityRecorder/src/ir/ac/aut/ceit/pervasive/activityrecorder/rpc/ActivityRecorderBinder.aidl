
package ir.ac.aut.ceit.pervasive.activityrecorder.rpc;

import ir.ac.aut.ceit.pervasive.activityrecorder.rpc.Classification;

/**
 *
 * @author Atefeh Zareh
 */
interface ActivityRecorderBinder {

    boolean isRunning();

    void submitClassification(String classification);

    List<Classification> getClassifications();

}
