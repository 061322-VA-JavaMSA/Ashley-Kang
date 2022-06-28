package com.revature.models;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Manager extends User{
	private String authKey;
	private static Logger log = LogManager.getLogger(Manager.class);
	
	public Manager() {
		super();
		try {
			readPropertiesFile("mCredentials.properties");
		} catch (IOException e) {
			log.error("Exception thrown: " + e.fillInStackTrace());
		}
	}
	
	private void readPropertiesFile(String fileName) throws IOException{
		
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		prop.load(loader.getResourceAsStream("mCredentials.properties"));
		
		String key = prop.getProperty("key");
		setKey(key);
	   }
	
	public String getKey() {
		return authKey;
	}
	
	public boolean checkKey(String key2Check) {
		if(key2Check.equals(authKey)) {
			return true;
		}
		return false;
	}
	
	private void setKey(String key) {
		authKey = key;
	}
	
	
	
	@Override
	public String toString() {
		return "Manager [name=" + name + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail=" + userEmail + ", userID="
				+ userID + "]";
	}
}
