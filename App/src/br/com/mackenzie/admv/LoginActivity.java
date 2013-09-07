package br.com.mackenzie.admv;

import br.com.mackenzie.admv.Exception.LoginException;
import br.com.mackenzie.admv.dao.UsuarioDAO;
import br.com.mackenzie.admv.model.Usuario;
import br.com.mackenzie.admv.utils.AndroidUtils;
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
			
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usuario = new Usuario(txtEmail.getText().toString(), txtSenha.getText().toString());
			
			try {
				boolean status = dao.fazerLogin(usuario);
				if (status){
					//Avança para a próxima Action
				}
			} catch (LoginException e) {
				e.printStackTrace();
				AndroidUtils.showMessageDialog(activity, e.getMessage(), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
