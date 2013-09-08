package br.mackenzie.myplaces.location.listener;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
/**
 * 
 * @author Vinicius
 *
 */
public class LocationListenerExt  implements LocationListener {
    private static double latitude;
    private static double longitude;
    
    @Override
	public void onLocationChanged(Location loc){
		loc.getLatitude();
		loc.getLongitude();
		latitude=loc.getLatitude();
		longitude=loc.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider){
	}
	@Override
	public void onProviderEnabled(String provider){
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras){
	}

	public static double getLatitude() {
		return latitude;
	}

	public static void setLatitude(double latitude) {
		LocationListenerExt.latitude = latitude;
	}

	public static double getLongitude() {
		return longitude;
	}

	public static void setLongitude(double longitude) {
		LocationListenerExt.longitude = longitude;
	}
}
