package jsonpropertiesloader.core;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jsonpropertiesloader.exception.PropertiesLoaderException;
/**
 * 
 * @author santi
 * This class represents a class loader for properties injected from a json file that conforms to the specified format. 
 * This loader allows real-time modifications to the loaded properties but not to those of the file, for now (v1.0). 
 * So reloading the application would mean losing the dynamically added properties.
 */
public final class PropertiesLoader {

	private static final String fileName = "properties.json";
	
	private static final Class<PropertiesLoader> CLAZZ = PropertiesLoader.class;
	
	private static Properties properties;
	
    private static final JSONParser parser = new JSONParser();
    
    private PropertiesLoader() {
    	super();
    }
    
	public static void load() throws PropertiesLoaderException {
		try {
			loadProperties();
		} catch (IOException | ParseException e) {
			throw new PropertiesLoaderException(e);
		}
	}

	private static void loadProperties() throws IOException, ParseException {
		ClassLoader classLoader = CLAZZ.getClassLoader();
        Object obj = parser.parse(new FileReader(classLoader.getResource(fileName).getFile()));
        JSONArray jsonArray =  (JSONArray) obj;
        
        int nProperties = jsonArray.size();
        properties = new Properties();
        for(int i = 0; i < nProperties ; i++) {
        	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String key = (String) jsonObject.get("key");
            String value = (String) jsonObject.get("value");
            properties.put(key, value);
        }
	}
	
	public static Properties getProperties() {
		return properties;
	}
	
}
