package com.revature.services;

import java.util.List;

import com.revature.daos.UserPostgres;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Item;

public class CustomerService {
	UserPostgres ups = new UserPostgres();
	
	public Customer createCustomer(Customer c) {
		Customer cc = ups.createUser(c);
		System.out.println("User: "+ cc + " was created.");
		return cc;
	}
	
	public Employee createEmployee(Employee e) {
		Employee ee = ups.createUser(e);
		System.out.println("User: "+ ee + " was created.");
		return ee;
	}

	public Customer retrieveCByID(int id) {
		Customer cc = ups.retrieveCByID(id);
		System.out.println("User: " + cc);
		return cc;
	}
	
	
	public Employee retrieveEByID(int id) {
		Employee ee = ups.retrieveEByID(id);
		System.out.println("User: " + ee);
		return ee;
	}
	
	public Customer retrieveCByUsername(String username) {
		Customer cc = ups.retrieveCByUsername(username);
		System.out.println("User: " + cc);
		return cc;
	}
	
	public Employee retrieveEByUsername(String username) {
		Employee ee = ups.retrieveEByUsername(username);
		System.out.println("User: " + ee);
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
	public boolean updateUser(int id, boolean isCust) {
		return ups.updateUser(id, isCust);
	}
	
	//make offer for item
	public void makeOffer(int amount, int userID, int itemID) {
		ups.makeOffer(amount, userID, itemID);
	}
	
	//view all owned items
	public List<Item> ownedItems(int userID){
		List<Item> ownerInv = ups.ownedItems(userID);
		for(int i = 0; i< ownerInv.size(); i++) {
			System.out.println("Item:" + ownerInv.get(i));
		}
		return ownerInv;
	}
	
	//view payments left
	public void paymentsLeft(int userID) {
		ups.paymentsLeft(userID);
	}


}
