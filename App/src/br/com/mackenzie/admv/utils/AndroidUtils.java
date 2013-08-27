package br.com.mackenzie.admv.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
 
/**
 * 
 * @author Vinicius
 *
 */
public class AndroidUtils {

	public static void showMessage(Context context, String mensagem){
		CharSequence text = mensagem;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	  
	public static void showMessageDialog(final Activity context, final String mensagem, final boolean kill){
		context.runOnUiThread(new Runnable() {
  		  public void run() {  
			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		    	    public void onClick(DialogInterface dialog, int which) {
		    	        switch (which){
		    	        	case DialogInterface.BUTTON_NEUTRAL:
		    	        		if (kill){
		    	        			android.os.Process.killProcess(android.os.Process.myPid());
		    	        		}
		    	        		break;
		    	        }
		    	    }
		  	  };
	
	    	  AlertDialog.Builder builder= new AlertDialog.Builder(context);
	    	  builder.setMessage(mensagem).setNeutralButton("Ok", dialogClickListener).show();
  		  }
		});
	}
	
	public static ProgressDialog showDialogWait(Activity act, String titulo, String mensagem){
		ProgressDialog dlDialog = new ProgressDialog(act);
        dlDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dlDialog.setTitle(titulo);
        dlDialog.setMessage(mensagem);
        
        return dlDialog;
	}
	
	public static boolean isOnline(ConnectivityManager conManager) {
        NetworkInfo netInfo = conManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
	
	/**
	 * Retorna o diretório de dados da aplicação.
	 * @param context
	 * @return String
	 * @throws Exception
	 */
	public static String getDataDir(Activity context) {
	    try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.dataDir;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return null;
	}
}