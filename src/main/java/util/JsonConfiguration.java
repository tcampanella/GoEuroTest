package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
public class JsonConfiguration {

    private static Properties configurationProperties = new Properties();
    private final String STANDARD_CONFIGURATION_FILE_NAME = "configuration.properties";
    
    
    public Properties getConfigurationProperties() {
        return configurationProperties;
    }

    public JsonConfiguration () {

    	loadConfigurationFile(STANDARD_CONFIGURATION_FILE_NAME);

    }
    
    public JsonConfiguration (String configurationFileName) {
    	
    	loadConfigurationFile(configurationFileName);
    }

    private void loadConfigurationFile(String configurationFileName) {

            InputStream input = null;

            try {
            	
                input = this.getClass().getClassLoader().getResourceAsStream("configuration/"+ configurationFileName);

                // load properties file
                configurationProperties.load(new InputStreamReader(input, "UTF-8"));

                // get the property value and print it out
                //System.out.println(prop.getProperty("URL"));

            } catch (IOException ex) {
                // Should not happen
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        // Should not happen
                        e.printStackTrace();
                    }
                }
            }
        
    }

    public String get (String key) {

    	String string = configurationProperties.getProperty(key);

        if ((string == null) || (string.equals(""))) {
            return "NOT FOUND";
        }

        return string;
    }


}