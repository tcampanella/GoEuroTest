package com.goEuroTest;

import com.sun.jersey.api.client.ClientResponse;

public interface IGoEuro {
	
	public ClientResponse restCall(String cityName);
	public boolean jsonToCsv(String jsonResponse);

}
