package com.goEuroTest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.goEuroTest.util.ConfigurationLoader;
import com.opencsv.CSVWriter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * The following class is used to generate the REST call (GET)
 * and to create a csv file from the corresponding response (Json objects)
 * 
 * @author Tommaso Campanella
 *
 */
public class GoEuro implements IGoEuro {
	
	ConfigurationLoader configurationLoader = null;
	
	/**
	 * First Constructor
	 */
	public GoEuro() {
		
		configurationLoader = new ConfigurationLoader();
	}
	
	
	/**
	 * Second Constructor
	 * 
	 * @param configurationFileName
	 */
	public GoEuro(String configurationFileName) {
		
		configurationLoader = new ConfigurationLoader(configurationFileName);
	}
	
	/**
	 * The following method will first make a Rest call to a specific cityName 
	 * and then it will return the corresponding as a ClientResponse instance 
	 * 
	 * @param cityName
	 * @return an instance of ClientResponse
	 */
	public ClientResponse restCall(String cityName) {
		
		Client client = Client.create();
		
		String URL = configurationLoader.get("URL")+cityName;
		WebResource webResource = null;
		
		try {
			
			webResource = client.resource(URL);

		} catch (IllegalArgumentException e) {
			
			System.err.println(configurationLoader.get("WRONG_URL"));
			return null;

		}
		
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		if (response.getStatus() != 200) {

			System.err.println("Failed : HTTP error code : " + response.getStatus());
			return null;
		}
		
		return response;
	}
	
	/**
	 * The following methods create a csv file, in which the jsonResponse provided as argument
	 * is written
	 * 
	 * @param jsonResponse
	 * @return a boolean to state if the csv file has been successfully created or not
	 */
	public boolean jsonToCsv(String jsonResponse) {
		
		JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse));
		JsonArray jsonArray = null;
		
		try {
			
			jsonArray = jsonReader.readArray();
			
		} catch (Exception e) {
			
			System.err.println(configurationLoader.get("JSON_RESPONSE_BAD_FORMATTING"));
			 return false;
			 
		} finally {
			
			jsonReader.close();
	    	
	    }
		
		if(jsonArray.size() == 0){
			
			System.err.println(configurationLoader.get("EMPTY_RESPONSE"));
			return false;
			
		} else {
		
			String outputFile = configurationLoader.get("FILE_NAME");
			
			try {
				
				// use FileWriter constructor that specifies open for appending
				CSVWriter csvOutput = new CSVWriter(new FileWriter(outputFile, false), ',');
				
				// Getting the header line
				String header[] = configurationLoader.get("HEADER").split(",");
				
				// Writing the header line
				csvOutput.writeNext(header);
				
				for(int i = 0;i < jsonArray.size();i++) {
					
					int _id = (jsonArray.getJsonObject(i)).getInt("_id");
					String name = (jsonArray.getJsonObject(i)).getString("name");
					String type = (jsonArray.getJsonObject(i)).getString("type");
					JsonObject geo_position = jsonArray.getJsonObject(i).getJsonObject("geo_position");
					double latitude = (geo_position.getJsonNumber("latitude")).doubleValue();
					double longitude = (geo_position.getJsonNumber("longitude")).doubleValue();
					
					String[] newLine = {Integer.toString(_id),name,type,Double.toString(latitude),Double.toString(longitude)};
					
					//writing the JsonObject
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

