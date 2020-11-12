package com.atmecs.auth.testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.auth.testdata.UserDataProvider;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutUserApiTesting {

	@Test(dataProvider="createUserData", dataProviderClass=UserDataProvider.class)
	
	public void putData(Object requestBody) throws MalformedURLException
	{
		System.out.println("Request Body:"+requestBody);
		String requestUrl="http://localhost:8000/products/4";
		
		Map<String,Object> headers = new HashMap<String,Object>();
		headers.put("Content.Type", "application/json");
		headers.put("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlY2hpZUBlbWFpbC5jb20iLCJwYXNzd29yZCI6InRlY2hpZSIsImlhdCI6MTYwNTE2MjEzNywiZXhwIjoxNjA1MTY1NzM3fQ.rn8_TIMgMNAVB9De30tBG0bCweFbdJhB3cWTmIe255s ");
		RequestSpecification request = RestAssured.given().headers(headers);
		Response response = request.when().body(requestBody.toString()).put(new URL(requestUrl)).then().extract().response();
		int statusCode = response.getStatusCode();
		String responseBody=response.getBody().asString();
		JsonPath jsonpath = response.jsonPath();
		
		int id = jsonpath.getInt("id");
		String name = jsonpath.getString("name");
		int cost=jsonpath.getInt("cost");
		int quantity=jsonpath.getInt("quantity");
		
		System.out.println("Status Code:" + statusCode);
		System.out.println("Response Body:" + statusCode);
		System.out.println("Email:" + statusCode);
		System.out.println("Password:" + statusCode);
		System.out.println("Access Token:" + statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
	
}
