package com.foursquareutils.request.user;

public class User {
	private String id;
	private String firstName;
	private String lastName;
	private String gender;
	private String homeCity;
	private Contact contact;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		if (gender!=null){
			if (gender.equalsIgnoreCase("male")){
				return "Masculino";
			}else if (gender.equalsIgnoreCase("female")){
				return "Feminino";
			}
		}
		
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHomeCity() {
		return homeCity;
	}
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
