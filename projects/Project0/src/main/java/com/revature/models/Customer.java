package com.revature.models;

import java.util.Objects;

public class Customer extends User{
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cardNumber);
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
		Customer other = (Customer) obj;
		return Objects.equals(cardNumber, other.cardNumber);
	}
	
	
}
