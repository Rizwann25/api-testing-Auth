package com.atmecs.auth.testdata;

import org.testng.annotations.DataProvider;

import com.atmecs.auth.constants.Constants;
import com.atmecs.auth.utilities.JsonReader;


public class UserDataProvider {

	@DataProvider(name="userData")
	public static Object[][] getCreateUserData()
	{
		JsonReader jsonReader=new JsonReader(Constants.JSON_DATA_PATH);
		Object object=jsonReader.parse();
		Object[][] data = new Object[1][1];
		data[0][0]=object;
		return data;
	}
	@DataProvider(name="loginData")
	public static Object[][] getLoginData()
	{
		JsonReader jsonReader=new JsonReader(Constants.LOGIN_DATA_PATH);
		Object object=jsonReader.parse();
		Object[][] data = new Object[1][1];
		data[0][0]=object;
		return data;
	}
}
