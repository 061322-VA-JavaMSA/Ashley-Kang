package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	public int insertUser(User u);
	public User getUserByID(int id);
	public User getUserByName(String username);
	public List<User> getUsers();
}
