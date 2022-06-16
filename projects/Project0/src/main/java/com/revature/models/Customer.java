package com.revature.models;

public class Customer extends User{
	
	/*
	 * User Only:
	 * store payment card
	 * 
	 */
	private String cardNumber;
	
	public void setCard(String card) {
		cardNumber = card;
	}
	
	public String getCard() {
		return cardNumber;
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail=" + userEmail + ", userID="
				+ userID + ", cardNumber=" + cardNumber + "]";
	}
}
