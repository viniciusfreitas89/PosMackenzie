package br.mackenzie.myplaces;

import java.util.List;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import br.mackenzie.myplaces.Exception.LoginException;
import br.mackenzie.myplaces.adapter.AdapterLocais;
import br.mackenzie.myplaces.business.LocaisBusiness;
import br.mackenzie.myplaces.location.Localizacao;
import br.mackenzie.myplaces.location.network.NetworkLocation;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.vo.LocalVO;

public class CheckinActivity  extends Activity {
	private ListView listView;
	private TextView txtNomeLocal;
	private TextView txtGasto;
	
	private Localizacao localizacao;
	private int 	idUsuario;
	private Integer idLocal;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin_layout);
        
        localizacao = new NetworkLocation(this);
        inicializarElementos();
        carregarValores();
        carregarLugares();
        inicializarEventos();
    }
	
	private void inicializarElementos(){
		listView = (ListView) findViewById(R.id.listaLocais);
		listView.setOnItemClickListener(new ListViewListener(this));
		
		txtNomeLocal = (TextView) this.findViewById(R.id.txtNomeLocal);
		txtGasto = (TextView) this.findViewById(R.id.txtValorGasto);
    }
	
	private void carregarValores(){
    	Bundle bundle = this.getIntent().getExtras(); 
	    if (bundle!=null){
	    	idUsuario = bundle.getInt("idUsuario");
	    }
    }
	 
	private void carregarLugares(){
    	LocaisBusiness bus = new LocaisBusiness();
    	
    	try {
			List<LocalVO> lugares = bus.listarTodos();
			if (lugares!=null){
				final AdapterLocais adapter = new AdapterLocais(this.getApplicationContext(), lugares);
				listView.setAdapter(adapter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	 
	private void inicializarEventos() {
		Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
		btnCadastrar.setOnClickListener(new ButtonCadastroListener(this));
	}
	 
	private void visualizarCheckins(){
		TabActivity parent = (TabActivity) getParent();
		TabHost tabhost = parent.getTabHost();
		if (tabhost!=null){
			tabhost.setCurrentTab(0);
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
			String valor = txtGasto.getText().toString();
			
			if (valor.isEmpty()){
				AndroidUtils.showMessageDialog(activity, activity.getString(R.string.checkin_msg_valor_obritorio), false);
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
				if (!nome.isEmpty()){
					idLocal = bsn.inserir(nome, 
									  localizacao.getMinhaLocalizacao().getLatitude(), 
									  localizacao.getMinhaLocalizacao().getLongitude(), 
									  1);
				}
				
				if (idLocal!=null){
					if (bsn.checkin(idUsuario, idLocal, vlr)){
						AndroidUtils.showMessageDialog(activity, activity.getString(R.string.checkin_msg_sucesso), false);
						visualizarCheckins();
					}else{
						AndroidUtils.showMessageDialog(activity, activity.getString(R.string.checkin_msg_falha), false);
					}
				}else{
					AndroidUtils.showMessageDialog(activity, activity.getString(R.string.checkin_msg_selecionar_local), false);
				}
				
				idLocal = null;
			} catch (LoginException e) {
				AndroidUtils.showMessageDialog(activity, activity.getString(R.string.login_mensagem_erro_atenticacao), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			String id = ((TextView)listView.getChildAt(posicao-listView.getFirstVisiblePosition()).findViewById(R.id.idLocal)).getText().toString();
			
			if (id!=null && !id.isEmpty()){
				idLocal = Integer.parseInt(id);
			}
		}
	}
}
