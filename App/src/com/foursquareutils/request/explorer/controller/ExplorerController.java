package com.foursquareutils.request.explorer.controller;

import com.foursquareutils.request.place.Place;
import com.foursquareutils.request.user.User;
import com.google.gson.Gson;
import com.json.JSONException;
import com.json.JSONObject;

public class ExplorerController {
	
	/**
	 * Método responsável pela conversão de uma String JSON para um Objeto Explorer
	 * @param json
	 * @return
	 */
	public Place locais(String json){
		Place place = null;
		try {
			JSONObject jo;
			Gson gson = new Gson();
			jo = new JSONObject(json).getJSONObject("response");
			
			place = gson.fromJson(jo.toString(), Place.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return place;
	}
	
	/**
	 * Método responsável pela conversão de uma String JSON para um Objeto User
	 * @param json
	 * @return
	 */
	public User user(String json){
		User user = null;
		try {
			JSONObject jo;
			Gson gson = new Gson();
			jo = new JSONObject(json).getJSONObject("response");
			
			user = gson.fromJson(jo.toString(), User.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}
}
