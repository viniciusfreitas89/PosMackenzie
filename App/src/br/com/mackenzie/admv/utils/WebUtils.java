package br.com.mackenzie.admv.utils;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
/**
 * 
 * @author Vinicius
 *
 */
public class WebUtils {
	
	/*
	 * Utilizado para conexões sem proxy
	 * */
	public static InputStream requestByPost(String url, ArrayList<NameValuePair> params) throws Exception{
		
		return requestByPost(url, params, null, null, null, null);
	}
	
	/*
	 * Utilizado para conexões com proxy
	 * */
	public static InputStream requestByPost(String url,
											ArrayList<NameValuePair> params, 
											String host, 
											Integer porta, 
											String login, 
											String senha) throws Exception{
    	InputStream is = null;
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	
		try{
    	     if (host!=null && !host.isEmpty()){
	    		 if (login!=null && !login.isEmpty() && senha!=null && !senha.isEmpty()){
	    	    	 httpclient.getCredentialsProvider().setCredentials(
	 	                    new AuthScope(host, porta),
	 	                    new UsernamePasswordCredentials(login, senha));
    	    	 }
	    		 
	    		 HttpHost proxy = new HttpHost(host, porta);
    	    	 httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
    	    	 
    	    	 HttpHost targetHost = new HttpHost(url.replace("http://", ""));
	             HttpPost httppost = new HttpPost("/");
 
	             httppost.setHeader("content-type", "application/x-www-form-urlencoded");
	             httppost.setEntity(new UrlEncodedFormEntity(params));

	             HttpResponse response;
				 response = httpclient.execute(targetHost, httppost);
	             HttpEntity entity = response.getEntity();
	             
	             if (response.getStatusLine().toString().contains("Proxy Authentication Required")){
	            	throw new Exception("Autenticação de proxy necessária");
	             }
	            
	             is = entity.getContent();
    	     }else{
    	    	 HttpPost httppost = new HttpPost(url);
        	     httppost.setHeader("content-type", "application/x-www-form-urlencoded");
        	     
        	     httppost.setEntity(new UrlEncodedFormEntity(params));
        	     
        	     HttpResponse response = httpclient.execute(httppost);
        	     HttpEntity entity = response.getEntity();
        	     is = entity.getContent();
    	     }
	     }catch(Exception e){
	    	 	 StringWriter sw = new StringWriter();
	    	 	 e.printStackTrace(new PrintWriter(sw));
    	         Log.e("log_tag", "Error in http connection"+e.toString());
    	         Log.e("log_tag", "ERROR: "+sw);
    	         
    	         if (e.getMessage().contains("refused")){
    	        	 throw new Exception("Conexão negada.");
    	         }else if (e.getMessage().contains("No address associated with hostname")){
    	        	 throw new Exception("Nenhúm endereço associado a este host: "+host);
    	         }
    	         return null;
	     }
		
    	return is; 
    }
}
