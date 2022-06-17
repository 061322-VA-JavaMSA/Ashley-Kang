package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.Customer;
import com.revature.models.Employee;

public class UserTests {
	static Customer cust;
	static Employee emp;
	
	
	@BeforeAll
	public static void setUp() {
		cust = new Customer();
		emp = new Employee();
	}
	
	@AfterAll
	public static void afterBehavior() {
		System.out.println("The test has concluded.");
	}
	
	@BeforeEach
	public void beforeBehavior() {
		System.out.println("The current test has started.");
	}
	
	@Test
	public void isInitiatedCust() {
		cust.setName("newCust");
		cust.setUserEmail("newCust@email.com");
		cust.setUserName("newCust1");
		cust.setUserPassword("password");
		cust.setCard("123456789");
		
		String expected = "newCust";
		String actual = cust.getName();
		assertEquals(expected, actual);
		
		expected = "newCust@email.com";
		actual = cust.getUserEmail();
		assertEquals(expected, actual);
		
		expected = "newCust1";
		actual = cust.getUserName();
		assertEquals(expected, actual);
		
		expected = "password";
		actual = cust.getUserPassword();
		assertEquals(expected, actual);
		
		expected = "123456789";
		actual = cust.getCard();
		assertEquals(expected, actual);
		
		System.out.println(cust.toString());
		}
	
	@Test
	public void isInitiatedEmp() {
		emp.setName("newEmp");
		emp.setUserEmail("newEmp@email.com");
		emp.setUserName("newEmp1");
		emp.setUserPassword("password");
		emp.setKey("123456789");
		
		String expected = "newEmp";
		String actual = emp.getName();
		assertEquals(expected, actual);
		
		expected = "newEmp@email.com";
		actual = emp.getUserEmail();
		assertEquals(expected, actual);
		
		expected = "newEmp1";
		actual = emp.getUserName();
		assertEquals(expected, actual);
		
		expected = "password";
		actual = emp.getUserPassword();
		assertEquals(expected, actual);
		
		expected = "123456789";
		actual = emp.getKey();
		assertEquals(expected, actual);
		
		System.out.println(emp.toString());
	}
}
