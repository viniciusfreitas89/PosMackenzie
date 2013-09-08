package br.mackenzie.myplaces.utils;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
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
	
	/**
	 * Recomendado para conexões sem proxy. 
	 * @param url : String
	 * @param params : List<NameValuePair>
	 * @return InputStream
	 * @throws Exception
	 */
	public static InputStream requestByPost(String url, List<NameValuePair> params) throws Exception{
		
		return request(url, params, null, null, null, null, true);
	}
	
	/**
	 * Recomendado para conexões com proxy. 
	 * @param url : String
	 * @param params : List<NameValuePair>
	 * @return InputStream
	 * @throws Exception
	 */
	public static InputStream requestByPost(String url,
											List<NameValuePair> params, 
											String host, 
											Integer porta, 
											String login, 
											String senha) throws Exception{
		
		return request(url, params, host, porta, login, senha, true); 
    }
	
	public static InputStream requestByGet(String url, List<NameValuePair> params) throws Exception{
		
		return request(url, params, null, null, null, null, false);
	}
	
	/**
	 * Recomendado para conexões com proxy. 
	 * @param url : String
	 * @param params : List<NameValuePair>
	 * @return InputStream
	 * @throws Exception
	 */
	public static InputStream requestByGet(String url,
											List<NameValuePair> params, 
											String host, 
											Integer porta, 
											String login, 
											String senha) throws Exception{
    	
    	return request(url, params, host, porta, login, senha, false); 
    }
	
	private static InputStream request(String url,
										List<NameValuePair> params, 
										String host, 
										Integer porta, 
										String login, 
										String senha,
										boolean isPost) throws Exception{
    	InputStream is = null;
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	
		try{
    	     if (host!=null && !host.isEmpty()){
	    		 if (login!=null && !login.isEmpty() && senha!=null && !senha.isEmpty()){
	    	    	 httpclient.getCredentialsProvider().setCredentials(
	 	                    new AuthScope(host, porta),
	 	                    new UsernamePasswordCredentials(login, senha));
    	    	 }
	    		 HttpResponse response;
	    		 HttpEntity entity;
	    		 
	    		 HttpHost proxy = new HttpHost(host, porta);
    	    	 httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
    	    	 
    	    	 HttpHost targetHost = new HttpHost(url.replace("http://", ""));
	             
	             if (isPost){
	            	 HttpPost httppost = new HttpPost("/");
		             httppost.setHeader("content-type", "application/x-www-form-urlencoded");
		             if (params!=null){
		            	 httppost.setEntity(new UrlEncodedFormEntity(params));
		             }
		             response = httpclient.execute(targetHost, httppost);
	             }else{
	            	 HttpGet httpget = new HttpGet("/");
	            	 httpget.setHeader("content-type", "application/x-www-form-urlencoded");
		             response = httpclient.execute(targetHost, httpget);
	             }
	             
	             entity = response.getEntity();
	             if (response.getStatusLine().toString().contains("Proxy Authentication Required")){
	            	throw new Exception("Autenticação de proxy necessária");
	             }
	            
	             is = entity.getContent();
    	     }else{
    	    	 HttpResponse response;
        	     
        	     if (isPost){
        	    	 HttpPost httppost = new HttpPost(url);
            	     httppost.setHeader("content-type", "application/x-www-form-urlencoded");
            	     
            	     if (params!=null){
            	    	 httppost.setEntity(new UrlEncodedFormEntity(params));
            	     }
            	     
            	     response = httpclient.execute(httppost);
	             }else{
		             HttpGet httpget = new HttpGet(url);
		             httpget.setHeader("content-type", "application/x-www-form-urlencoded");
            	     
            	     response = httpclient.execute(httpget);
	             }
        	     
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
