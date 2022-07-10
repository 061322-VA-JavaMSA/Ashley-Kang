package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.CorsFix;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();

	//view all employees
	//view single employee?
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorsFix.addCorsHeader(request.getRequestURI(), response);
		String pathInfo = request.getPathInfo();
		
		if(pathInfo == null) {
			List<UserDTO> users = us.getUsers();
			try(PrintWriter pw = response.getWriter()){
				pw.write(om.writeValueAsString(users));
				response.setStatus(200);
				pw.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			int id = Integer.parseInt(pathInfo.substring(1));
			try (PrintWriter pw = response.getWriter()) {
				
				User u = us.getUserByID(id);
				UserDTO uDTO = new UserDTO(u);
				pw.write(om.writeValueAsString(uDTO));

				response.setStatus(200);
			} catch (Exception e) {
				response.setStatus(404);
			}
		}
	
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}
}
