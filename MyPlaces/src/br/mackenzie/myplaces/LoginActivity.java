package br.mackenzie.myplaces;

import br.mackenzie.myplaces.R;
import br.mackenzie.myplaces.Exception.LoginException;
import br.mackenzie.myplaces.business.UsuarioBusiness;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.utils.Criptografia;
import br.mackenzie.myplaces.vo.UsuarioVO;
import br.mackenzie.myplaces.xml.XMLWriter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	private TextView txtEmail;
	private TextView txtSenha;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_layout);
        
        inicializarElementos();
        inicializarEventos();
    }
    
    private void inicializarElementos(){
    	txtEmail = (TextView) this.findViewById(R.id.txtEmail);
		txtSenha = (TextView) this.findViewById(R.id.txtSenha);
    }

	private void inicializarEventos() {
		Button btnLogin = (Button) findViewById(R.id.btnlogin);
		btnLogin.setOnClickListener(new ButtonLoginListener(this));
		
		Button btncadastro = (Button) findViewById(R.id.btncadastro);
		btncadastro.setOnClickListener(new ButtonCadastroListener());
	}
	
	private class ButtonLoginListener implements View.OnClickListener{
		private Activity activity;
		
		public ButtonLoginListener(Activity activity){
			this.activity = activity;
		}
		
		@Override
		public void onClick(View arg0) {
			String email = txtEmail.getText().toString();
			String senha = Criptografia.criptografar(txtSenha.getText().toString());
			
			UsuarioBusiness bsn = new UsuarioBusiness();
			
			try {
				UsuarioVO vo = bsn.fazerLogin(email, senha);
				salvarDados(vo);
				
				Bundle b = new Bundle();
				b.putInt("idUsuario", vo.getId());
				
				Intent intent = new Intent(getApplicationContext(), TabLayoutActivity.class);
				intent.putExtras(b);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				
				startActivity(intent);
			} catch (LoginException e) {
				AndroidUtils.showMessageDialog(activity, activity.getString(R.string.login_mensagem_erro_atenticacao), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void salvarDados(UsuarioVO vo){
		XMLWriter<UsuarioVO> writer = new XMLWriter<UsuarioVO>(UsuarioVO.class);
		writer.writeFile(vo, AndroidUtils.getDataDir(this)+"/"+UsuarioVO.class.getSimpleName()+".xml");
	}
	
	private class ButtonCadastroListener implements View.OnClickListener{
		
		public ButtonCadastroListener(){
		}
		
		@Override
		public void onClick(View arg0) {
			String email = txtEmail.getText().toString();
			
			Bundle b = new Bundle();
			b.putString("email", email);
			
			Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
			intent.putExtras(b);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			startActivity(intent);
		}
	}
}
