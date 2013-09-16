package util;

import com.google.gson.Gson;
import com.json.JSONException;
import com.json.JSONObject;


public class JsonUtils<T>{
	private Class<T> type;
	
	public JsonUtils(Class<T> type) { 
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
