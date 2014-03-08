package eu.liveandgov.wp1.human_activity_recognition.classifier;

import eu.liveandgov.wp1.human_activity_recognition.containers.FeatureVector;

/**
 * Created by hartmann on 3/8/14.
 */

public class ManualClassify {

    private static final String ON_TABLE = "on_table";
    private static final String SITTING  = "sitting";
    private static final String STANDING = "standing";
    private static final String WALKING  = "walking";
    private static final String RUNNING  = "running";

    public static String classify(FeatureVector v) {
        if (v.s2Var < 0.9) {
            // sitting, standing, on_table
            if (v.ytilt < 0.7) {
                if (v.s2Var < 0.05 && v.ztilt > 0.9) {
                    return ON_TABLE;
                } else {
                    return SITTING;
                }
            } else {
                return STANDING;
            }
        } else {
            // walking, running
            if (v.s2Var < 30){
                return WALKING;
            } else {
                return RUNNING;
            }
        }
    }
}
