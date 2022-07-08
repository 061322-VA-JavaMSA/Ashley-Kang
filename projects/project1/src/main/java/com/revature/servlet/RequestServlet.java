package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.TicketNotCreatedException;
import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.services.TicketService;
import com.revature.services.UserService;
import com.revature.util.CorsFix;

public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TicketService ts = new TicketService();
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();

       
    
	//retrieve tickets
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorsFix.addCorsHeader(request.getRequestURI(), response);
		
		HttpSession session = request.getSession();
		int userID = (int) session.getAttribute("user_id");
		User u = us.getUserByID(userID);
		
		if(u.getRole() == User.Role.MANAGER) {
			try(PrintWriter pw = response.getWriter()){
				pw.write(om.writeValueAsString(ts.getAllTickets()));
				response.setStatus(200);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(u.getRole() == User.Role.EMPLOYEE) {
			
			try(PrintWriter pw = response.getWriter()){
				pw.write(om.writeValueAsString(ts.getTicketByEmpID(userID)));
				response.setStatus(200);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else
		{
			//invalid operation
			response.setStatus(400);
		}
	}

	//submit tickets via employee
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorsFix.addCorsHeader(request.getRequestURI(), response);
		
		String ticketType = request.getParameter("type");
		String ticketDesc = request.getParameter("desc");
		Float ticketAmount = Float.parseFloat(request.getParameter("amount"));
	
		
		//retrieve employees id
		HttpSession session = request.getSession();
		Ticket t = new Ticket();
		t.setStatus(Ticket.Status.PENDING);
		t.setType(Ticket.Type.valueOf(ticketType));
		t.setEmployee_id((int) session.getAttribute("user_id"));
		t.setTicket_amount(ticketAmount);
		t.setTicket_desc(ticketDesc);
		try {
			ts.insertTicket(t);
			if(t.getId() == -1) {
				throw new TicketNotCreatedException();
			}
		}catch (TicketNotCreatedException e){
			e.printStackTrace();
		}
		
		//created the ticket successfully
		response.setStatus(201);
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}

}
