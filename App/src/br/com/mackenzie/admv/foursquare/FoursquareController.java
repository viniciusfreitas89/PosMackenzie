package br.com.mackenzie.admv.foursquare;

import java.io.File;

import android.app.Activity;
import br.com.mackenzie.admv.foursquare.xml.FoursquareData;
import br.com.mackenzie.admv.utils.AndroidUtils;
import br.com.mackenzie.admv.xml.persistence.XMLReader;
import br.com.mackenzie.admv.xml.persistence.XMLWriter;

public class FoursquareController {
	private String pathXml;
	
	public FoursquareController(Activity activity){
		pathXml = AndroidUtils.getDataDir(activity)+"/"+FoursquareData.class.getSimpleName()+".xml";
	}
	
	public boolean gravarToken(String token){
		XMLWriter<FoursquareData> reader = new XMLWriter<FoursquareData>(FoursquareData.class);
		FoursquareData data = new FoursquareData();
		data.setToken(token);
		
		return reader.writeFile(data, pathXml);
	}
	
	public boolean isAutenticado(){
		XMLReader<FoursquareData> reader = new XMLReader<FoursquareData>(FoursquareData.class);
		FoursquareData f = reader.readFile(pathXml);
		
		return f!=null && f.getToken()!=null && !f.getToken().isEmpty() ? true : false;
	}
	
	public void limparDadosUsuario(){
		File file = new File(pathXml);
		if (file.exists()){
			file.delete();
		}
	}
}
