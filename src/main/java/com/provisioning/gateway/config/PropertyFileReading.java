package com.provisioning.gateway.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReading {
	public static String getProperty(String key) {
		Properties prop = new Properties();
		String property = null;
		try {
			// load a properties file for reading
			prop.load(new FileInputStream("myConfig.properties"));
			// get the properties and print
//			prop.list(System.out);
			// Reading each property value
			property = prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return property;
	}

}
