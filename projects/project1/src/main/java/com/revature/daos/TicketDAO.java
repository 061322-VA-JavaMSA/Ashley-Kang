package com.revature.daos;

import java.util.List;

import com.revature.exceptions.TicketNotFoundException;
import com.revature.exceptions.TicketNotCreatedException;
import com.revature.models.Ticket;

public interface TicketDAO {
	
	public void insertTicket(Ticket t);
	public Ticket getTicketByID(int id) throws TicketNotFoundException;
	
	public void updateTicket(String status, int ticketID) throws TicketNotFoundException;
	public void updateMTicket(int mID, int ticketID) throws TicketNotFoundException;

	public List<Ticket> getTicketByEmpID(int empID);
	public List<Ticket> getTicketByManID(int manID);
	public List<Ticket> getAllTickets();
	public List<Ticket> getPendTickets();
	public List<Ticket> getDenTickets();
	public List<Ticket> getApprTickets();
	public List<Ticket> getLodTickets();
	public List<Ticket> getFoodTickets();
	public List<Ticket> getTravTickets();
	public List<Ticket> getOthTickets();
}
