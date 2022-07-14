package com.revature.services;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import com.revature.daos.UserHibernate;
import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserNotCreatedException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public class UserService {
	private static Logger log = LogManager.getLogger(UserService.class);
	UserHibernate uh = new UserHibernate();
	
	public List<UserDTO> getUsers(){
		return uh.getUsers();
	}
	
	public User getUserByName(String name) {
		User u = null;
		try {
			u = uh.getUserByName(name);
		} catch (UserNotFoundException e) {
			log.error("Exception thrown:" + e.fillInStackTrace());
		}
		return u;
	}
	
	public int insertUser(User u) {
		try {
			uh.insertUser(u);
		} catch (UserNotCreatedException e) {
			log.error("Exception thrown:" + e.fillInStackTrace());
		}
		return u.getId();
	}
	public User getUserByID(int id) {
		User u = null;
		try {
			u =  uh.getUserByID(id);
		} catch (UserNotFoundException e) {
			log.error("Exception thrown:" + e.fillInStackTrace());
		}
		return u;
		
	}
	
	public void updateUN(String username, String oldName) {
		try {
			uh.updateUN(username, oldName);
		} catch (UserNotFoundException e) {
			log.error("Exception thrown:" + e.fillInStackTrace());
		}
	}
	
	public void updateP(String pass, int id) {
		try {
			uh.updateP(pass, id);
		} catch (UserNotFoundException e) {
			log.error("Exception thrown:" + e.fillInStackTrace());
		}
	}
	
	public void updateN(String n, int id) {
		try {
			uh.updateN(n, id);
		} catch (UserNotFoundException e) {
			log.error("Exception thrown:" + e.fillInStackTrace());
		}
	}
	
}
