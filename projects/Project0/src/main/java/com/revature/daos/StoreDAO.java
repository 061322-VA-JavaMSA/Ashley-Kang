package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
//import com.revature.models.User;

public interface StoreDAO {
	Item createItem(Item i);
	Item retrieveById(int id);
	//Item retrieveByName(String name);
	List<Item> retrieveInventory();
	boolean itemOffer();
	//accept or reject offer for item
	//update item to owned state (implicitely?)
	//reject all offers once something is accepted (implicit?)
	//view payments
	void viewPayments();
	//calculate weekly payments
	boolean deleteByID(int id);
	boolean updateItem();
	boolean deleteAll();
}
