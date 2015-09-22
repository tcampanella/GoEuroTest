package jersey;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.opencsv.CSVWriter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import util.JsonConfiguration;

public class JerseyClientGet {
	
	//Load configuration file
	JsonConfiguration jsonConfiguration = null;
	
	
	public JerseyClientGet() {
		
		jsonConfiguration = new JsonConfiguration();
	}
	
	public JerseyClientGet(String configurationFileName) {
		
		jsonConfiguration = new JsonConfiguration(configurationFileName);
	}
	
	
	public ClientResponse loadJsonResponse(String cityName) {
		
		Client client = Client.create();
		
		String URL = jsonConfiguration.get("URL")+cityName;
		WebResource webResource = null;
		try {
			
			webResource = client.resource(URL);

			
		} catch (IllegalArgumentException e) {
			
			System.out.println("The provided URL " + URL + " is not well formatted: please provide a correct one");
			return null;

		}
		
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		if (response.getStatus() != 200) {

			System.out.println("Failed : HTTP error code : " + response.getStatus());
			return null;
		}
		
		return response;
	}
	
	public boolean jsonToCsv(String jsonResponse) {
		
		JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse));
		JsonArray jsonArray = jsonReader.readArray();
		jsonReader.close();
		
		if(jsonArray.size() == 0){
			
			System.out.println(jsonConfiguration.get("EMPTY_RESPONSE"));
			return false;
			
		} else {
		
			String outputFile = jsonConfiguration.get("FILE_NAME");
			
			try {
				// use FileWriter constructor that specifies open for appending
				CSVWriter csvOutput = new CSVWriter(new FileWriter(outputFile, false), ',');
				
				// if the file didn't already exist then we need to write out the header line
				//String header[] = {" _id","name","type","latitude","longitude"};
				
				String header[] = jsonConfiguration.get("HEADER").split(",");
				
				csvOutput.writeNext(header);
				
				//System.out.println("--- Test output ---");

				for(int i = 0;i < jsonArray.size();i++) {
					
					int _id = (jsonArray.getJsonObject(i)).getInt("_id");
					String name = (jsonArray.getJsonObject(i)).getString("name");
					String type = (jsonArray.getJsonObject(i)).getString("type");
					JsonObject geo_position = jsonArray.getJsonObject(i).getJsonObject("geo_position");
					double latitude = (geo_position.getJsonNumber("latitude")).doubleValue();
					double longitude = (geo_position.getJsonNumber("longitude")).doubleValue();
					
					String[] newLine = {Integer.toString(_id),name,type,Double.toString(latitude),Double.toString(longitude)};
					
					//System.out.println(java.util.Arrays.toString(newLine));
					
					csvOutput.writeNext(newLine);
					
				}
			
				csvOutput.close();
				return true;
		
			} catch (IOException e) {
				
				e.printStackTrace();
				return false;
			}
		}
		
	}

}

