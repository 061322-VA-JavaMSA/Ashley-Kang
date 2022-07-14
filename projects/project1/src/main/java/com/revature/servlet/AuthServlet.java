package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.CorsFix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();
	private static Logger log = LogManager.getLogger(AuthServlet.class);
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorsFix.addCorsHeader(request.getRequestURI(), response);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			User principal = us.getUserByName(username);
			if(!principal.getUser_pass().equals(password)) {
				log.error("User not found");
				throw new UserNotFoundException();
			}else {
				request.getSession().setAttribute("userID", principal.getId());
				//System.out.println(request.getSession().getAttribute("userID"));
				request.getSession().setAttribute("userRole", principal.getRole());
				response.setStatus(200);
				
				String cookie = response.getHeader("Set-Cookie") + "; SameSite=None; Secure";
				response.setHeader("Set-Cookie", cookie);;
				
				UserDTO principalDTO = new UserDTO(principal);
				try(PrintWriter pw = response.getWriter()){
					pw.write(om.writeValueAsString(principalDTO));
					response.setStatus(200);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}catch (UserNotFoundException e) {
			response.sendError(400, "Login unsuccessful.");
			log.error("Status code: 400. Login unsuccessful" + e.fillInStackTrace());
		}
	}
	
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		
		HttpSession session = req.getSession();
		
		session.invalidate();
		log.info("session terminated.");
	}
	
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}
}
