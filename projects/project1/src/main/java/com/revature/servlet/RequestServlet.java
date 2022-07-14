package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.services.TicketService;
import com.revature.services.UserService;
import com.revature.util.CorsFix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestServlet extends HttpServlet {
	private static Logger log = LogManager.getLogger(RequestServlet.class);
	private static final long serialVersionUID = 1L;
	private TicketService ts = new TicketService();
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();

       
    
	//retrieve tickets
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorsFix.addCorsHeader(request.getRequestURI(), response);
		
		
		String pathInfo = request.getPathInfo();
		if(pathInfo != null) {
			int userID = Integer.parseInt(pathInfo.substring(1));
			try(PrintWriter pw = response.getWriter()){
				pw.write(om.writeValueAsString(ts.getTicketByEmpID(userID)));
				response.setStatus(200);
			}catch (Exception e) {
				log.error("Exception thrown:" + e.fillInStackTrace());
				response.setStatus(400);			}
		}else {
			try(PrintWriter pw = response.getWriter()){
				pw.write(om.writeValueAsString(ts.getAllTickets()));
				response.setStatus(200);
			}catch (Exception e) {
				log.error("Exception thrown:" + e.fillInStackTrace());
				response.setStatus(400);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorsFix.addCorsHeader(request.getRequestURI(), response);
		String pathInfo = request.getPathInfo();
		
		User u = us.getUserByID(Integer.parseInt(pathInfo.substring(1)));
		if(u.getRole() == User.Role.MANAGER) {
			//manager updating ticket
			System.out.println(request.getParameter("ticketid"));
			int ticketid = Integer.parseInt(request.getParameter("ticketid"));
			String status = request.getParameter("status");
			ts.updateTicket(status, ticketid);
			ts.updateMTicket(ticketid, u.getId());
			log.info("updated ticket:" + ts.getTicketByID(ticketid));
			response.setStatus(200);
		}else {
			//employee creating ticket
			int id = Integer.parseInt(pathInfo.substring(1));
			String ticketType = request.getParameter("type");
			String ticketDesc = request.getParameter("desc");
			Float ticketAmount = Float.parseFloat(request.getParameter("amount"));
			Ticket t = new Ticket();
			t.setStatus("PENDING");
			t.setType(ticketType);
			t.setEmployee_id(id);
			t.setTicket_amount(ticketAmount);
			t.setTicket_desc(ticketDesc);
			ts.insertTicket(t);
			
			//created the ticket successfully
			try(PrintWriter pw = response.getWriter()){
				pw.write(om.writeValueAsString(t));
			}catch (Exception e) {
				log.error("Exception thrown:" + e.fillInStackTrace());
			}
			response.setStatus(201);
		}
		
	}
		
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}

}
