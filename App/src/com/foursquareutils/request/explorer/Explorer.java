package com.foursquareutils.request.explorer;

import java.util.List;

//https://developer.foursquare.com/docs/explore#req=venues/explore%3Fll%3D-23.55014,-46.639924
public class Explorer {
	private String headerFullLocation;
	private int totalResults;
	private List<Groups> groups;
	
	public String getHeaderFullLocation() {
		return headerFullLocation;
	}
	public void setHeaderFullLocation(String headerFullLocation) {
		this.headerFullLocation = headerFullLocation;
	}
	public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	public List<Groups> getGroups() {
		return groups;
	}
	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}
}
