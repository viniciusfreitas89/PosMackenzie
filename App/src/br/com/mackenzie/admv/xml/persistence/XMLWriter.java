package br.com.mackenzie.admv.xml.persistence;

import java.io.FileOutputStream;
import java.lang.reflect.ParameterizedType;

import br.com.mackenzie.admv.foursquare.xml.FoursquareData;
import br.com.mackenzie.admv.utils.AndroidUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * 
 * @author Vinicius
 *
 */
public class XMLWriter<T> {
	private Class<?> type;
	
	public XMLWriter(Class<?> type) { 
		this.type = type;
	}
	
	public boolean writeFile(T object, String path){
		try {
			XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
			
			xstream.alias(this.type.getSimpleName(), this.type);
			
			if (path != null){
	            FileOutputStream fs = new FileOutputStream(path);
	            xstream.toXML(object, fs);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
