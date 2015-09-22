package jersey;

import static org.junit.Assert.*;

import org.junit.Test;
import com.sun.jersey.api.client.ClientResponse;

public class JerseryClientTest {
	
	private JerseyClientGet jerseyClientGet = null;
	private final String WRONG_CONFIGURATION_FILE = "configuration_wrong.properties";
	
	@Test
	public void test1() {
		
		jerseyClientGet = new JerseyClientGet();
		
		ClientResponse clientResponse = jerseyClientGet.loadJsonResponse("London");

		assertTrue(jerseyClientGet.jsonToCsv(clientResponse.getEntity(String.class)));
		
	}
	
	@Test
	public void test2() {
		
		jerseyClientGet = new JerseyClientGet();
		
		ClientResponse clientResponse = jerseyClientGet.loadJsonResponse("Nocity");

		assertFalse(jerseyClientGet.jsonToCsv(clientResponse.getEntity(String.class)));
		
		
	}
	
	@Test
	public void test3() {
		
		jerseyClientGet = new JerseyClientGet();
		
		ClientResponse clientResponse = jerseyClientGet.loadJsonResponse("City with bad formatted URL");

		assertTrue(clientResponse == null);
		
		
	}
	
	@Test
	public void test4() {
		
		jerseyClientGet = new JerseyClientGet(WRONG_CONFIGURATION_FILE);
		
		ClientResponse clientResponse = jerseyClientGet.loadJsonResponse("Berlin");

		assertTrue(clientResponse == null);
		
	}

}
