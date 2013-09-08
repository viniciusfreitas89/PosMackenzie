package br.mackenzie.myplaces;

import br.com.mackenzie.admv.R;
import br.mackenzie.myplaces.Exception.LoginException;
import br.mackenzie.myplaces.business.UsuarioBusiness;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.utils.Criptografia;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * @author Vinicius
 *
 */
public class LoginActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.telalogin);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        ativarEventos();
    }

	private void ativarEventos() {
		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new ButtonLoginListener(this));
	}
	
	private class ButtonLoginListener implements View.OnClickListener{
		private Activity activity;
		
		public ButtonLoginListener(Activity activity){
			this.activity = activity;
		}
		
		@Override
		public void onClick(View arg0) {
			TextView txtEmail = (TextView) activity.findViewById(R.id.txtEmail);
			TextView txtSenha = (TextView) activity.findViewById(R.id.txtSenha);
			String email = txtEmail.getText().toString();
			String senha = Criptografia.criptografar(txtSenha.getText().toString());
			
			UsuarioBusiness bsn = new UsuarioBusiness();
			
			try {
				bsn.fazerLogin(email, senha);
				
				AndroidUtils.showMessageDialog(activity, "Autenticação OK", false);
				
				
			} catch (LoginException e) {
				AndroidUtils.showMessageDialog(activity, activity.getString(R.string.login_mensagem_erro_atenticacao), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
