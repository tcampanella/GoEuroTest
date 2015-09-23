package com.goEuroTest.test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.goEuroTest.*;
import com.opencsv.CSVReader;
import com.sun.jersey.api.client.ClientResponse;

public class GoEuroTest {
	
	private IGoEuro goEuro = null;
	
	@SuppressWarnings("rawtypes")
	@Test
	public void test1() {
		
		/**
		 * Create a new GoEuro instance
		 */
		
		goEuro = new GoEuro();
		
		/**
		 * Call the method restCall() for the city Berlin
		 */
		
		ClientResponse clientResponse = goEuro.restCall("Berlin");
		
		/**
		 * 
		 * Check that the REST call has been successful
		 * 
		 */
		
		String response = clientResponse.getEntity(String.class);
		
		assertTrue(response.contains("376217"));
		assertTrue(goEuro.jsonToCsv(response));
		
		/**
		 * Check that a call to the method jsonToCsv() with an empty response
		 * returns false
		 */
		
		assertFalse(goEuro.jsonToCsv("[]"));
		
		/**
		 * Check that a call to the method jsonToCsv() with an bad formatted response
		 * returns false
		 */
		
		assertFalse(goEuro.jsonToCsv("bad formatter"));
		
		/**
		 * Check that the csv file has been created
		 */
		
		IOException ioException = null;
		CSVReader reader = null;
		
		try {
			reader = new CSVReader(new FileReader("output.csv"));
		} catch (IOException e) {
			ioException = e;
		}
		
		assertTrue(ioException == null);
		assertTrue(reader != null);
		
		/**
		 * Check the csv header file has been correctly created
		 */
		
		String header_1 = "_id";
		
		List entries = null;
		
		try {
			entries = reader.readAll();
			assertTrue(((String[])entries.get(0))[0].equals(header_1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * Check one entry in the csv file
		 */
		
		assertTrue(((String[])entries.get(1))[0].equals("376217"));
		
	}
	
}
