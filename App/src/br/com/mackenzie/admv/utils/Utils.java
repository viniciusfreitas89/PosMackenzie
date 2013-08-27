package br.com.mackenzie.admv.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.util.Log;
/**
 * 
 * @author Vinicius
 *
 */
public class Utils {
	
	@SuppressLint("SimpleDateFormat") 
	public static Date stringToDate(String data, String formato){
		try {
			SimpleDateFormat format = new SimpleDateFormat(formato);
			java.util.Date dt = new java.util.Date(format.parse(data).getTime());
			return dt;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String inputStreamToString(InputStream is){
		//convert response to string
    	String result = "";
		try{
	       BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
	       StringBuilder sb = new StringBuilder();
	       sb.append(reader.readLine() + "\n");

	       String line="0";
	       while ((line = reader.readLine()) != null) {
	    	   sb.append(line + "\n");
	       }
	       is.close();
	       return sb.toString();
        }catch(Exception e){
              Log.e("log_tag", "Error converting result "+e.toString());
        }
		
		return result;
	}
	
}