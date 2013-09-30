package br.mackenzie.myplaces.adapter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.mackenzie.myplaces.R;
import br.mackenzie.myplaces.utils.Utils;
import br.mackenzie.myplaces.vo.LocalVO;

public class AdapterLocais extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<LocalVO> itens; 
	private boolean showValorGasto;
	
	public AdapterLocais(Context context, List<LocalVO> itens, boolean showValorGasto) { 
		this.itens = itens; 
		this.showValorGasto = showValorGasto;
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
			
			itemHolder.idLocal = ((TextView) view.findViewById(R.id.idLocal));
			itemHolder.nomeUsuario = ((TextView) view.findViewById(R.id.nome_usuario)); 
			itemHolder.local = ((TextView) view.findViewById(R.id.nome_local)); 
			itemHolder.valor = ((TextView) view.findViewById(R.id.valor_gasto)); 
			itemHolder.categoria = ((TextView) view.findViewById(R.id.categoria_local));
			
			itemHolder.idLocal.setVisibility(View.GONE);
			if (!showValorGasto){
				itemHolder.local.setTypeface(null, Typeface.BOLD);
				
				itemHolder.nomeUsuario.setVisibility(View.GONE);
				itemHolder.valor.setVisibility(View.GONE);
			}
			
			view.setTag(itemHolder); 
		} else { 
			itemHolder = (ItemSuporte) view.getTag(); 
		} 
		
		LocalVO item = itens.get(position);
		if (showValorGasto){
			String valor = "0,00";
			if (item.getValor_gasto()!=null){
				valor = f.format(item.getValor_gasto());
			}
			
			itemHolder.valor.setText("R$ "+valor);
		}
		
		if (item.getId_local()!=null){
			itemHolder.idLocal.setText(item.getId_local().toString());
		}
		if (item.getUsuario()!=null){
			itemHolder.nomeUsuario.setText(item.getUsuario().getNome());
		}
		String data = "";
		if (item.getData_registro()!=null){
			Date date = Utils.stringToDate(item.getData_registro(), "yyyy-MM-dd HH:mm:ss");
			data = " - " +new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
		}
		itemHolder.local.setText(item.getLocal() + data); 
		itemHolder.categoria.setText(item.getCategoria().getNome());
		
		return view;
	}

	private class ItemSuporte { 
		TextView idLocal;
		TextView nomeUsuario;
		TextView local; 
    	TextView valor; 
    	TextView categoria; 
    }
}
