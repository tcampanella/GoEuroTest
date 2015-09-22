package jersey;

import com.sun.jersey.api.client.ClientResponse;

public class Main {

	
	public static void main(String[] args) {
		
				
			/**
			 * Get Json response
			 */
			
			JerseyClientGet jerseyClientGet = new JerseyClientGet();
			
			ClientResponse clientResponse = jerseyClientGet.loadJsonResponse(args[0]);
			
			/**
			 * Parse Json object to csv file
			 */
			
			jerseyClientGet.jsonToCsv(clientResponse.getEntity(String.class));
			
	}

}
