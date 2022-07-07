package com.revature.services;

import java.util.List;

import com.revature.daos.UserHibernate;
import com.revature.models.User;

public class UserService {
	UserHibernate uh = new UserHibernate();
	
	public List<User> getUsers(){
		return uh.getUsers();
	}
	
	public User getUserByName(String name) {
		return uh.getUserByName(name);
	}
}
