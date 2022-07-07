package com.revature.daos;

import java.util.List;

import com.revature.exceptions.TicketNotFoundException;
import com.revature.exceptions.TicketNotCreatedException;
import com.revature.models.Ticket;

public interface TicketDAO {
	
	public int insertTicket(Ticket t) throws TicketNotCreatedException;
	public Ticket getTicketByID(int id) throws TicketNotFoundException;
	//public Ticket getTicketByStatus();
	public Ticket getTicketByEmpID(int empID) throws TicketNotFoundException;
	public Ticket getTicketByManID(int manID) throws TicketNotFoundException;
	public List<Ticket> getAllTickets();
	public List<Ticket> getPendTickets();
	public List<Ticket> getDenTickets();
	public List<Ticket> getApprTickets();
	public List<Ticket> getLodTickets();
	public List<Ticket> getFoodTickets();
	public List<Ticket> getTravTickets();
	public List<Ticket> getOthTickets();
}
