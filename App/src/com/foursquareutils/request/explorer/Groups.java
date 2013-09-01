package com.foursquareutils.request.explorer;

import java.util.List;

public class Groups {
	private String type;
	private String name;
	private List<Items> items;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
}
