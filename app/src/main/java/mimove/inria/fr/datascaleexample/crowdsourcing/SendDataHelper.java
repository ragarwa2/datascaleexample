package mimove.inria.fr.datascaleexample.crowdsourcing;

import android.content.Context;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
import android.util.Log;

import com.ambientic.crowdsource.core.GoFlowException;
import com.ambientic.crowdsource.core.GoFlowFactory;
import com.ambientic.crowdsource.core.GoFlowPushTask;
//import com.google.android.gms.maps.model.LatLng;

//import java.util.HashMap;


import mimove.inria.fr.datascaleexample.models.Mood;
import mimove.inria.fr.datascaleexample.models.Station;

public class SendDataHelper {
    private static SendDataHelper _instance;
    private static String Tag = "SendDataHelper";

    private CrowdsourcingMonitor _monitor = null;
    private String _id;

    private Context context;


    public static SendDataHelper getInstance(){
        if(_instance == null)
            _instance = new SendDataHelper();

        return _instance;
    }

    /**
     * private constructor
      */
    private SendDataHelper() {
    };


    /**
     * Send a measurement object to the server
     *
     * @param measurement The measurement instance to send
     */
    public long sendMoodToServer(Mood measurement,Station stationMeasurement){
        // make sure the connection is started

        try {
            CSMeasurement csMeasurement = new CSMeasurement();
            csMeasurement.setCrowdSourcedValue(measurement);
            csMeasurement.setCrowdSourcedLocation(stationMeasurement);
            // reuse existing pushtask, but create if failure at start/runtime
            GoFlowPushTask<CSMeasurement> _pushTaskMeasurment;
            Log.d(Tag, "h1");
            Log.d(Tag, ""+csMeasurement.getCrowdSourcedLocation().getId());
            try {
                CSMeasurementPushCallback callback = new CSMeasurementPushCallback();
                _pushTaskMeasurment = new GoFlowPushTask<CSMeasurement>(_id, CSMeasurement.class, callback);
                // provide the task to the callback so that it can unregister the publisher
                callback.setTask (_pushTaskMeasurment);
                if (_pushTaskMeasurment!=null) {
                    long requestId = _pushTaskMeasurment.sendData(csMeasurement);
                    Log.d(Tag, "pushed");
                    return requestId;
                } else {
                    Log.d(Tag, "sendMeasurementToServer failed CS not yet initialized");
                }
            } catch (GoFlowException e) {
                Log.d(Tag, "unable to create push task " + e.getMessage());
            }

        } catch (Exception e) {
            // global catch to prevent app from crashing due to some unexpected network error
            Log.d(Tag, "sendMeasurementToServer failed " + e.getMessage());
        }

        return 0;
    }




    /**
     *
     */
    public void connectCrowdsourcing() {
        try {//to be sure the call back is done
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GoFlowFactory.getSingleton().connect();
    }

    /**
     *
     */
    public void disconnectCrowdsourcing() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GoFlowFactory.getSingleton().disconnect();
    }


    /**
     * Initialize the crowdsourcing middleware. Connecting to RabbitMQ and GoFlow server.
     */

    public void initCrowdsourcing(Context context) {
        this.context = context;
        _id = "uniqueID";
        _monitor = new CrowdsourcingMonitor();
        this.context = context;
        // initialize goflow
        try {
            // disable heartbeats to preserve battery - must be done before init
            GoFlowFactory.getSingleton().setHeartbeats(0);
            // init goflow
            Object[] args = new Object[2];
            args[0] = context;
            args[1] = _monitor;
            // do not start the connection, just init the fields
            GoFlowFactory.getSingleton().initCrowdsourcing(args, false);
            Log.d(Tag, "goflow initialized");
        } catch (Exception e) {
            Log.e(Tag, "unable to init goflow " + e.getMessage());
            e.printStackTrace();
        }
    }
}