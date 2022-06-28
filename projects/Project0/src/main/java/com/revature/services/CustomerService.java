package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.daos.UserPostgres;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Item;

public class CustomerService {
	UserPostgres ups = new UserPostgres();
	
	public void createCustomer(Customer c) {
		ups.createCustomer(c);
		//System.out.println("User: "+ cc + " was created.");
		
	}
	
	public void createEmployee(Employee e) {
		ups.createEmployee(e);
		//System.out.println("User: "+ ee + " was created.");
		
	}

	public Customer retrieveCByID(int id) {
		Customer cc = ups.retrieveCByID(id);
		//System.out.println("User: " + cc);
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
	
	public List<Customer> retrieveCustomers(){
		List<Customer> cc = ups.retrieveCustomers();
		for(int i = 0; i< cc.size();i++) {
			System.out.println("User: " + cc.get(i));
		}
		return cc;
	}
	
	public List<Employee> retrieveEmployees(){
		List<Employee> ee = ups.retrieveEmployees();
		for(int i = 0; i< ee.size();i++) {
			System.out.println("User: " + ee.get(i));
		}
		return ee;
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
			System.out.println("\tItem Name: " + ownerInv.get(i).getItemName());
			System.out.println("\tItem Description: " + ownerInv.get(i).getItemDescription());
		}
	}
	
	//view payments left
	public void paymentsLeft(int userID) {
		ups.paymentsLeft(userID);
	}


}
