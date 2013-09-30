package br.mackenzie.myplaces;

import java.util.List;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.location.LocationManager;
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
import br.mackenzie.myplaces.location.gps.GPSLocation;
import br.mackenzie.myplaces.location.network.NetworkLocation;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.vo.LocalVO;

public class CheckinActivity  extends Activity {
	private static final int ACT_LOCAL_CADASTRO = 1;  // The request code
	private ListView listView;
	private TextView txtGasto;
	private Button btnCadastrarLocal;
	private Button btnCheckin;
	
	private Localizacao localizacao;
	private int 	idUsuario;
	private Integer idLocal;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin_layout);
        
        localizacao = new GPSLocation(this);
        if (!localizacao.isProvedorAtivo()){
        	localizacao = new NetworkLocation(this);
        	if (!localizacao.isProvedorAtivo()){
            	AndroidUtils.showMessageDialog(this, "Esse dispositvio não possui configurações necessários para consultar o local atual.\nPor favor, ative o GPS ou sua conexão com a internet.", false);
            }else{
            	AndroidUtils.showMessage(this, "Localização pela rede.");
            }
        }else{
        	AndroidUtils.showMessage(this, "Localização por GPS");
        }
        
        inicializarElementos();
        carregarValores();
        carregarLugares();
        inicializarEventos();
    }
	
	private void inicializarElementos(){
		listView = (ListView) findViewById(R.id.listaLocais);
		listView.setOnItemClickListener(new ListViewListener(this));
		
		txtGasto = (TextView) this.findViewById(R.id.txtValorGasto);
		
		btnCheckin = (Button) findViewById(R.id.btnCheckin);
		btnCadastrarLocal = (Button) findViewById(R.id.btnCadastrarLocal);
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
			List<LocalVO> lugares = bus.listarTodos(20, 
													localizacao.getMinhaLocalizacao().getLatitude(), 
													localizacao.getMinhaLocalizacao().getLongitude());
			if (lugares!=null){
				final AdapterLocais adapter = new AdapterLocais(this.getApplicationContext(), lugares, false);
				listView.setAdapter(adapter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	 
	private void inicializarEventos() {
		btnCheckin.setOnClickListener(new ButtonCheckinListener(this));
		btnCadastrarLocal.setOnClickListener(new ButtonCadastroLocalListener(this));
	}
	 
	private void visualizarCheckins(){
		TabActivity parent = (TabActivity) getParent();
		TabHost tabhost = parent.getTabHost();
		if (tabhost!=null){
			tabhost.setCurrentTab(0);
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
			String nome = ((TextView)listView.getChildAt(posicao-listView.getFirstVisiblePosition()).findViewById(R.id.nome_local)).getText().toString();
			
			if (id!=null && !id.isEmpty()){
				idLocal = Integer.parseInt(id);
				AndroidUtils.showMessage(activity.getApplicationContext(), "'"+nome+"' Selecionado.");
			}
		}
	}
    
    private class ButtonCheckinListener implements View.OnClickListener{
		private Activity activity;

		public ButtonCheckinListener(Activity activity){
			this.activity = activity;
		}

		@Override
		public void onClick(View arg0) {
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
				if (idLocal!=null){
					if (bsn.checkin(idUsuario, idLocal, vlr)){
						AndroidUtils.showMessage(activity.getApplicationContext(), activity.getString(R.string.checkin_msg_sucesso));
						
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
    
    private class ButtonCadastroLocalListener implements View.OnClickListener{
		private Activity activity;
		
		public ButtonCadastroLocalListener(Activity activity){
			this.activity = activity;
		}
		
		@Override
		public void onClick(View arg0) {
			Bundle b = new Bundle();
			b.putInt("idUsuario", idUsuario);
			
			Intent intent = new Intent(getApplicationContext(), CheckinCadastroActivity.class);
			intent.putExtras(b);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			startActivityForResult(intent, ACT_LOCAL_CADASTRO);
		}
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACT_LOCAL_CADASTRO:
            	visualizarCheckins();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
    
}
