package com.revature.services;

import com.revature.daos.UserPostgres;
import com.revature.models.Customer;
import com.revature.models.Employee;

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

	public void retrieveUser() {
		// TODO Auto-generated method stub
		
	}
}
