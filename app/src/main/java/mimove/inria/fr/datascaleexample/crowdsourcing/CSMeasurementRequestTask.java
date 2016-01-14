package mimove.inria.fr.datascaleexample.crowdsourcing;
import mimove.inria.fr.datascaleexample.models.Mood;

import com.ambientic.crowdsource.core.GoFlowRequestCallback;
import com.ambientic.crowdsource.core.GoFlowRequestTask;

/**
 * Created by ragarwal on 08/01/16.
 */
public class CSMeasurementRequestTask extends GoFlowRequestTask<CSMeasurement,CSMeasurementRequest >{
    public CSMeasurementRequestTask(Class<CSMeasurement> cSMeasurement, CSMeasurementRequest  moodrequest, GoFlowRequestCallback<CSMeasurement,CSMeasurementRequest> callback){
        super(cSMeasurement,moodrequest,callback);
    }

}
