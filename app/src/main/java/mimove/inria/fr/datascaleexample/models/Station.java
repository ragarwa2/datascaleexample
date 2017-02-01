/**
 * Initial software, Pierre-Guillaume Raverdy,
 * 
 * Copyright, Ambientic 2014
 */

package mimove.inria.fr.datascaleexample.models;

import android.location.Address;

import com.ambientic.crowdsource.data.DoExpose;
import com.ambientic.crowdsource.data.GoFlowLocation;

/**
 * Station
 * 
 * @author khoo
 * 
 */
public class Station extends GoFlowLocation {
	@DoExpose
	public static final Integer CLASS_VERSION = new Integer(4);

	protected double latitude;
	protected double longitude;

    public Station() {
		super();
	}

	/**
	 * Station constructor
	 * 
	 * @param id
	 *          Station id
	 * @param latitude
	 *          Station latitude
	 * @param longitude
	 *          Station longitude
	 */
	public Station(String id, double latitude, double longitude) {
		super();
		try {super.setId(id);}catch (Exception e){e.printStackTrace();}
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: " + id + "\n");
		sb.append("Latitude: " + String.valueOf(latitude) + "\n");
		sb.append("Longitude: " + String.valueOf(longitude) + "\n");
		return sb.toString();
	}
}
