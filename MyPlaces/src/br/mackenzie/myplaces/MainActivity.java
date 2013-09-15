package br.mackenzie.myplaces;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import br.mackenzie.myplaces.R;
import br.mackenzie.myplaces.business.UsuarioBusiness;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.vo.UsuarioVO;
import br.mackenzie.myplaces.xml.XMLReader;

/**
 * 
 * @author Vinicius
 *
 */
public class MainActivity extends Activity {
	public static String pathXml = null;
//	private ConnectivityManager conManager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        setContentView(R.layout.login_layout);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
//        if (!AndroidUtils.isConectadoNaInternet(conManager)){
//        	AndroidUtils.showMessageDialog(this, this.getString(R.string.mensagem_sem_conexao_internet), true);
//        }else{
	        pathXml = AndroidUtils.getDataDir(this)+"/"+UsuarioVO.class.getSimpleName()+".xml";
	        
	        TaskCheckLogin task = new TaskCheckLogin();
	        task.execute();
//        }
    }
    
    private boolean isAutenticado(){
    	XMLReader<UsuarioVO> reader = new XMLReader<UsuarioVO>(UsuarioVO.class);
		UsuarioVO user = reader.readFile(pathXml);
		
		if (user == null){
			return false;
		}
		
		UsuarioBusiness bsn = new UsuarioBusiness();
		try {
			bsn.fazerLogin(user.getEmail(), user.getSenha());
		} catch (Exception e) {
			return false;
		}
    	
    	return true;
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
