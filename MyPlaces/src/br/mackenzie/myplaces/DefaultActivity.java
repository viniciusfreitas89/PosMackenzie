package br.mackenzie.myplaces;

import java.io.File;

import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.vo.UsuarioVO;
import br.mackenzie.myplaces.xml.XMLWriter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.LinearLayout.LayoutParams;

public abstract class DefaultActivity extends Activity{
	protected Integer idUsuario;
	
	private Activity activity;
	private ImageView btnSearch;
	private PopupWindow pw;
	
	protected void initDefaultElements(){
		activity = this;
		
		ImageButton imgBtnMenu = (ImageButton) findViewById(R.id.imgBtnMenu);
		imgBtnMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				construirMenus();
			}
		});
		
		btnSearch = (ImageView) findViewById(R.id.imgSearch);
    	btnSearch.setOnClickListener(new ButtonSearchListener());
    	
    	carregarValores();
    }
	
	private void carregarValores(){
    	Bundle bundle = this.getIntent().getExtras(); 
	    if (bundle!=null){
	    	idUsuario = bundle.getInt("idUsuario");
	    }
    }
	
	private void construirMenus(){
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    ImageButton imgBtnMenu = (ImageButton) findViewById(R.id.imgBtnMenu);
	    View pview = inflater.inflate(R.layout.header_menu_btn, null, false);
	    
    	pw = new PopupWindow(
    			pview, 
		        LayoutParams.WRAP_CONTENT,
		        LayoutParams.WRAP_CONTENT, 
		        true);
	    
    	
	    pw.setBackgroundDrawable(new BitmapDrawable());
	    pw.setOutsideTouchable(true);
	    
	    pw.showAsDropDown(imgBtnMenu);
	    
	    
	    Button btnListaAmigos = (Button) pview.findViewById(R.id.btnListaAmigos);
	    btnListaAmigos.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Bundle b = new Bundle();
				b.putInt("idUsuario", idUsuario);
				
				Intent intent = new Intent(getApplicationContext(), AmigosActivity.class);
				intent.putExtras(b);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				
				startActivity(intent);
			}
		});
	    
	    Button btnListarSolicitacoes = (Button) pview.findViewById(R.id.btnListarSolicitacoes);
	    btnListarSolicitacoes.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Bundle b = new Bundle();
				b.putInt("idUsuario", idUsuario);
				
				Intent intent = new Intent(getApplicationContext(), SolicitacoesActivity.class);
				intent.putExtras(b);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				
				startActivity(intent);
			}
		});
	    
	    Button btnTimeline = (Button) pview.findViewById(R.id.btnTimeline);
	    btnTimeline.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Bundle b = new Bundle();
				b.putInt("idUsuario", idUsuario);
				
				Intent intent = new Intent(getApplicationContext(), TabLayoutActivity.class);
				intent.putExtras(b);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				
				startActivity(intent);
			}
		});
	    
	    Button btnLogout = (Button) pview.findViewById(R.id.btnLogout);
	    btnLogout.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				File file = new File(AndroidUtils.getDataDir(activity)+"/"+UsuarioVO.class.getSimpleName()+".xml");
				if (file.exists()){
					file.delete();
				}
				
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				
				startActivity(intent);
			}
		});
	    
	    Button btnSair = (Button) pview.findViewById(R.id.btnSair);
	    btnSair.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
	}
	
	private class ButtonSearchListener implements View.OnClickListener{
		public ButtonSearchListener(){
		}
		
		@Override
		public void onClick(View arg0) {
			Bundle b = new Bundle();
			b.putInt("idUsuario", idUsuario);
			
			Intent intent = new Intent(getApplicationContext(), SearchFriendsActivity.class);
			intent.putExtras(b);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			startActivity(intent);
		}
	}
}
