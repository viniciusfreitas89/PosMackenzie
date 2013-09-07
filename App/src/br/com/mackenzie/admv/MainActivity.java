package br.com.mackenzie.admv;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import br.com.mackenzie.admv.dao.UsuarioDAO;
import br.com.mackenzie.admv.foursquare.FoursquareController;
import br.com.mackenzie.admv.model.Usuario;
import br.com.mackenzie.admv.utils.AndroidUtils;
import br.com.mackenzie.admv.xml.persistence.XMLReader;

/**
 * 
 * @author Vinicius
 *
 */
public class MainActivity extends Activity {
	public static String pathXml = null;
	
	FoursquareController controller;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        pathXml = AndroidUtils.getDataDir(this)+"/"+Usuario.class.getSimpleName()+".xml";
        
        TaskCheckLogin task = new TaskCheckLogin();
        task.execute();
    }
    
    private boolean isAutenticado(){
    	XMLReader<Usuario> reader = new XMLReader<Usuario>(Usuario.class);
		Usuario user = reader.readFile(pathXml);
		
		UsuarioDAO dao = new UsuarioDAO();
		
		try {
			boolean status = dao.fazerLogin(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return false;
    }
    
    private class TaskCheckLogin extends AsyncTask<Object, Integer, Void> { 
	    @Override 
	    protected void onPreExecute() {
	    } 
	    @Override
		protected Void doInBackground(Object... params) {
	    	if (!isAutenticado()){
		    	Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
	        	startActivity(intent);
	    	}
			return null;
		} 
	    @Override 
	    protected void onProgressUpdate(Integer... progress) { 
	    } 
	    @Override 
	    protected void onPostExecute(Void result) { 
	    }
	}
}
