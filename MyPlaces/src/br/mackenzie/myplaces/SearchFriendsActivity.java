<<<<<<< HEAD
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchFriendsActivity extends Activity {
	private ImageButton btnBuscar;
	private ListView listView;
	private EditText txtChave;
	private Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_friends_layout);
		
		activity = this;
		
		addEvent();
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
				AdapterUsuarios adapter = new AdapterUsuarios(this.getApplicationContext(), list);
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

=======
package br.mackenzie.myplaces;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SearchFriendsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_friends_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

>>>>>>> d7f6953322704f321a19ed8a3ba536dbd45381c0
