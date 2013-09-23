package br.mackenzie.myplaces;

import java.util.List;

import br.mackenzie.myplaces.Exception.LoginException;
import br.mackenzie.myplaces.adapter.AdapterCategorias;
import br.mackenzie.myplaces.business.CategoriasBusiness;
import br.mackenzie.myplaces.business.LocaisBusiness;
import br.mackenzie.myplaces.location.Localizacao;
import br.mackenzie.myplaces.location.network.NetworkLocation;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.vo.CategoriaVO;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class CheckinCadastroActivity extends Activity {
    private ListView listView ;
    private EditText txtNomeLocal;
    private EditText txtValorGasto;
    private Button btnCadastrar;
    
    private Localizacao localizacao;
    private Integer idCategoria;
    
    private Integer idUsuario;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.checkin_cadastro_local_layout);
        
        localizacao = new NetworkLocation(this);
        
        carregarValores();
        inicializarElementos();
        inicializarEventos();
        carregarCategorias();
    }
    
    private void carregarValores(){
    	Bundle bundle = this.getIntent().getExtras(); 
	    if (bundle!=null){
	    	idUsuario = bundle.getInt("idUsuario");
	    }
    }
    
    private void inicializarElementos(){
    	listView = (ListView) findViewById(R.id.listCategorias);
    	txtNomeLocal = (EditText) this.findViewById(R.id.txtNomeLocal);
    	txtValorGasto = (EditText) this.findViewById(R.id.txtValorGasto);
    	btnCadastrar = (Button) this.findViewById(R.id.btnCadastrar);
    }
    
    private void inicializarEventos() {
    	listView.setOnItemClickListener(new ListViewListener(this));
    	btnCadastrar.setOnClickListener(new ButtonCadastroListener(this));
	}
    
    private void carregarCategorias(){
    	CategoriasBusiness bus = new CategoriasBusiness();
    	
    	try {
			List<CategoriaVO> categorias = bus.listar();
			if (categorias!=null){
				AdapterCategorias adapter = new AdapterCategorias(this.getApplicationContext(), categorias);
				listView.setAdapter(adapter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
  //Listener para o Click da lista
    private class ListViewListener implements OnItemClickListener{
		private Activity activity;
		
		public ListViewListener(Activity activity){
			this.activity = activity;
		}
		
		@Override
		public void onItemClick(final AdapterView<?> listView, View view, final int posicao,
				long arg3) {
			String id = ((TextView)listView.getChildAt(posicao-listView.getFirstVisiblePosition()).findViewById(R.id.id)).getText().toString();
			String nome = ((TextView)listView.getChildAt(posicao-listView.getFirstVisiblePosition()).findViewById(R.id.nome)).getText().toString();
			
			if (id!=null && !id.isEmpty()){
				idCategoria = Integer.parseInt(id);
				AndroidUtils.showMessage(activity.getApplicationContext(), "'"+nome+"' Selecionado.");
			}
		}
	}
    
    private class ButtonCadastroListener implements View.OnClickListener{
		private Activity activity;

		public ButtonCadastroListener(Activity activity){
			this.activity = activity;
		}

		@Override
		public void onClick(View arg0) {
			String nome = txtNomeLocal.getText().toString();
			String valor = txtValorGasto.getText().toString();

			if (nome.isEmpty()){
				AndroidUtils.showMessageDialog(activity, activity.getString(R.string.checkin_msg_nome_obritorio), false);
				return;
			}
			
			if (valor.isEmpty()){
				AndroidUtils.showMessageDialog(activity, activity.getString(R.string.checkin_msg_valor_obritorio), false);
				return;
			}
			
			if (idCategoria == null){
				AndroidUtils.showMessageDialog(activity, activity.getString(R.string.checkin_msg_categoria_obritorio), false);
				return;
			}

			float vlr = 0;
			if (valor.contains(",")){
				vlr = Float.valueOf(valor.replace(".", "").replace(",", "."));
			}else{
				vlr = Float.valueOf(valor);
			}

			LocaisBusiness bsn = new LocaisBusiness();
			try {
				Integer idLocal = bsn.inserir(nome, 
									  localizacao.getMinhaLocalizacao().getLatitude(), 
									  localizacao.getMinhaLocalizacao().getLongitude(), 
									  idCategoria);

				if (idLocal!=null){
					if (bsn.checkin(idUsuario, idLocal, vlr)){
						AndroidUtils.showMessage(activity.getApplicationContext(), activity.getString(R.string.checkin_msg_sucesso));
						
						activity.finish();
					}else{
						AndroidUtils.showMessageDialog(activity, activity.getString(R.string.checkin_msg_falha), false);
					}
				}else{
					AndroidUtils.showMessageDialog(activity, activity.getString(R.string.checkin_msg_selecionar_local), false);
				}
				
				idCategoria = null;
			} catch (LoginException e) {
				AndroidUtils.showMessageDialog(activity, activity.getString(R.string.login_mensagem_erro_atenticacao), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
