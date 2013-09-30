package br.mackenzie.myplaces.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.mackenzie.myplaces.R;
import br.mackenzie.myplaces.vo.UsuarioVO;

public class AdapterAmigos extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<UsuarioVO> items; 
	
	public AdapterAmigos(Context context, List<UsuarioVO> items) { 
		this.items = items; 
		mInflater = LayoutInflater.from(context);
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
			view = mInflater.inflate(R.layout.lista_amigos_item, null); 
			
			itemHolder = new ItemSuporte();
			
			((TextView) view.findViewById(R.id.id)).setVisibility(View.GONE);
			itemHolder.id = ((TextView) view.findViewById(R.id.id));
			itemHolder.nome = ((TextView) view.findViewById(R.id.nome)); 
			
			view.setTag(itemHolder); 
		} else { 
			itemHolder = (ItemSuporte) view.getTag(); 
		} 
		
		UsuarioVO item = items.get(position);
		
		itemHolder.id.setText(String.valueOf(item.getId())); 
		itemHolder.nome.setText(item.getNome());
		
		return view;
	}

	private class ItemSuporte { 
		TextView id; 
		TextView nome; 
    }
}
