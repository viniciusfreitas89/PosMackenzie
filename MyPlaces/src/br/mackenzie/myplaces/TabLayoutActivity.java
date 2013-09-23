package br.mackenzie.myplaces;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
=======
import android.view.Window;
>>>>>>> d7f6953322704f321a19ed8a3ba536dbd45381c0
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import br.mackenzie.myplaces.R;

public class TabLayoutActivity  extends TabActivity {
	private ImageView btnSearch;
	private Integer idUsuario;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab_layout);
        
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
        TabSpec profilespec = tabHost.newTabSpec("Perfil");
        profilespec.setIndicator("Perfil", getResources().getDrawable(R.drawable.icon_profile_tab));
        Intent videosIntent = new Intent(this, ProfileActivity.class);
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
    }
    
    private class ButtonSearchListener implements View.OnClickListener{
		public ButtonSearchListener(){
		}
		
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(getApplicationContext(), SearchFriendsActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			startActivity(intent);
		}
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