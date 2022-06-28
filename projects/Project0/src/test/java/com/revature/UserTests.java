package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.daos.StorePostgres;
import com.revature.daos.UserPostgres;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Item;
import com.revature.models.Manager;

public class UserTests {
	static UserPostgres ups;
	static StorePostgres sps;
	
	
	@BeforeAll
	public static void setUp() {
		ups = new UserPostgres();
		sps = new StorePostgres();
	}
	
	@AfterAll
	public static void afterBehavior() {
		sps.deleteAll();
		ups.deleteAll(false);
		ups.deleteAll(true);
		System.out.println("The test has concluded.");
	}
	
	
	//id = 1;
	
	@Test
	public void isInitiatedCust() {
		System.out.println("The current test has started.");
		Customer cust = new Customer();
		cust.setName("newCust");
		cust.setUserEmail("newCust@email.com");
		cust.setUserName("newCust1");
		cust.setUserPassword("password");
		cust.setCard("123456789");
		ups.createCustomer(cust);
		
		Customer actual = ups.retrieveCByUsername("newCust1");
		assertEquals(cust, actual);
		}
	
	
	@Test
	public void isInitiatedEmp() {
		System.out.println("The current test has started.");
		Employee emp = new Employee();
		emp.setName("newEmp");
		emp.setUserEmail("newEmp@email.com");
		emp.setUserName("newEmp1");
		emp.setUserPassword("password");
		ups.createEmployee(emp);
		
		Employee actual = ups.retrieveEByUsername("newEmp1");
		
		assertEquals(emp, actual);
	}
	
	@Test
	public void isInitiatedMan() {
		System.out.println("The current test has started.");
		Manager man = new Manager();
		man.setName("newMan");
		man.setUserEmail("newMan@email.com");
		man.setUserName("newMan1");
		man.setUserPassword("password");
		ups.createManager(man);
		
		Manager actual = ups.retrieveMByUsername("newMan1");
		assertEquals(man, actual);

	}
	
	
	@Test
	public void createItem() {
		System.out.println("The current test has started.");
		Item i = new Item();
		float cost = 40.0f;
		i.setItemCost(cost);
		i.setItemName("the Unit Tester");
		i.setItemDescription("For testing purposes");
		Item expected = sps.createItem(i);
		Item actual = sps.retrieveByName("the Unit Tester");
		assertEquals(expected,actual);
	}
	
	//id = 2;
	
	@Test
	public void retrieveItemID() {
		System.out.println("The current test has started.");
		Item i = new Item();
		float cost = 40.0f;
		i.setItemCost(cost);
		i.setItemName("rIID");
		i.setItemDescription("For testing purposes");
		Item expected = sps.createItem(i);
		Item actual = sps.retrieveById(expected.getItemID());
		assertEquals(expected.getItemID(),actual.getItemID());
	}
	
	@Test
	public void retrieveCustomerID() {
		System.out.println("The current test has started.");
		Customer cust = new Customer();
		cust.setName("rCID");
		cust.setUserEmail("newCust@email.com");
		cust.setUserName("rCID");
		cust.setUserPassword("password");
		cust.setCard("123456789");
		ups.createCustomer(cust);
		
		Customer actual = ups.retrieveCByID(cust.getUserID());
		assertEquals(cust.getUserID(), actual.getUserID());
	}
	
	@Test
	public void rEID() {
		System.out.println("The current test has started.");
		Employee emp = new Employee();
		emp.setName("rEID");
		emp.setUserEmail("newEmp@email.com");
		emp.setUserName("rEID");
		emp.setUserPassword("password");
		ups.createEmployee(emp);
		
		Employee actual = ups.retrieveEByID(emp.getUserID());
		
		assertEquals(emp.getUserID(), actual.getUserID());
	}
	
	@Test
	public void rMID() {
		System.out.println("The current test has started.");
		Manager man = new Manager();
		man.setName("rMID");
		man.setUserEmail("newMan@email.com");
		man.setUserName("rMID");
		man.setUserPassword("password");
		ups.createManager(man);
		
		Manager actual = ups.retrieveMByID(man.getUserID());
		assertEquals(man.getUserID(), actual.getUserID());
	}
	
	
}

