package com.revature.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Item;
//import com.revature.models.User;
import com.revature.models.Manager;

public interface UserDAO {
	//Create Customer/Employee
	void createCustomer(Customer c);
	void createEmployee(Employee e);
	void createManager(Manager m);
	
	//Retrieve Customer/Employee
	Customer retrieveCByID(int id);
	Employee retrieveEByID(int id);
	Manager retrieveMByID(int id);
	Customer retrieveCByUsername(String username);
	Employee retrieveEByUsername(String username);
	Manager retrieveMByUsername(String username);
	
	//Retrieve all customers/employees
	List<Customer> retrieveCustomers();
	List<Employee> retrieveEmployees();
	
	
	//delete customers/employees
	boolean deleteByID(int id, boolean isCust);
	boolean deleteAll(boolean isCust);
	
	//update User info
	boolean updateUser(int id, boolean isCust, Scanner sc);
	boolean updateManager(int id, Scanner sc);
	
	//make offer for item
	void makeOffer(float amount, int userID, int itemID);
	
	//view all owned items
	ArrayList<Item> ownedItems(int userID);
	
	//view payments left
	void paymentsLeft(int userID);
	
	//make a payment?

}
