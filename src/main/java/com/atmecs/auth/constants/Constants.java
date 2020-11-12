package com.atmecs.auth.constants;

import java.io.File;

      public class Constants {
			public static final String BASE_DIR = System.getProperty("user.dir");
			public static final String JSON_DATA_PATH = BASE_DIR + File.separator + "userData.json";
			public static final String LOGIN_DATA_PATH = JSON_DATA_PATH + File.separator + "loginData.json";
			public static final String RESPONSE_BODY_PATH = LOGIN_DATA_PATH  + JSON_DATA_PATH;
								
	}

