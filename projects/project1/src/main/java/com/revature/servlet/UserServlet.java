package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.CorsFix;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<User> users = us.getUsers();
		PrintWriter pw = response.getWriter();
		//pw.write("Hello from UserServlet");
		pw.write(users.get(1).toString());
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		CorsFix.addCorsHeader(request.getRequestURI(), response);
		String username = request.getParameter("username");

		try(PrintWriter pw = response.getWriter()){
			pw.write(om.writeValueAsString(us.getUserByName(username)));
			response.setStatus(200);
		}
		
		//used to check if it works
		System.out.println(us.getUserByName(username));
		//response.setStatus(201);
		
		
		
		
	}

}
