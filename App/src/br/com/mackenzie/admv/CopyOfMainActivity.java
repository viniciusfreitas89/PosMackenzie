package br.com.mackenzie.admv;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.foursquare.android.nativeoauth.FoursquareOAuth;

import br.com.mackenzie.admv.foursquare.FoursquareController;
import br.com.mackenzie.admv.location.Localizacao;
import br.com.mackenzie.admv.location.network.NetworkLocation;
import br.com.mackenzie.admv.model.Coordenadas;
import br.com.mackenzie.admv.model.Lugar;
import br.com.mackenzie.admv.utils.AndroidUtils;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * 
 * @author Vinicius
 *
 */
public class CopyOfMainActivity extends Activity {
	
	private Activity activity = null;
	
	FoursquareController controller;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.telalogin);
        
        activity = this;
        
//        controller = FoursquareController.getInstance(this);
//        
//        //Habilita o uso da internet.
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        
//        activity = this;
//        this.eventoElementos();
        
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
    
    private void atualizarBtnFoursquare(){
    	boolean isAuthorized = controller.isAutenticado();
    	
    	Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setVisibility(isAuthorized ? View.GONE : View.VISIBLE);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = FoursquareOAuth.getConnectIntent(CopyOfMainActivity.this, FoursquareController.CLIENT_ID);
                
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
