package br.mackenzie.myplaces.adapter;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.mackenzie.myplaces.R;
import br.mackenzie.myplaces.vo.LocalVO;

public class AdapterLocais extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<LocalVO> itens; 
	
	public AdapterLocais(Context context, List<LocalVO> itens) { 
		this.itens = itens; 
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public Object getItem(int index) {
		return itens.get(index);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ItemSuporte itemHolder; 
		DecimalFormat f = new DecimalFormat("##0.00");
		
		if (view == null) { 
			view = mInflater.inflate(R.layout.lista_checkin_item, null); 
			
			itemHolder = new ItemSuporte();
			
			((TextView) view.findViewById(R.id.idLocal)).setVisibility(View.GONE);
			itemHolder.idLocal = ((TextView) view.findViewById(R.id.idLocal));
			itemHolder.local = ((TextView) view.findViewById(R.id.nome_local)); 
			itemHolder.valor = ((TextView) view.findViewById(R.id.valor_gasto)); 
			itemHolder.categoria = ((TextView) view.findViewById(R.id.categoria_local));
			
			view.setTag(itemHolder); 
		} else { 
			itemHolder = (ItemSuporte) view.getTag(); 
		} 
		
		LocalVO item = itens.get(position);
		String valor = "0,00";
		if (item.getValor_gasto()!=null){
			valor = f.format(item.getValor_gasto());
		}
		if (item.getId_local()!=null){
			itemHolder.idLocal.setText(item.getId_local().toString());
		}else{
			//TODO: Remover esse ELSE
			itemHolder.idLocal.setText("1");
		}
		
		itemHolder.local.setText(item.getLocal()); 
		itemHolder.valor.setText("R$ "+valor);
		itemHolder.categoria.setText(item.getCategoria().getNome());
		
		return view;
	}

	private class ItemSuporte { 
		TextView idLocal; 
		TextView local; 
    	TextView valor; 
    	TextView categoria; 
    }
}
