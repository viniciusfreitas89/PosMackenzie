package br.mackenzie.myplaces.xml;

import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * 
 * @author Vinicius
 *
 */
public class XMLWriter<T> {
	private Class<?> type;
	
	public XMLWriter(Class<T> type) { 
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
