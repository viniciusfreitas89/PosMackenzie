package com.foursquareutils.request.explorer.controller;

import com.foursquareutils.request.explorer.Explorer;
import com.google.gson.Gson;
import com.json.JSONException;
import com.json.JSONObject;

public class ExplorerController {
	
	/**
	 * Método responsável pela conversão de uma String JSON para um Objeto Explorer
	 * @param json
	 * @return
	 */
	public Explorer explorar(String json){
		Explorer explorer = null;
		try {
			JSONObject jo;
			Gson gson = new Gson();
			jo = new JSONObject(json).getJSONObject("response");
			
			explorer = gson.fromJson(jo.toString(), Explorer.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return explorer;
	}
}
