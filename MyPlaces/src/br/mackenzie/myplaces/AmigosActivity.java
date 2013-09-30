package br.mackenzie.myplaces;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import br.mackenzie.myplaces.adapter.AdapterAmigos;
import br.mackenzie.myplaces.business.UsuarioBusiness;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.vo.UsuarioVO;


public class AmigosActivity extends DefaultActivity{
    private ListView listView ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.amizades_layout);
        
        inicializarElementos();
        inicializarEventos();
        carregarAmigos();
    }
    
    private void inicializarElementos(){
    	listView = (ListView) findViewById(R.id.listAmigos);
    	initDefaultElements();
    }
    
    private void inicializarEventos() {
    	listView.setOnItemClickListener(new ListViewListener(this));
	}
    
    private void carregarAmigos(){
    	UsuarioBusiness bus = new UsuarioBusiness();
    	
    	try {
			List<UsuarioVO> amigos = bus.listarAmigos(idUsuario);
			if (amigos!=null){
				AdapterAmigos adapter = new AdapterAmigos(this.getApplicationContext(), amigos);
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
				AndroidUtils.showMessage(activity.getApplicationContext(), "'"+nome+"' Selecionado.");
			}
		}
	}
    
}
