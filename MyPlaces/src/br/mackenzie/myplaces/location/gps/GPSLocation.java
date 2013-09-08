package br.mackenzie.myplaces.location.gps;

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
	
	public CoordenadasVO getMinhaLocalizacao(){
		CoordenadasVO coordenadas = new CoordenadasVO();
		
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
