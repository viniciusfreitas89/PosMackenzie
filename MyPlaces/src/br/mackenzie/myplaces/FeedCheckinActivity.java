package br.mackenzie.myplaces;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import br.mackenzie.myplaces.adapter.AdapterLocais;
import br.mackenzie.myplaces.business.LocaisBusiness;
import br.mackenzie.myplaces.vo.LocalVO;

public class FeedCheckinActivity extends Activity {
	private ListView listView;
	private int idUsuario;
	private boolean loaded = false;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_layout);
        
        listView = (ListView) findViewById(R.id.listCheckin);
        listView.setOnItemClickListener(new ListViewListener(this));
        
        carregarValores();
	    carregarTimeline();
	    loaded = true;
    }
    
    private void carregarValores(){
    	Bundle bundle = this.getIntent().getExtras(); 
	    if (bundle!=null){
	    	idUsuario = bundle.getInt("idUsuario");
	    }
    }
    
    private void carregarTimeline(){
    	LocaisBusiness bus = new LocaisBusiness();
    	
    	try {
			List<LocalVO> lugares = bus.listarPorUsuario(idUsuario);
			if (lugares!=null){
				final AdapterLocais adapter = new AdapterLocais(this.getApplicationContext(), lugares, true);
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
			
			String id = ((TextView)listView.getChildAt(posicao-listView.getFirstVisiblePosition()).findViewById(R.id.idLocal)).getText().toString();
			System.out.println("id: "+id);
		}
	}
    
    @Override
    public void onResume(){
    	super.onResume();
    	if (loaded){
    		carregarTimeline();
    	}
    }
}
