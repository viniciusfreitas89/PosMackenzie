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
import br.mackenzie.myplaces.vo.CategoriaVO;
import br.mackenzie.myplaces.vo.GastoVO;

public class AdapterRelatorio extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<GastoVO> items; 
	
	public AdapterRelatorio(Context context, List<GastoVO> items) { 
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
		DecimalFormat f = new DecimalFormat("##0.00");
		
		if (view == null) { 
			view = mInflater.inflate(R.layout.lista_relatorio_item, null); 
			
			itemHolder = new ItemSuporte();
			
			itemHolder.categoria = ((TextView) view.findViewById(R.id.categoria));
			itemHolder.valorGasto = ((TextView) view.findViewById(R.id.valor_gasto)); 
			
			view.setTag(itemHolder); 
		} else { 
			itemHolder = (ItemSuporte) view.getTag(); 
		} 
		
		GastoVO item = items.get(position);
		
		itemHolder.categoria.setText(item.getCategoria()); 
		itemHolder.valorGasto.setText("R$ "+f.format(item.getTotal()));
		
		return view;
	}

	private class ItemSuporte { 
		TextView categoria; 
		TextView valorGasto; 
    }
}
