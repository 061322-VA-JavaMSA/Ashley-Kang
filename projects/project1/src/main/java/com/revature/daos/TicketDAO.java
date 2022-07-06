package com.revature.daos;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDAO {
	
	public int insertTicket(Ticket t);
	public Ticket getTicketByID(int id);
	//public Ticket getTicketByStatus();
	public Ticket getTicketByEmpID(int empID);
	public Ticket getTicketByManID(int manID);
	public List<Ticket> getPendTickets();
	public List<Ticket> getDenTickets();
	public List<Ticket> getApprTickets();
	public List<Ticket> getLodTickets();
	public List<Ticket> getFoodTickets();
	public List<Ticket> getTravTickets();
	public List<Ticket> getOthTickets();
}
