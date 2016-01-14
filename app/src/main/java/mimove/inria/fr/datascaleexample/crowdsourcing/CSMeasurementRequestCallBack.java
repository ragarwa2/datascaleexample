package mimove.inria.fr.datascaleexample.crowdsourcing;

import android.util.Log;
import java.util.List;
import com.ambientic.crowdsource.core.GoFlowRequestCallback;

/**
 * Created by ragarwal on 08/01/16.
 */
public class CSMeasurementRequestCallBack implements GoFlowRequestCallback<CSMeasurement,CSMeasurementRequest> {
    @Override public void failedPull() { System.out.println("request callback : failed requesting");
        Log.d("Request","failed to request");
        }
    @Override public void updatePull(List<CSMeasurement> csInfos, boolean isSelf) {
        for (CSMeasurement tmpHappi : csInfos) {
            Log.d("Request",tmpHappi.getCrowdSourcedValue().getMood());
        }
    }
    @Override
    public void invalidVersion(boolean locationVersion, boolean valueVersion) {
        Log.d("Request","request callback : invalid version");
    }
    @Override public void receiveRequest(final CSMeasurementRequest request, final boolean self) {
            Log.d("Request", request.getLocationId());
        }
}
