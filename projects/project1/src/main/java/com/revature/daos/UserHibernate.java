package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserHibernate implements UserDAO{

	@Override
	public List<User> getUsers(){
List<User> users = new ArrayList<User>();
		
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			users = s.createQuery("from User", User.class).list();
		}
		
		return users;
	}

	@Override
	public int insertUser(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUserByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
