package com.atmecs.auth.testscripts;

import java.net.MalformedURLException;
import java.net.URL;



import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteProductApiTest {

	@Test
	public void deleteTestApi() throws MalformedURLException
	{
		String requestUrl = "http://localhost:8000/products/15";
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlY2hpZUBlbWFpbC5jb20iLCJwYXNzd29yZCI6InRlY2hpZSIsImlhdCI6MTYwNTE2MjEzNywiZXhwIjoxNjA1MTY1NzM3fQ.rn8_TIMgMNAVB9De30tBG0bCweFbdJhB3cWTmIe255s");
		
		Response response = request.delete(new URL(requestUrl)).then().extract().response();
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
		
		System.out.println("Status Code:"+ statusCode);
	}
}
