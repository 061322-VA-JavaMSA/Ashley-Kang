package com.revature.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.ApplicationDriver;
import com.revature.daos.UserPostgres;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Item;
import com.revature.models.Manager;
import com.revature.util.ConnectionsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerService {
	private static Logger log = LogManager.getLogger(CustomerService.class);
	UserPostgres ups = new UserPostgres();
	
	public void createCustomer(Customer c) {
		ups.createCustomer(c);
		
	}
	
	public void createEmployee(Employee e) {
		ups.createEmployee(e);
		
	}
	
	public void createManager(Manager m) {
		ups.createManager(m);
	}

	public Customer retrieveCByID(int id) {
		Customer cc = ups.retrieveCByID(id);
		
		return cc;
	}
	
	
	public Employee retrieveEByID(int id) {
		Employee ee = ups.retrieveEByID(id);
		//System.out.println("User: " + ee);
		return ee;
	}
	
	public Customer retrieveCByUsername(String username) {
		Customer cc = ups.retrieveCByUsername(username);
		//System.out.println("User: " + cc);
		return cc;
	}
	
	public Employee retrieveEByUsername(String username) {
		Employee ee = ups.retrieveEByUsername(username);
		//System.out.println("User: " + ee);
		return ee;
	}
	
	public void retrieveCustomers(){
		List<Customer> customers = ups.retrieveCustomers();
		System.out.println("Your customers are: ");
		//System.out.println(ownerInv.size());
		for(int i = 0; i< customers.size(); i++) {
			log.info("Customer: " + customers.get(i).toString());
			System.out.println("\tCustomer ID: " + customers.get(i).getUserID());
			System.out.println("\tCustomer Name: " + customers.get(i).getName());
		}
	}
	
	public void retrieveEmployees(){
		List<Employee> employees = ups.retrieveEmployees();
		System.out.println("Your customers are: ");
		//System.out.println(ownerInv.size());
		for(int i = 0; i< employees.size(); i++) {
			log.info("Employee: " + employees.get(i).toString());
			System.out.println("\tEmployee ID: " + employees.get(i).getUserID());
			System.out.println("\tEmployee Name: " + employees.get(i).getName());
		}
	}
	
	
	public boolean deleteByID(int id, boolean isCust) {
		return ups.deleteByID(id, isCust);
	}
	public boolean deleteAll(boolean isCust) {
		return ups.deleteAll(isCust);
	}
	
	//update User info
	public void updateUser(int id, boolean isCust, Scanner sc) {
		ups.updateUser(id, isCust, sc);	
	}
	
	//make offer for item
	public void makeOffer(float amount, int userID, int itemID) {
		ups.makeOffer(amount, userID, itemID);
	}
	
	//view all owned items
	public void ownedItems(int userID){
		ArrayList<Item> ownerInv = ups.ownedItems(userID);
		System.out.println("Your owned items: ");
		//System.out.println(ownerInv.size());
		for(int i = 0; i< ownerInv.size(); i++) {
			log.info("Item: " + ownerInv.get(i).toString());
			System.out.println("\tItem ID: "+ ownerInv.get(i).getItemID());
			System.out.println("\tItem Name: " + ownerInv.get(i).getItemName());
			System.out.println("\tItem Description: " + ownerInv.get(i).getItemDescription());
		}
	}
	
	//view payments left
	public void paymentsLeft(int userID) {
		ups.paymentsLeft(userID);
	}

	public Manager retrieveMByUsername(String username) {
		Manager m = ups.retrieveMByUsername(username);
		return m;
	}

	public void updateManager(int userID, Scanner sc) {
		ups.updateManager(userID, sc);
		
	}


}
