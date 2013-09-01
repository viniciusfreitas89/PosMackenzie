package br.com.mackenzie.admv.location;
/**
 * 
 * @author Vinicius
 *
 */
public class Coordenadas {
	/*
	 * Valor default, localização de SP
	 * 
	 * */
	private double latitude = -23.55014;
	private double longitude = -46.639924;
	
	@Override
	public String toString(){
		return this.latitude+","+this.longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
