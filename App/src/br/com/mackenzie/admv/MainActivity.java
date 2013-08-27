package br.com.mackenzie.admv;

import com.foursquare.android.nativeoauth.FoursquareCancelException;
import com.foursquare.android.nativeoauth.FoursquareDenyException;
import com.foursquare.android.nativeoauth.FoursquareInvalidRequestException;
import com.foursquare.android.nativeoauth.FoursquareOAuth;
import com.foursquare.android.nativeoauth.FoursquareOAuthException;
import com.foursquare.android.nativeoauth.FoursquareUnsupportedVersionException;
import com.foursquare.android.nativeoauth.model.AccessTokenResponse;
import com.foursquare.android.nativeoauth.model.AuthCodeResponse;

import br.com.mackenzie.admv.foursquare.FoursquareController;
import br.com.mackenzie.admv.location.Coordenadas;
import br.com.mackenzie.admv.location.Localizacao;
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
	
	private static final int REQUEST_CODE_FSQ_CONNECT = 200;
    private static final int REQUEST_CODE_FSQ_TOKEN_EXCHANGE = 201;
    
    private static final String CLIENT_ID = "ZFH1KCRLDWBBDAPUIAQARFURLRFE4FRP35RHVPQ1OZPE4YR5";
    private static final String CLIENT_SECRET = "5NYDBENTBWB0TG5GKK1WQYU2PTDTZJKEI3OTJFVGIY3DIE0I";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Habilita o uso da internet.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
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
        // Inflate the menu; this adds items to the action bar if it is present.
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
    	      }
    	 });
    	
    	FoursquareController controller = new FoursquareController(activity);
    	controller.limparDadosUsuario();
    	
    	atualizarBtnFoursquare();
    }
    
    private void atualizarBtnFoursquare(){
    	FoursquareController controller = new FoursquareController(activity);
    	
    	boolean isAuthorized = controller.isAutenticado();
    	
    	Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setVisibility(isAuthorized ? View.GONE : View.VISIBLE);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = FoursquareOAuth.getConnectIntent(MainActivity.this, CLIENT_ID);
                
                if (FoursquareOAuth.isPlayStoreIntent(intent)) {
                    AndroidUtils.showMessageDialog(activity, getString(R.string.foursquare_nao_instalado), false);
                    startActivity(intent);
                } else {
                    startActivityForResult(intent, REQUEST_CODE_FSQ_CONNECT);
                }
            }
        });
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_FSQ_CONNECT:
                onCompleteConnect(resultCode, data);
                break;
                
            case REQUEST_CODE_FSQ_TOKEN_EXCHANGE:
                onCompleteTokenExchange(resultCode, data);
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
    
    private void onCompleteConnect(int resultCode, Intent data) {
        AuthCodeResponse codeResponse = FoursquareOAuth.getAuthCodeFromResult(resultCode, data);
        Exception exception = codeResponse.getException();
        
        if (exception == null) {
            // Success.
            String code = codeResponse.getCode();
            performTokenExchange(code);

        } else {
            if (exception instanceof FoursquareCancelException) {
            	AndroidUtils.showMessage(this, getString(R.string.foursquare_conexao_cancelada));
            } else if (exception instanceof FoursquareDenyException) {
            	AndroidUtils.showMessage(this, getString(R.string.foursquare_conexao_negada));
            } else if (exception instanceof FoursquareOAuthException) {
                // OAuth error.
                String errorMessage = exception.getMessage();
                String errorCode = ((FoursquareOAuthException) exception).getErrorCode();
                AndroidUtils.showMessage(this, errorMessage + " [" + errorCode + "]");
                
            } else if (exception instanceof FoursquareUnsupportedVersionException) {
            	AndroidUtils.showMessage(this, getString(R.string.foursquare_desatualizado));
            } else if (exception instanceof FoursquareInvalidRequestException) {
                // Invalid request.
            	AndroidUtils.showMessage(this, exception.getMessage());
            } else {
                // Error.
            	AndroidUtils.showMessage(this, exception.getMessage());
            }
        }
    }
    
    private void performTokenExchange(String code) {
        Intent intent = FoursquareOAuth.getTokenExchangeIntent(this, CLIENT_ID, CLIENT_SECRET, code);
        startActivityForResult(intent, REQUEST_CODE_FSQ_TOKEN_EXCHANGE);
    }
    
    private void onCompleteTokenExchange(int resultCode, Intent data) {
    	FoursquareController controller = new FoursquareController(activity);
    	AccessTokenResponse tokenResponse = FoursquareOAuth.getTokenFromResult(resultCode, data);
        Exception exception = tokenResponse.getException();
        
        if (exception == null) {
            String accessToken = tokenResponse.getAccessToken();
            // Success.
            AndroidUtils.showMessageDialog(activity, "Access token: " + accessToken, false);
            
            // Persist the token for later use. In this example, we save
            // it to shared prefs.
            controller.gravarToken(accessToken);
            
            // Refresh UI.
            atualizarBtnFoursquare();
            
        } else {
            if (exception instanceof FoursquareOAuthException) {
                // OAuth error.
                String errorMessage = ((FoursquareOAuthException) exception).getMessage();
                String errorCode = ((FoursquareOAuthException) exception).getErrorCode();
                AndroidUtils.showMessageDialog(activity, errorMessage + " [" + errorCode + "]", false);
                
            } else {
                // Other exception type.
            	AndroidUtils.showMessage(activity, exception.getMessage());
            }
        }
    }
}
