package com.atmecs.auth.testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.auth.testdata.UserDataProvider;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginApi {

	@Test(dataProvider="UserLoginData", dataProviderClass=UserDataProvider.class)
	public void testApi(Object requestBody) throws MalformedURLException
	{
		System.out.println("Request Body:"+requestBody);
		String requestUrl="http://localhost:8000/auth/login";
		
		Map<String,Object> headers=new HashMap<String, Object>();
		headers.put("Content.Type", "application/json");
		RequestSpecification request=RestAssured.given().headers(headers);
		
		Response response = request.when().body(requestBody.toString()).post(new URL(requestUrl)).then().extract().response();
		int statusCode = response.getStatusCode();
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		JsonPath jsonPath=response.jsonPath();
		
		JSONObject jsonObject=(JSONObject) requestBody;
		String token = jsonPath.get("access_Token");
		
		String actualEmail=jsonObject.get("email").toString();
		String actualPassword=jsonObject.get("Password").toString();
		
		System.out.println("Status Code:" + statusCode);
		System.out.println("Response Body:" + statusCode);
		System.out.println("Email:" + statusCode);
		System.out.println("Password:" + statusCode);
		System.out.println("Access Token:" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
}
