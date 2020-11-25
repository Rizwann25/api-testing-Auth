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


	public class PostCreateUser {

	@SuppressWarnings("unchecked")
	@Test
	public void postCreateUser() throws MalformedURLException {
		
		String name = "Jacob";
		String job = "Software Engineer";
		
		JSONObject user = new JSONObject();
		user.put("name", name);
		user.put("job", job);
		
		Properties NEW_DATA_PATH;
		NEW_DATA_PATH = PropertyReader.readProperties(Constants.USER_DATA_PATH);
		String baseUrl = NEW_DATA_PATH.getProperty("baseURL");
		String requestUrl = NEW_DATA_PATH.getProperty("createUser");
		Map<String,Object> headers=new HashMap<String, Object>();
		headers.put("ContentType", "application/json");
		
		RequestSpecification request=RestAssured.given().headers(headers);
		Response response = request.when().body(user).post(new URL(baseUrl + requestUrl)).
				then().extract().response();
		System.out.println(response.getBody().asString());
		
		//status code validation.
	    int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode , 201);
		
		//response data validation
		Assert.assertEquals(response.getBody().asString().contains("id") , true);
		Assert.assertEquals(response.getBody().asString().contains("createdAt") , true);
		
   }
}
