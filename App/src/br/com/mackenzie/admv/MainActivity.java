package br.com.mackenzie.admv;

import java.util.List;

import com.foursquare.android.nativeoauth.FoursquareOAuth;

import br.com.mackenzie.admv.foursquare.FoursquareController;
import br.com.mackenzie.admv.location.Coordenadas;
import br.com.mackenzie.admv.location.Localizacao;
import br.com.mackenzie.admv.location.Lugar;
import br.com.mackenzie.admv.location.network.NetworkLocation;
import br.com.mackenzie.admv.utils.AndroidUtils;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * 
 * @author Vinicius
 *
 */
public class MainActivity extends Activity {
	
	private Activity activity = null;
	
	FoursquareController controller;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        controller = FoursquareController.getInstance(this);
        
        //Habilita o uso da internet.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        activity = this;
        this.eventoElementos();
    }
    
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private Context getActionBarThemedContextCompat() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void eventoElementos(){
    	final TextView txtLatitude = (TextView) this.findViewById(R.id.txtLatitude);
    	final TextView txtLongitude = (TextView) this.findViewById(R.id.txtLongitude);
    	
    	Button btn_show_location = (Button) findViewById(R.id.btnLocalizacao);
    	btn_show_location.setOnClickListener(new View.OnClickListener() {
    	     public void onClick(View view) {
    	    	 Localizacao localizacao = new NetworkLocation(activity);
    	    	 Coordenadas coord = localizacao.getMinhaLocalizacao();
    	    	 
    	    	 txtLatitude.setText("Latitude: "+coord.getLatitude());
    	    	 txtLongitude.setText("Longitude: "+coord.getLongitude());
    	    	 
    	    	 try {
					List<Lugar> lugares = controller.listarLugaresProximos(coord);
					System.out.println("lugares: "+lugares);
					if (lugares!=null){
						for (Lugar lugar : lugares){
							System.out.println(lugar.getNome());
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
    	      }
    	 });
    	
//    	controller.limparDadosUsuario();
    	atualizarBtnFoursquare();
    }
    
    private void atualizarBtnFoursquare(){
    	boolean isAuthorized = controller.isAutenticado();
    	
    	Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setVisibility(isAuthorized ? View.GONE : View.VISIBLE);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = FoursquareOAuth.getConnectIntent(MainActivity.this, FoursquareController.CLIENT_ID);
                
                if (FoursquareOAuth.isPlayStoreIntent(intent)) {
                    AndroidUtils.showMessageDialog(activity, getString(R.string.foursquare_nao_instalado), false);
                    startActivity(intent);
                } else {
                    startActivityForResult(intent, FoursquareController.REQUEST_CODE_FSQ_CONNECT);
                }
            }
        });
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FoursquareController.REQUEST_CODE_FSQ_CONNECT:
                controller.onCompleteConnect(resultCode, data);
                break;
                
            case FoursquareController.REQUEST_CODE_FSQ_TOKEN_EXCHANGE:
                controller.onCompleteTokenExchange(resultCode, data);
                atualizarBtnFoursquare();
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
