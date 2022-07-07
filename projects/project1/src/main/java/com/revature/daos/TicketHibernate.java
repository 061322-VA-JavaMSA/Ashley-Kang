package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.revature.exceptions.TicketNotCreatedException;
import com.revature.exceptions.TicketNotFoundException;
import com.revature.models.Ticket;
import com.revature.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TicketHibernate implements TicketDAO{

	@Override
	public int insertTicket(Ticket t) throws TicketNotCreatedException{
		t.setId(-1);
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			int id = (int) s.save(t);
			t.setId(id);
			tx.commit();	
		} catch(ConstraintViolationException e) {
			//log it
		}
		
		if(t.getId() == -1) {
			throw new TicketNotCreatedException();
		}else {
			return t.getId();
		}
	}
	

	@Override
	public Ticket getTicketByID(int id) throws TicketNotFoundException{
		Ticket t = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			t = s.get(Ticket.class, id);
		}
		if(t == null) {
			throw new TicketNotFoundException();
		}else {
			return t;
		}
	}

	@Override
	public Ticket getTicketByEmpID(int empID) throws TicketNotFoundException {
		Ticket t = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
			Root<Ticket> root = cq.from(Ticket.class);
			
			Predicate predicateForEmpID = cb.equal(root.get("employee_id"), empID);
			cq.select(root).where(predicateForEmpID);
			
		
			t = (Ticket) s.createQuery(cq).getSingleResult();
		}
		
		if(t == null) {
			throw new TicketNotFoundException();
		}else {
			return t;
		}
	}

	@Override
	public Ticket getTicketByManID(int manID) throws TicketNotFoundException {
		Ticket t = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
			Root<Ticket> root = cq.from(Ticket.class);
			
			Predicate predicateForEmpID = cb.equal(root.get("manager_id"), manID);
			cq.select(root).where(predicateForEmpID);		
			t = (Ticket) s.createQuery(cq).getSingleResult();
		}
		
		if(t == null) {
			throw new TicketNotFoundException();
		}else {
			return t;
		}
	}

	@Override
	public List<Ticket> getPendTickets() {
		List<Ticket> t = new ArrayList<Ticket>();
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
			Root<Ticket> root = cq.from(Ticket.class);
			
			Predicate predicateForStat = cb.equal(root.get("ticket_stat"), "PENDING");
			cq.select(root).where(predicateForStat);
			

			t = s.createQuery(cq).getResultList();
		}
		return t;
	}

	@Override
	public List<Ticket> getDenTickets() {
		List<Ticket> t = new ArrayList<Ticket>();
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
			Root<Ticket> root = cq.from(Ticket.class);
			
			Predicate predicateForStat = cb.equal(root.get("ticket_stat"), "DENIED");
			cq.select(root).where(predicateForStat);
			

			t = s.createQuery(cq).getResultList();
		}
		
		return t;
	}

	@Override
	public List<Ticket> getApprTickets() {
		List<Ticket> t = new ArrayList<Ticket>();
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
			Root<Ticket> root = cq.from(Ticket.class);
			
			Predicate predicateForStat = cb.equal(root.get("ticket_stat"), "APPROVED");
			cq.select(root).where(predicateForStat);
			

			t = s.createQuery(cq).getResultList();
		}
		
		return t;
	}

	@Override
	public List<Ticket> getLodTickets()  {
		List<Ticket> t = new ArrayList<Ticket>();
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
			Root<Ticket> root = cq.from(Ticket.class);
			
			Predicate predicateForType = cb.equal(root.get("ticket_stat"), "LODGING");
			cq.select(root).where(predicateForType);
			

			t = s.createQuery(cq).getResultList();
		}
		
		return t;
	}

	@Override
	public List<Ticket> getFoodTickets()  {
		List<Ticket> t = new ArrayList<Ticket>();
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
			Root<Ticket> root = cq.from(Ticket.class);
			
			Predicate predicateForType = cb.equal(root.get("ticket_stat"), "FOOD");
			cq.select(root).where(predicateForType);
			

			t = s.createQuery(cq).getResultList();
		}
		
		return t;
	}

	@Override
	public List<Ticket> getTravTickets() {
		List<Ticket> t = new ArrayList<Ticket>();
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
			Root<Ticket> root = cq.from(Ticket.class);
			
			Predicate predicateForType = cb.equal(root.get("ticket_stat"), "TRAVEL");
			cq.select(root).where(predicateForType);
			

			t = s.createQuery(cq).getResultList();
		}
		
		return t;
	}

	@Override
	public List<Ticket> getOthTickets() {
		List<Ticket> t = new ArrayList<Ticket>();
		try(Session s = HibernateUtil.getSessionFactory().openSession();){			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
			Root<Ticket> root = cq.from(Ticket.class);
			
			Predicate predicateForType = cb.equal(root.get("ticket_stat"), "OTHER");
			cq.select(root).where(predicateForType);
			

			t = s.createQuery(cq).getResultList();
		}
		
		return t;
	}


	@Override
	public List<Ticket> getAllTickets()  {
		List<Ticket> tickets = new ArrayList<Ticket>();
		
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			tickets = s.createQuery("from Ticket", Ticket.class).list();
		}
		
		return tickets;
	}

}
