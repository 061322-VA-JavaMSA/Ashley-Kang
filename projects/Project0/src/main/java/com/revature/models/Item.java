package com.revature.models;

import java.util.Objects;

public class Item {

	
	private int itemID;
	private String itemName;
	private String itemDescription;
	private float itemCost;
	
	//int?
	private int ownerID;
	private boolean isOwned;
	
	public Item(){
		
	}
	
	public float getItemCost() {
		return itemCost;
	}

	public void setItemCost(float itemCost) {
		this.itemCost = itemCost;
	}
	
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public boolean isOwned() {
		return isOwned;
	}

	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}
	@Override
	public String toString() {
		return "Item [itemID=" + itemID + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", itemCost=" + itemCost + ", ownerID=" + ownerID + ", isOwned=" + isOwned + "]";
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(isOwned, itemCost, itemDescription, itemID, itemName, ownerID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return isOwned == other.isOwned && Float.floatToIntBits(itemCost) == Float.floatToIntBits(other.itemCost)
				&& Objects.equals(itemDescription, other.itemDescription) && itemID == other.itemID
				&& Objects.equals(itemName, other.itemName) && ownerID == other.ownerID;
	}
	
}
