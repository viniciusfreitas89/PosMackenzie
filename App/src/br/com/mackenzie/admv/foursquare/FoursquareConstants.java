package br.com.mackenzie.admv.foursquare;

public interface FoursquareConstants {
	int 	REQUEST_CODE_FSQ_CONNECT 			= 200;
	int 	REQUEST_CODE_FSQ_TOKEN_EXCHANGE 	= 201;
	String 	CLIENT_ID 							= "ZFH1KCRLDWBBDAPUIAQARFURLRFE4FRP35RHVPQ1OZPE4YR5";
	String 	CLIENT_SECRET 						= "5NYDBENTBWB0TG5GKK1WQYU2PTDTZJKEI3OTJFVGIY3DIE0I";
	
	/******* URLs *******/
	
	//https://developer.foursquare.com/docs/venues/explore
	String 	URL_EXPLORE							= "https://api.foursquare.com/v2/venues/explore?ll=%1$s&oauth_token=%2$s&v=20130831";
	//https://developer.foursquare.com/docs/checkins/add
	String URL_CHECKIN							= "https://api.foursquare.com/v2/checkins/add?venueId=%1$s";
	//https://developer.foursquare.com/docs/venues/venues
	String URL_VENUE_DETAIL						= "https://api.foursquare.com/v2/venues/VENUE_ID=%1$s";
}
