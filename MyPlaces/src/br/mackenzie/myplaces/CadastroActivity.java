package br.mackenzie.myplaces;

import br.mackenzie.myplaces.R;
import br.mackenzie.myplaces.Exception.UsuarioException;
import br.mackenzie.myplaces.business.UsuarioBusiness;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.utils.Criptografia;
import android.app.Activity;
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
public class CadastroActivity extends Activity {
	private TextView txtNome;
	private TextView txtEmail;
	private TextView txtSenha;
	private TextView txtCSenha;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cadastro_layout);
        
        inicializarElementos();
        inicializarEventos();
    }
    
    private void inicializarElementos(){
    	txtNome = (TextView) this.findViewById(R.id.txt_nome);
		txtEmail = (TextView) this.findViewById(R.id.txtEmail);
		txtSenha = (TextView) this.findViewById(R.id.txtPassword);
		txtCSenha = (TextView) this.findViewById(R.id.txtRepeatPassword);
		
		Bundle bundle = this.getIntent().getExtras(); 
	    if (bundle!=null){
	    	txtEmail.setText(bundle.getString("email") != null ? bundle.getString("email") : "");
	    }
    }

	private void inicializarEventos() {
		Button btnLogin = (Button) findViewById(R.id.btn_criar_conta);
		btnLogin.setOnClickListener(new ButtonCadastrarListener(this));
	}
	
	private class ButtonCadastrarListener implements View.OnClickListener{
		private Activity activity;
		
		public ButtonCadastrarListener(Activity activity){
			this.activity = activity;
		}
		
		@Override
		public void onClick(View arg0) {
			String email = txtEmail.getText().toString();
			String nome = txtNome.getText().toString();
			String senha = Criptografia.criptografar(txtSenha.getText().toString());
			String cSenha = Criptografia.criptografar(txtCSenha.getText().toString());
			
			if (!senha.equals(cSenha)){
				AndroidUtils.showMessageDialog(activity, activity.getString(R.string.cadastro_msg_senha_nao_confere), false);
				return;
			}
			
			UsuarioBusiness bsn = new UsuarioBusiness();
			
			try {
				bsn.cadastrar(nome, email, senha, cSenha);
				AndroidUtils.showMessageDialog(activity, activity.getString(R.string.cadastro_msg_realizado_sucesso), false);
			} catch (UsuarioException e) {
				AndroidUtils.showMessageDialog(activity, e.getMessage(), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
