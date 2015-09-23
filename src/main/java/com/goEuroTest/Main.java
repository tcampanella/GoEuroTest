package com.goEuroTest;

import com.sun.jersey.api.client.ClientResponse;

/**
 * 
 * @author Tommaso Campanella
 *
 */

public class Main {

	public static void main(String[] args) {
		
				
			/**
			 * Get Rest call (GET) response
			 */
			
			IGoEuro goEuro = new GoEuro();
			ClientResponse clientResponse = goEuro.restCall(args[0]);
			
			/**
			 * Parse Json objects to csv file
			 */
			
			goEuro.jsonToCsv(clientResponse.getEntity(String.class));
			
	}

}
