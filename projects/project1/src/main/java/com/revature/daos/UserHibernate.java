package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserNotCreatedException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserHibernate implements UserDAO{
	private static Logger log = LogManager.getLogger(UserHibernate.class);

	@Override
	public List<UserDTO> getUsers(){
		List<User> users = new ArrayList<User>();
		
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			users = s.createQuery("from User", User.class).list();
		}catch (Exception e) {
			log.error("Exception thrown:" + e.fillInStackTrace());
		}
		
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for(User u: users) {
			usersDTO.add(new UserDTO(u));
		}
		return usersDTO;
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
			log.error("ConstraintViolationException thrown:" + e.fillInStackTrace());
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
			log.error("UserNotFoundException thrown");
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
			
			cq.select(root).where(predicateForUsername);
			
			// retrieves the result
			user = (User) s.createQuery(cq).getSingleResult();
		}
		
		if(user == null) {
			log.error("UserNotFoundException thrown:");
			throw new UserNotFoundException();
		}
		else {
			return user;
		}
		
	}

	@Override
	public void updateUN(String username, String oldname) throws UserNotFoundException{
		User u = getUserByName(oldname);
		
		if(u == null) {
			throw new UserNotFoundException();
		}
		
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaUpdate<User> criteriaUpdate = cb.createCriteriaUpdate(User.class);
			Root<User> root = criteriaUpdate.from(User.class);
			criteriaUpdate.set("username", username);
			criteriaUpdate.where(cb.equal(root.get("username"), oldname));
			Transaction transaction = s.beginTransaction();
			s.createQuery(criteriaUpdate).executeUpdate();
			transaction.commit();
		}
	}

	@Override
	public void updateP(String pass, int id) throws UserNotFoundException{
		User u = getUserByID(id);
		if(u == null) {
			throw new UserNotFoundException();
		}
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaUpdate<User> criteriaUpdate = cb.createCriteriaUpdate(User.class);
			Root<User> root = criteriaUpdate.from(User.class);
			criteriaUpdate.set("user_pass", pass);
			criteriaUpdate.where(cb.equal(root.get("id"), id));
			Transaction transaction = s.beginTransaction();
			s.createQuery(criteriaUpdate).executeUpdate();
			transaction.commit();
		}
	}

	@Override
	public void updateN(String n, int id) throws UserNotFoundException{
		User u = getUserByID(id);
		if(u == null) {
			log.error("Exception thrown:");
			throw new UserNotFoundException();
		}
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaUpdate<User> criteriaUpdate = cb.createCriteriaUpdate(User.class);
			Root<User> root = criteriaUpdate.from(User.class);
			criteriaUpdate.set("user_name", n);
			criteriaUpdate.where(cb.equal(root.get("id"), id));
			Transaction transaction = s.beginTransaction();
			s.createQuery(criteriaUpdate).executeUpdate();
			transaction.commit();
		}
	}

}
