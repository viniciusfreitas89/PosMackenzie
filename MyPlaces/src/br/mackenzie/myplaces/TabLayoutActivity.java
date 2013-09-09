package br.mackenzie.myplaces;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import br.com.mackenzie.admv.R;

public class TabLayoutActivity  extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);
        
        TabHost tabHost = getTabHost();
        
        // Tab for Friends Feed
        TabSpec friendspec = tabHost.newTabSpec("Amigos");
        friendspec.setIndicator("Amigos", getResources().getDrawable(R.drawable.icon_friends_tab));
        Intent photosIntent = new Intent(this, FeedCheckinActivity.class);
        friendspec.setContent(photosIntent);
        
        // Tab for Checkin
        TabSpec checkinspec = tabHost.newTabSpec("Checkin");
        // setting Title and Icon for the Tab
        checkinspec.setIndicator("Checkin", getResources().getDrawable(R.drawable.icon_checkin_tab));
        Intent songsIntent = new Intent(this, CheckinActivity.class);
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