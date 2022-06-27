package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
//import com.revature.models.User;

public interface StoreDAO {
	//Create items
	Item createItem(Item i);
	
	//Retrieve item
	Item retrieveById(int id);
	Item retrieveByName(String name);
	
	//Retrieve list of all items
	List<Item> retrieveInventory();
	
	//View items offer and reject or accept
	boolean itemOffer(int itemID);
	
	
	//view all payments left
	void viewPayments();
	
	//delete items
	boolean deleteByID(int id);
	boolean deleteAll();
	
	//update item information
	boolean updateItem(int id);
}
