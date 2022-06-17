package com.revature.models;

/*
 * authentication id?
 */

public class Employee extends User{
	private String authKey;
	
	public void setKey(String key) {
		authKey = key;
	}
	
	public String getKey() {
		return authKey;
	}
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail=" + userEmail + ", userID="
				+ userID + ", authKey=" + authKey + "]";
	}
}
