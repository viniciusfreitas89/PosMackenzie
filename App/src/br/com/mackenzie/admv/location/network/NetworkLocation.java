package br.com.mackenzie.admv.location.network;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import br.com.mackenzie.admv.location.Localizacao;
import br.com.mackenzie.admv.location.listener.LocationListenerExt;
import br.com.mackenzie.admv.model.Coordenadas;
import br.com.mackenzie.admv.utils.AndroidUtils;
/**
 * 
 * @author Vinicius
 *
 */
public class NetworkLocation implements Localizacao{
	private Activity activity;
	private LocationManager mlocManager = null;
	
	public NetworkLocation(Activity activity){
		this.activity = activity;
		mlocManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
	}
	
	public boolean isProvedorAtivo(){
		return mlocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	}
	
	public Coordenadas getMinhaLocalizacao(){
		Coordenadas coordenadas = new Coordenadas();
		
        LocationListenerExt mlocListener;
        mlocListener = new LocationListenerExt();
        
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);

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
        	 AndroidUtils.showMessageDialog(activity, "A rede não esta ativa", false);
         }
        
       return coordenadas;
	}
}
