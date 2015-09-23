package com.goEuroTest.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * The following class is used to load and manage a specific property file. 
 * Such file can be a standard one (configuration.properties), otherwise it can be
 * passed as argument to the constructor of the class
 * 
 * @author Tommaso Campanella
 *
 */

public class ConfigurationLoader {

    private Properties configurationProperties = null;
    private final String STANDARD_CONFIGURATION_FILE_NAME = "configuration.properties";
    
    /**
     * First constructor
     */
    public ConfigurationLoader () {

    	loadConfigurationFile(STANDARD_CONFIGURATION_FILE_NAME);

    }
    
    /**
     * Second constructor
     * 
     * @param configurationFileName
     */
    public ConfigurationLoader (String configurationFileName) {
    	
    	loadConfigurationFile(configurationFileName);
    }
    
    /**
     * The following method returns the property HashTable previously loaded
     * 
     * @return configurationProperties
     */
    public Properties getConfigurationProperties() {
    	
    	if(configurationProperties == null)
    		return null;
		else 
			return configurationProperties;
    }


    /**
     * The following method load a property file.
     * The default path is configuration/
     *  
     * @param configurationFileName
     */
    private void loadConfigurationFile(String configurationFileName) {

	    InputStream input = null;
	
	    try {
	    	
	        input = this.getClass().getClassLoader().getResourceAsStream("configuration/"+ configurationFileName);

	        
	        // load properties file
	        if(input != null) {
	        	
	        	configurationProperties = new Properties();
	        	configurationProperties.load(new InputStreamReader(input, "UTF-8"));
	        }
	        	
	
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
    
    /**
     * 
     * The following method returns the value associated to a specific key
     * If no keys are found, "NOT FOUND" is returned
     * 
     * @param key
     * @return value associated to the key
     */
    public String get (String key) {

    	String value = configurationProperties.getProperty(key);

        if ((value == null) || (value.equals(""))) {
            return "NOT FOUND";
        }

        return value;
    }

}