package com.revature.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee extends User{
	private String authKey;
	private static Logger log = LogManager.getLogger(Manager.class);
	
	public Employee() {
		super();
		try {
			readPropertiesFile("eCredentials.properties");
		} catch (IOException e) {
			log.error("Exception thrown: " + e.fillInStackTrace());
		}
	}
	
	private void readPropertiesFile(String fileName) throws IOException{
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		prop.load(loader.getResourceAsStream("eCredentials.properties"));
		
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
		return "Employee [name=" + name + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail=" + userEmail + ", userID="
				+ userID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(authKey);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(authKey, other.authKey);
	}
	
	
}
