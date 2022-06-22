package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
import com.revature.models.User;

public interface UserDAO {
	User createUser(User u);
	User retrieveUserbyId(int id);
	User retrieveByUsername(String username);
	List<User> retrieveUsers();
	boolean deleteByID(int id);
	boolean updateUser();
	boolean deleteAll();
	void makeOffer(int amount);
	List<Item> ownedItems();
	void paymentsLeft();
	//make offer for item
	//see owned items
	//remaining payments
}
