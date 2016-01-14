package mimove.inria.fr.datascaleexample.crowdsourcing;

import android.util.Log;

import com.ambientic.crowdsource.core.GoFlowManagerCallback;
import com.ambientic.incentive.core.IncentiveCallback;

/**
 * Created by Georgios Mathioudakis (mathioudakis.giorgos@gmail.com) on 26/9/14.
 */
public class CrowdsourcingMonitor implements GoFlowManagerCallback {

    private static String Tag = "CrowdsourcingMonitor";

    @Override
    public void crowdsourcingEnabled() {
        Log.d(Tag, "CrowdsourcingEnabled");
    }

    @Override
    public void crowdsourcingDisabled() {
        Log.d(Tag, "CrowdsourcingDisabled");
    }

    @Override
    public void crowdsourcingNetworkPending() {
        Log.d(Tag, "CrowdsourcingNetworkPending");
    }

    @Override
    public IncentiveCallback getIncentiveCallback() {
        return null;
    }
}