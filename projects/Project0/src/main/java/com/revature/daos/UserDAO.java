package com.revature.daos;

import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Item;
//import com.revature.models.User;

public interface UserDAO {
	//Create Customer/Employee
	Customer createUser(Customer c);
	Employee createUser(Employee e);
	
	//Retrieve Customer/Employee
	Customer retrieveCByID(int id);
	Employee retrieveEByID(int id);
	Customer retrieveCByUsername(String username);
	Employee retrieveEByUsername(String username);
	
	//Retrieve all customers/employees
	List<Customer> retrieveCustomers();
	List<Employee> retrieveEmployees();
	
	
	//delete customers/employees
	boolean deleteByID(int id, boolean isCust);
	boolean deleteAll(boolean isCust);
	
	//update User info
	boolean updateUser(int id, boolean isCust);
	
	//make offer for item
	void makeOffer(int amount, int userID, int itemID);
	
	//view all owned items
	List<Item> ownedItems(int userID);
	
	//view payments left
	void paymentsLeft(int userID);
	
	//make a payment?

}
