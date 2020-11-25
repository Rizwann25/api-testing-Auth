package com.atmecs.auth.testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.auth.constants.Constants;
import com.atmecs.auth.utilities.PropertyReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutUpdateUser {

	@SuppressWarnings("unchecked")
	@Test
	public void updateUser() throws MalformedURLException
	{
		String name = "Rizwan";
		String job = "Associate Software Engineer";
		
		JSONObject user = new JSONObject();
		user.put("name", name);
		user.put("job", job);
		
		Properties NEW_DATA_PATH;
		NEW_DATA_PATH = PropertyReader.readProperties(Constants.USER_DATA_PATH);
		String baseUrl = NEW_DATA_PATH.getProperty("baseURL");
		String requestUrl = NEW_DATA_PATH.getProperty("updateUser");
		Map<String,Object> headers=new HashMap<String, Object>();
		headers.put("ContentType", "application/json");
		
		RequestSpecification request=RestAssured.given().headers(headers);
		Response response = request.when().body(user).put(new URL(baseUrl + requestUrl)).
				then().extract().response();
		System.out.println(response.getBody().asString());
		
		//status code validation.
	    int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode , 200);
		
		//response data validation
		Assert.assertEquals(response.getBody().asString().contains("updatedAt") , true);
		
	}
	
}
