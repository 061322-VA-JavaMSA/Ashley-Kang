package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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
		User u = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			u = s.get(User.class, id);
		}
		return u;
	}

	@Override
	public User getUserByName(String username) {
User user = null;
		
		try(Session s = HibernateUtil.getSessionFactory().openSession();){
			// SELECT * FROM USERS WHERE USERNAME = '';
			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			// define entity to be searched
			Root<User> root = cq.from(User.class);
			
			//define conditions
			Predicate predicateForUsername = cb.equal(root.get("username"), username);
//			Predicate predicateForSomethingElse = cb.equal(root.get("password"), password);
//			Predicate predicateFromUnameAndPass = cb.and(predicateForUsername, predicateForSomethingElse);
			
			cq.select(root).where(predicateForUsername);
			
			// retrieves the result
			user = (User) s.createQuery(cq).getSingleResult();
		}
		
		return user;
	}
}
