package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
import com.revature.models.User;

public class UserPostgres implements UserDAO{
	
	@Override
	public User createUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User retrieveUserbyId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User retrieveByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> retrieveUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteByID(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeOffer(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> ownedItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void paymentsLeft() {
		// TODO Auto-generated method stub
		
	}

}