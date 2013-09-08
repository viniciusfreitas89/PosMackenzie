package br.mackenzie.myplaces.location.network;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import br.mackenzie.myplaces.location.Localizacao;
import br.mackenzie.myplaces.location.listener.LocationListenerExt;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.vo.CoordenadasVO;
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
	
	public CoordenadasVO getMinhaLocalizacao(){
		CoordenadasVO coordenadas = new CoordenadasVO();
		
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
