package com.atmecs.auth.testscripts;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetProductApiTest {

	@Test
	public void testApi() throws MalformedURLException {
		String requestUrl = "http://localhost:8000/products/1";
		
		RequestSpecification request = RestAssured.given().header("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlY2hpZUBlbWFpbC5jb20iLCJwYXNzd29yZCI6InRlY2hpZSIsImlhdCI6MTYwNTE2MjEzNywiZXhwIjoxNjA1MTY1NzM3fQ.rn8_TIMgMNAVB9De30tBG0bCweFbdJhB3cWTmIe255s");
		
		Response response = request.get(new URL(requestUrl));
		
		int statusCode = response.getStatusCode();
		String responseBody = response.getBody().asString();
		JsonPath jsonPath = response.jsonPath();
		
		int id = jsonPath.get("id");
		String name = jsonPath.get("name");
		int cost = jsonPath.get("cost");
		int quantity = jsonPath.getInt("quantity");
		
		Assert.assertEquals(statusCode, 200, "Verified Status Code");
		
		System.out.println("Status Code:" + statusCode);
		System.out.println("Response Body:" + responseBody);
		System.out.println("Id:" + id);
		System.out.println("Name :" + name);
		System.out.println("Cost:" + cost);
		System.out.println("Quantity:" + quantity);
	}
}
