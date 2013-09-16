package br.mackenzie.myplaces.xml;

import java.io.File;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class XMLReader<T> {
	private Class<?> type;
	
	public XMLReader(Class<T> type) { 
		this.type = type;
	}
	
	@SuppressWarnings("unchecked")
	public T readFile(String path){
		XStream xstream = new XStream(new DomDriver("ISO-8859-1")) {
						  @Override
						  protected MapperWrapper wrapMapper(MapperWrapper next) {
						    return new MapperWrapper(next) {
						      @SuppressWarnings("rawtypes")
						      @Override
						      public boolean shouldSerializeMember(Class definedIn,
						              String fieldName) {
						        if (definedIn == Object.class) {
						          return false;
						        }
						        return super.shouldSerializeMember(definedIn, fieldName);
						      }
						    };
						  }
						};
		
		xstream.alias(this.type.getSimpleName(), type);
		
		if (path != null){
			File file = new File(path);
			if (file.exists()){
				return (T) xstream.fromXML(file);
			}
		}
		
		return null;
	}
}
