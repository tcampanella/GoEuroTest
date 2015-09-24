package com.goEuroTest.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.goEuroTest.util.ConfigurationLoader;

public class ConfigurationLoaderTest {
	
	@Test
	public void test1() {
		
		/**
		 * Create a new ConfigurationLoader instance
		 */
		ConfigurationLoader configurationLoader = new ConfigurationLoader();

		/**
		 * Check that the property file has been correctly created and read
		 */
		
		assertTrue(configurationLoader.getConfigurationProperties()!= null);
		assertTrue(configurationLoader.get("FILE_NAME").equals("output.csv"));
		
		/**
		 * Check that the correct message is provided in case a key does not exist
		 */
		
		assertTrue(configurationLoader.get("KEY_NOT_EXISTING").equals("NOT FOUND"));
		
		/**
		 * Check that getConfigurationProperties() returns the correct Property instance
		 */
		assertTrue(configurationLoader.getConfigurationProperties().get("FILE_NAME").equals("output.csv"));
		
		/**
		 * Create a new ConfigurationLoader instance with a wrong property file name
		 */
		configurationLoader = new ConfigurationLoader("file path not correct");
			
		/**
		 * Check that the property file has not been instantiated
		 */
		assertTrue(configurationLoader.getConfigurationProperties() == null);
		
	}
	
}
