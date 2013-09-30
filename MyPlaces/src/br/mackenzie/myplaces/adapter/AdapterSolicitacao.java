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
import br.mackenzie.myplaces.vo.SolicitacoesVO;

public class AdapterSolicitacao extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<SolicitacoesVO> items; 
	private Activity activity;
	
	public AdapterSolicitacao(Activity activity, List<SolicitacoesVO> items) { 
		this.items = items; 
		mInflater = LayoutInflater.from(activity.getApplicationContext());
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
			view = mInflater.inflate(R.layout.lista_solicitacao_item, null); 
			
			itemHolder = new ItemSuporte();
			
			((TextView) view.findViewById(R.id.id)).setVisibility(View.GONE);
			itemHolder.id = ((TextView) view.findViewById(R.id.id));
			itemHolder.nome = ((TextView) view.findViewById(R.id.nome));
			itemHolder.btnAceitar = ((Button) view.findViewById(R.id.btnAceitar));
			itemHolder.btnRecusar = ((Button) view.findViewById(R.id.btnRecusar));
			
			view.setTag(itemHolder); 
		} else { 
			itemHolder = (ItemSuporte) view.getTag(); 
		} 
		
		SolicitacoesVO item = items.get(position);
		
		itemHolder.id.setText(String.valueOf(item.getId())); 
		itemHolder.nome.setText(item.getUsuario().getNome());
		itemHolder.btnAceitar.setOnClickListener(new ButtonAceitarListener(item.getId()));
		itemHolder.btnRecusar.setOnClickListener(new ButtonRecusarListener(item.getId()));
		
		return view;
	}

	private class ItemSuporte { 
		TextView id; 
		TextView nome; 
		Button btnAceitar;
		Button btnRecusar;
    }
	
	private class ButtonAceitarListener implements View.OnClickListener{
		private int idSolicitacao;
		
		public ButtonAceitarListener(int idSolicitacao){
			this.idSolicitacao = idSolicitacao;
		}
		
		@Override
		public void onClick(View arg0) {
			final UsuarioBusiness business = new UsuarioBusiness();
			
        	boolean status;
			try {
				status = business.alterarSolicitacao(idSolicitacao, "aceito");
			
				if (!status){
					AndroidUtils.showMessageDialog(activity, activity.getString(R.string.user_solicitar_aceitar_falha), false);
				}else{
					AndroidUtils.showMessageDialog(activity, activity.getString(R.string.user_solicitar_aceitar_sucesso), false);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private class ButtonRecusarListener implements View.OnClickListener{
		private int idSolicitacao;
		
		public ButtonRecusarListener(int idSolicitacao){
			this.idSolicitacao = idSolicitacao;
		}
		
		@Override
		public void onClick(View arg0) {
			final UsuarioBusiness business = new UsuarioBusiness();
			
        	boolean status;
			try {
				status = business.alterarSolicitacao(idSolicitacao, "n_aceito");
			
				if (!status){
					AndroidUtils.showMessageDialog(activity, activity.getString(R.string.user_solicitar_recusar_falha), false);
				}else{
					AndroidUtils.showMessageDialog(activity, activity.getString(R.string.user_solicitar_recusar_sucesso), false);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
