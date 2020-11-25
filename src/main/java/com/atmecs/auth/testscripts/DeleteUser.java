package com.atmecs.auth.testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.auth.constants.Constants;
import com.atmecs.auth.utilities.PropertyReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteUser {

	@Test
	public void deleteUser() throws MalformedURLException
	{
		Properties NEW_DATA_PATH;
		NEW_DATA_PATH = PropertyReader.readProperties(Constants.USER_DATA_PATH);
		String baseUrl = NEW_DATA_PATH.getProperty("baseURL");
		String requestUrl = NEW_DATA_PATH.getProperty("deleteUser");
		
		//headers are necessary when need to pass some values in header ex: bearer token etc.
		Map<String,Object> headers=new HashMap<String, Object>();
		headers.put("ContentType", "application/json");
		
		RequestSpecification request=RestAssured.given().headers(headers);
		Response response = request.when().delete(new URL(baseUrl + requestUrl)).
				then().extract().response();
		System.out.println(response.getBody().asString());
		
		//status code validation.
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode , 204);
		
		//response body validation
		Assert.assertEquals(true, response.getBody().asString().isEmpty());
	}
}
