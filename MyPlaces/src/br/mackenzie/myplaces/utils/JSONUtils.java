package br.mackenzie.myplaces.utils;

import com.google.gson.Gson;
import com.json.JSONException;
import com.json.JSONObject;

@SuppressWarnings("unchecked")
public class JSONUtils<T>{
	private Class<?> type;
	
	public JSONUtils(Class<?> type) { 
		this.type = type;
	}
	
	public T translate(String json) throws JSONException{
		T obj = null;
		
		JSONObject jo;
		Gson gson = new Gson();
		jo = new JSONObject(json);
		
		obj = (T) gson.fromJson(jo.toString(), type);
		
		return obj;
	}
	
	public T translate(String json, String key) throws JSONException{
		T obj = null;
		
		JSONObject jo;
		Gson gson = new Gson();
		jo = new JSONObject(json).getJSONObject(key);
		
		obj = (T) gson.fromJson(jo.toString(), type);
		
		return obj;
	}
}
