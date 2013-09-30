package br.mackenzie.myplaces;


import java.util.List;

import br.mackenzie.myplaces.adapter.AdapterUsuarios;
import br.mackenzie.myplaces.business.UsuarioBusiness;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.vo.UsuarioVO;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchFriendsActivity extends DefaultActivity {
	private ImageButton btnBuscar;
	private ListView listView;
	private EditText txtChave;
	private Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_friends_layout);
		
		activity = this;
		
		addEvent();
		initDefaultElements();
	}
	
	private void addEvent(){
		btnBuscar = (ImageButton) this.findViewById(R.id.bIgmBuscar);
		btnBuscar.setOnClickListener(new ButtonSearchListener());
		
		listView = (ListView) this.findViewById(R.id.list_usuarios);
		txtChave = (EditText) this.findViewById(R.id.txtChave);
	}
	
	private void buscarUsuarios(){
		UsuarioBusiness bsn = new UsuarioBusiness();
		
		try {
			List<UsuarioVO> list = bsn.buscar(txtChave.getText().toString());
			if (list!=null){
				AdapterUsuarios adapter = new AdapterUsuarios(this, list, idUsuario);
				listView.setAdapter(adapter);
			}
		} catch (Exception e) {
			AndroidUtils.showMessageDialog(this, e.getMessage(), false);
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private class ButtonSearchListener implements View.OnClickListener{
		public ButtonSearchListener(){
		}
		
		@Override
		public void onClick(View arg0) {
			if (!txtChave.getText().toString().isEmpty()){
				buscarUsuarios();
			}else{
				AndroidUtils.showMessageDialog(activity, getString(R.string.user_busca_filtro_vazio), false);
			}
		}
	}
}

