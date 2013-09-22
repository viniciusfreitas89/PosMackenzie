package br.mackenzie.myplaces;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import br.mackenzie.myplaces.R;

public class TabLayoutActivity  extends TabActivity {
	private Integer idUsuario;
	
    /** Called when the activity is first created. */
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
    }
}