package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.CorsFix;

public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();
    
	//retrieve account details
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorsFix.addCorsHeader(request.getRequestURI(), response);
		String pathInfo = request.getPathInfo();
		int userID = Integer.parseInt(pathInfo.substring(1));
		User u = us.getUserByID(userID);
		
		try(PrintWriter pw = response.getWriter()){
			pw.write(om.writeValueAsString(u));
			response.setStatus(200);
			pw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorsFix.addCorsHeader(request.getRequestURI(), response);
		
		
		String pathInfo = request.getPathInfo();
		int id = Integer.parseInt(pathInfo.substring(1));
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		User u = us.getUserByID(id);
		
		if(username!="") {
			us.updateUN(username, u.getUsername());
			response.setStatus(200);
			
		}
		if(password!="") {
			us.updateP(password, u.getId());
			response.setStatus(200);
		
		}
		if(name!= "") {
			us.updateN(name, u.getId());
			response.setStatus(200);
		}
	
		//updated info correctly
		try(PrintWriter pw = response.getWriter()){
			pw.write(om.writeValueAsString(u));
			pw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}

}
