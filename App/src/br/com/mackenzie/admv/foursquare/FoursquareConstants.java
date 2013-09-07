package br.com.mackenzie.admv.foursquare;

public interface FoursquareConstants {
	String version = "20130831";
	int 	REQUEST_CODE_FSQ_CONNECT 			= 200;
	int 	REQUEST_CODE_FSQ_TOKEN_EXCHANGE 	= 201;
	String 	CLIENT_ID 							= "ZFH1KCRLDWBBDAPUIAQARFURLRFE4FRP35RHVPQ1OZPE4YR5";
	String 	CLIENT_SECRET 						= "5NYDBENTBWB0TG5GKK1WQYU2PTDTZJKEI3OTJFVGIY3DIE0I";
	
	/******* URLs *******/
	//https://developer.foursquare.com/docs/venues/explore
	String 	URL_EXPLORE							= "https://api.foursquare.com/v2/venues/explore?ll=%1$s&oauth_token=%2$s&v="+version;
	//https://developer.foursquare.com/docs/checkins/checkins
	String URL_CHECKIN							= "https://api.foursquare.com/v2/checkins/%1$s?signature=%2$soauth_token=%3$s&v="+version;
	//https://developer.foursquare.com/docs/venues/venues
	String URL_VENUE_DETAIL						= "https://api.foursquare.com/v2/checkins/add?venueId=%1$s&oauth_token=%2$s&v="+version;
	//https://developer.foursquare.com/docs/users/users
	String URL_USER_DETAIL						= "https://api.foursquare.com/v2/users/self?oauth_token=%1$s&v="+version;
}
