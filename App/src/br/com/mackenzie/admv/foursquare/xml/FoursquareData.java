package br.com.mackenzie.admv.foursquare.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author Vinicius
 *
 */
@XStreamAlias("FoursquareData")
public class FoursquareData {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
