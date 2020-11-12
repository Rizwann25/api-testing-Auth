package com.atmecs.auth.testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.atmecs.auth.constants.Constants;
import com.atmecs.auth.testdata.UserDataProvider;
import com.atmecs.auth.utilities.TokenReader;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


	public class PostProductApiTest {

	@Test(dataProvider = "createUserData", dataProviderClass = UserDataProvider.class)
	public void postData(Object requestBody) throws MalformedURLException {
	System.out.println("Request Body : " + requestBody);
	Reporter.log("Request Body has been Specified");
	String requestUrl = "http://localhost:8000/products";
	Reporter.log("Request url has been sent");

	TokenReader token = new TokenReader();
	String bearerToken = token.readToken(Constants.RESPONSE_BODY_PATH, "access_token");
	Reporter.log("Got Bearer Token");

	Map<String, Object> headers = new HashMap<String, Object>();
	headers.put("Content-Type", "application/json");
	headers.put("Authorization", "Bearer " + bearerToken);

	Reporter.log("Authorization has been Done");
	RequestSpecification request = RestAssured.given().headers(headers);
	Response response = request.when().body(requestBody.toString()).post(new URL(requestUrl)).then().extract()
	.response();
	Reporter.log("Post Request Method has been Done");
	int statusCode = response.getStatusCode();
	Reporter.log("Got Status Code :" + statusCode);
	System.out.println("Status Code :" + statusCode);
	String responseBody = response.getBody().asString();
	Reporter.log("Got Response Body :" + responseBody);
	System.out.println("Response Body : " + responseBody);
	Assert.assertEquals(statusCode, 201);
	Reporter.log("Assertion passed");
	JsonPath jsonpath = response.jsonPath();
	int id = jsonpath.getInt("id");
   }
}
