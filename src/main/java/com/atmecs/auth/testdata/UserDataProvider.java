package com.atmecs.auth.testdata;

import org.testng.annotations.DataProvider;
import com.atmecs.auth.constants.Constants;

public class UserDataProvider {

	@DataProvider(name="createuserData")
	public static Object[][] getCreateUserData()
	{
		JSONReader jsonReader=new JSONReader(Constants.JSON_DATA_PATH);
		Object object=jsonReader.parse();
		Object[][] data = new Object[1][1];
		data[0][0]=object;
		return data;
	}
	@DataProvider(name="userloginData")
	public static Object[][] getLoginData()
	{
		JSONReader jsonReader=new JSONReader(Constants.LOGIN_DATA_PATH);
		Object object=jsonReader.parse();
		Object[][] data = new Object[1][1];
		data[0][0]=object;
		return data;
	}
}
