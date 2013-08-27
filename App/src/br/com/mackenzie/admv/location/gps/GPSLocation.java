package br.com.mackenzie.admv.location.gps;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import br.com.mackenzie.admv.location.Coordenadas;
import br.com.mackenzie.admv.location.Localizacao;
import br.com.mackenzie.admv.location.listener.LocationListenerExt;
import br.com.mackenzie.admv.utils.AndroidUtils;
/**
 * 
 * @author Vinicius
 *
 */
public class GPSLocation implements Localizacao{
	private Activity activity;
	private LocationManager mlocManager = null;
	
	public GPSLocation(Activity activity){
		this.activity = activity;
		mlocManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
	}
	
	public boolean isProvedorAtivo(){
		return mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}
	
	public Coordenadas getMinhaLocalizacao(){
		Coordenadas coordenadas = new Coordenadas();
		
        LocationListenerExt mlocListener = new LocationListenerExt();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);

        if (isProvedorAtivo()) {
           if(LocationListenerExt.getLatitude()>0) {
        	   coordenadas.setLatitude(LocationListenerExt.getLatitude());
        	   coordenadas.setLongitude(LocationListenerExt.getLongitude());
            }
            else {
            	 String title = "Aguarde";
                 String msg = "Carregando localização.";
                 AndroidUtils.showDialogWait(activity, title, msg);
             }
        } else {
        	AndroidUtils.showMessageDialog(activity, "O GPS não esta ativo", false);
        }
        
       return coordenadas;
	}
}
