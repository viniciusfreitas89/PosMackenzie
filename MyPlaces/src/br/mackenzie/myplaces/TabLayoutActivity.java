package br.mackenzie.myplaces;


import java.io.File;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.LinearLayout.LayoutParams;
import br.mackenzie.myplaces.R;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.vo.UsuarioVO;

public class TabLayoutActivity  extends TabActivity {
	private ImageView btnSearch;
	private Integer idUsuario;
	private PopupWindow pw;
	private Activity activity;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab_layout);
        
        activity = this;
        
        Bundle bundle = this.getIntent().getExtras(); 
	    if (bundle!=null){
	    	this.idUsuario = bundle.getInt("idUsuario");
	    }
	    Bundle b = new Bundle();
		b.putInt("idUsuario", idUsuario);
		
		
        TabHost tabHost = getTabHost();
        
        // Tab for Friends Feed
        TabSpec friendspec = tabHost.newTabSpec("Timeline");
        friendspec.setIndicator("Timeline", getResources().getDrawable(R.drawable.icon_friends_tab));
        Intent photosIntent = new Intent(this, FeedCheckinActivity.class);
        photosIntent.putExtras(b);
        friendspec.setContent(photosIntent);
        
        // Tab for Checkin
        TabSpec checkinspec = tabHost.newTabSpec("Checkin");
        // setting Title and Icon for the Tab
        checkinspec.setIndicator("Checkin", getResources().getDrawable(R.drawable.icon_checkin_tab));
        Intent songsIntent = new Intent(this, CheckinActivity.class);
        songsIntent.putExtras(b);
        checkinspec.setContent(songsIntent);
        
        // Tab for Profile
        TabSpec profilespec = tabHost.newTabSpec("Relatório");
        profilespec.setIndicator("Relatório", getResources().getDrawable(R.drawable.icon_profile_tab));
        Intent videosIntent = new Intent(this, ProfileActivity.class);
        videosIntent.putExtras(b);
        profilespec.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(friendspec); // Adding friends tab
        tabHost.addTab(checkinspec); // Adding checkin tab
        tabHost.addTab(profilespec); // Adding profile tab
        
        addEvents();
    }
    
    
    private void addEvents(){
    	btnSearch = (ImageView) findViewById(R.id.imgSearch);
    	btnSearch.setOnClickListener(new ButtonSearchListener());
    	
    	ImageButton imgBtnMenu = (ImageButton) findViewById(R.id.imgBtnMenu);
		imgBtnMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				criarMenu();
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
    
    private void criarMenu(){
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
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
			android.os.Process.killProcess(android.os.Process.myPid());
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}