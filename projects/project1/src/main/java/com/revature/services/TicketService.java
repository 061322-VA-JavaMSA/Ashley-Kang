package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.daos.TicketHibernate;
import com.revature.exceptions.TicketNotCreatedException;
import com.revature.exceptions.TicketNotFoundException;
import com.revature.models.Ticket;

public class TicketService {
	TicketHibernate th = new TicketHibernate();
	
	public int insertTicket(Ticket t){
		try {
			th.insertTicket(t);
		} catch (TicketNotCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t.getId();
	}
	public Ticket getTicketByID(int id) {
		Ticket t = null;
		try {
			t = th.getTicketByID(id);
		} catch (TicketNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	public List<Ticket> getTicketByEmpID(int empID) {
		return th.getTicketByEmpID(empID);
	}
	public List<Ticket> getTicketByManID(int manID) {
		return th.getTicketByManID(manID);
	}
	public List<Ticket> getAllTickets(){
		return th.getAllTickets();
	}
	public List<Ticket> getPendTickets(){
		return th.getPendTickets();
	}
	public List<Ticket> getDenTickets(){
		return th.getDenTickets();
	}
	public List<Ticket> getApprTickets(){
		return th.getApprTickets();
	}
	public List<Ticket> getLodTickets(){
		return th.getLodTickets();
	}
	public List<Ticket> getFoodTickets(){
		return th.getFoodTickets();
	}
	public List<Ticket> getTravTickets(){
		return th.getTravTickets();
	}
	public List<Ticket> getOthTickets(){
		return th.getOthTickets();
	}
}