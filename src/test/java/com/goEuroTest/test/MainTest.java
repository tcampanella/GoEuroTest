package com.goEuroTest.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.goEuroTest.*;
import com.sun.jersey.api.client.ClientResponse;

/**
 * The following code is used to test the expected behaviour
 * as by specification in https://github.com/goeuro/dev-test/
 * 
 * @author Tommaso Campanella
 *
 */
public class MainTest {
	
	private IGoEuro goEuro = null;
	private final String WRONG_CONFIGURATION_FILE = "configuration_wrong.properties";
	
	@Test
	public void test1() {
		
		/**
		 * Create a new goEuro instance
		 */
		
		goEuro = new GoEuro();
		
		/**
		 * Make the rest call for the city Berlin
		 */
		
		ClientResponse clientResponse = goEuro.restCall("Berlin");
		
		
		/**
		 * Check that the csv file has been correctly created and written
		 * (further test will be executed in the XXXX.java) 
		 */
		
		assertTrue(goEuro.jsonToCsv(clientResponse.getEntity(String.class)));
		
	}
	
	@Test
	public void test2() {
		
		/**
		 * Create a new goEuro instance
		 */
		
		goEuro = new GoEuro();
		
		/**
		 * Make the rest call for the city Nocity
		 */
		
		ClientResponse clientResponse = goEuro.restCall("Nocity");
		
		/**
		 * Check that the response is empty, as "Nocity" is not a valid city
		 */

		assertFalse(goEuro.jsonToCsv(clientResponse.getEntity(String.class)));
		
		
	}
	
	@Test
	public void test3() {
		
		/**
		 * Create a new goEuro instance
		 */
		
		goEuro = new GoEuro();
		
		/**
		 * Make the rest call for the city Nocity
		 */
		
		ClientResponse clientResponse = goEuro.restCall("City with bad formatted URL");
		
		/**
		 * Check that the response is empty, as "City with bad formatted URL" 
		 * generate an invalid (not well formatted) URL
		 */

		assertTrue(clientResponse == null);
		
		
	}
	
	@Test
	public void test4() {
		
		/**
		 * Create a new goEuro instance
		 */
		
		goEuro = new GoEuro(WRONG_CONFIGURATION_FILE);
		
		/**
		 * Make the rest call for the city Berlin
		 */
		
		ClientResponse clientResponse = goEuro.restCall("Berlin");
		
		/**
		 * Check that the response is empty, as the URL provided
		 * with the wrong configuration file is not pointing to any RESTFull Web Service
		 */

		assertTrue(clientResponse == null);
		
	}

}
