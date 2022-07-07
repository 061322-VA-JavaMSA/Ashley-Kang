package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.revature.exceptions.UserNotCreatedException;
import com.revature.exceptions.UserNotFoundException;
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
	public int insertUser(User u) throws UserNotCreatedException{
		u.setId(-1);
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			int id = (int) s.save(u);
			u.setId(id);
			tx.commit();	
		} catch(ConstraintViolationException e) {
			//log it
		}
		if(u.getId() == -1) {
			throw new UserNotCreatedException();
		}else {
			return u.getId();
		}
	}

	@Override
	public User getUserByID(int id) throws UserNotFoundException{
		User u = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			u = s.get(User.class, id);
		}
		if(u == null) {
			throw new UserNotFoundException();
		}else {
			return u;
		}
	}

	@Override
	public User getUserByName(String username) throws UserNotFoundException{
		User user = null;
		
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);
			
			Predicate predicateForUsername = cb.equal(root.get("username"), username);
//			Predicate predicateForSomethingElse = cb.equal(root.get("password"), password);
//			Predicate predicateFromUnameAndPass = cb.and(predicateForUsername, predicateForSomethingElse);
			
			cq.select(root).where(predicateForUsername);
			
			// retrieves the result
			user = (User) s.createQuery(cq).getSingleResult();
		}
		
		if(user == null) {
			throw new UserNotFoundException();
		}
		else {
			return user;
		}
		
	}
}
