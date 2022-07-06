package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserService {
	public List<User> getUsers(){
		List<User> users = new ArrayList<User>();
		
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			users = s.createQuery("from User", User.class).list();
		}
		
		return users;
	}
}
