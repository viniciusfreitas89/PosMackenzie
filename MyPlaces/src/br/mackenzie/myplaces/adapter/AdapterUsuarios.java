package br.mackenzie.myplaces.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import br.mackenzie.myplaces.R;
import br.mackenzie.myplaces.business.UsuarioBusiness;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.vo.UsuarioVO;

public class AdapterUsuarios extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<UsuarioVO> items; 
	private int idUsuario;
	private Activity activity;
	
	public AdapterUsuarios(Activity activity, List<UsuarioVO> items, int idUsuario) { 
		this.items = items; 
		mInflater = LayoutInflater.from(activity.getApplicationContext());
		this.idUsuario = idUsuario;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int index) {
		return items.get(index);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ItemSuporte itemHolder; 
		if (view == null) { 
			view = mInflater.inflate(R.layout.lista_user_item, null); 
			
			itemHolder = new ItemSuporte();
			
			((TextView) view.findViewById(R.id.id)).setVisibility(View.GONE);
			itemHolder.id = ((TextView) view.findViewById(R.id.id));
			itemHolder.nome = ((TextView) view.findViewById(R.id.nome));
			itemHolder.btnAdicionar = ((Button) view.findViewById(R.id.btnAdicionar));
			
			view.setTag(itemHolder); 
		} else { 
			itemHolder = (ItemSuporte) view.getTag(); 
		} 
		
		UsuarioVO item = items.get(position);
		
		itemHolder.id.setText(String.valueOf(item.getId_usuario())); 
		itemHolder.nome.setText(item.getNome());
		itemHolder.btnAdicionar.setOnClickListener(new ButtonAdicionarListener(item.getId_usuario()));
		
		return view;
	}

	private class ItemSuporte { 
		TextView id; 
		TextView nome; 
		Button btnAdicionar;
    }
	
	private class ButtonAdicionarListener implements View.OnClickListener{
		private int idAmigo;
		
		public ButtonAdicionarListener(int idUsuario){
			this.idAmigo = idUsuario;
		}
		
		@Override
		public void onClick(View arg0) {
			final UsuarioBusiness business = new UsuarioBusiness();
			
        	boolean status;
			try {
				status = business.adicionar(idUsuario, idAmigo);
			
				if (!status){
					AndroidUtils.showMessageDialog(activity, activity.getString(R.string.user_solicitar_amizade_falha), false);
				}else{
					AndroidUtils.showMessageDialog(activity, activity.getString(R.string.user_solicitar_amizade_sucesso), false);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
