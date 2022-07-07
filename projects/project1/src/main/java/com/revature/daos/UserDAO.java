package com.revature.daos;

import java.util.List;

import com.revature.exceptions.UserNotCreatedException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public interface UserDAO {
	
	public int insertUser(User u) throws UserNotCreatedException;
	public User getUserByID(int id) throws UserNotFoundException;
	public User getUserByName(String username) throws UserNotFoundException;
	public List<User> getUsers();
}
