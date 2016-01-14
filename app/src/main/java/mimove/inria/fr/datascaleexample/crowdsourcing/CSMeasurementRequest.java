package mimove.inria.fr.datascaleexample.crowdsourcing;

import com.ambientic.crowdsource.data.GoFlowRequest;

import mimove.inria.fr.datascaleexample.models.Mood;
import mimove.inria.fr.datascaleexample.models.Station;


public class CSMeasurementRequest extends GoFlowRequest<Station, Mood> {

	private Station subject;

	public CSMeasurementRequest(Station station) {
        super(station, Mood.class);
	}

	public Station getStation() {
		return subject;
	}

	public void setStation(Station station) {
		this.subject = station;
	}

	//
	// CrowdSourcedRequest
	//

	@Override
	public String getLocationId() {
		return subject.getId();
	}

}
