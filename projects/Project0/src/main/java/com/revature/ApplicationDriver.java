package com.revature;

import java.util.List;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Item;
import com.revature.services.CustomerService;
import com.revature.services.StoreService;
import com.revature.util.ConnectionsUtil;


public class ApplicationDriver {
	static Scanner sc;
	
	static boolean loginOptionSelected;
	static boolean storeMenuSelected;
	static boolean isCustomer;
	
	static CustomerService cs;
	static StoreService ss;
	static Employee e;
	static Customer c;
	
	static void menuScreen(String input) {
		switch(input) {
		case "1":
			System.out.println("Create Account");
			System.out.println("Do you have an authorization key? [Y] or [N]");
			if(sc.nextLine().equals("Y")|| sc.nextLine().equals("y")) {
				Employee newUser = new Employee();
				System.out.println("Please enter your name");
				newUser.setName(sc.nextLine());
				System.out.println("Please enter your Username");
				newUser.setUserName(sc.nextLine());
				System.out.println("Please enter your Password");
				newUser.setUserPassword(sc.nextLine());
				System.out.println("Please enter your Email");
				newUser.setUserEmail(sc.nextLine());
				System.out.println("Please enter your authorization key");
				newUser.setKey(sc.nextLine());
				isCustomer = false;
				cs.createEmployee(newUser);
				loginOptionSelected = true;
			}else if(sc.nextLine().equals("N")|| sc.nextLine().equals("n")) {
				Customer newUser = new Customer();
				System.out.println("Please enter your name");
				newUser.setName(sc.nextLine());
				System.out.println("Please enter your Username");
				newUser.setUserName(sc.nextLine());
				System.out.println("Please enter your Password");
				newUser.setUserPassword(sc.nextLine());
				System.out.println("Please enter your Email");
				newUser.setUserEmail(sc.nextLine());
				System.out.println("Please enter your payment card number");
				newUser.setCard(sc.nextLine());
				cs.createCustomer(newUser);
				isCustomer = true;
				loginOptionSelected = true;
			}else {
				System.out.println("Input not recognized. Please try again.");
			}	
			break;
		case "2":
			System.out.println("Login");
			System.out.println("Are you logging in as a customer or an employee?\n\"1\" for Customer, \"2\" for Employee");
			if(sc.nextLine().equals("1")) {
				isCustomer = true;
			}else if(sc.nextLine().equals("2")) {
				isCustomer = false;
			}else {
				System.out.println("Input not understood.");
			}
			System.out.println("Please enter your username");
			String username = sc.nextLine();
			System.out.println("Please enter your password");
			String password = sc.nextLine();
			
			if(isCustomer) {
				c = cs.retrieveCByUsername(username);
				if(c.getUserPassword().equals(password)){
					System.out.println("Login Successful");
				}else {
					System.out.println("Invalid password");
				}
				
			}else {
				e = cs.retrieveEByUsername(username);
				if(c.getUserPassword().equals(password)){
					System.out.println("Login Successful");
				}else {
					System.out.println("Invalid password");
				}
			}
			loginOptionSelected = true;
			break;
		case "3":
			System.out.println("Thank you for shopping with us today. Goodbye.");
			loginOptionSelected = true;
			break;
		default:
			System.out.println("Input not found. Please try again.");
			break;
			
		}
	}
	
	static void storeMenuCustomer(String input) {
		switch(input) {
		case "1":
			showInventory();
			storeMenuSelected = true;
			break;
		case "2":
			makePurchase();
			storeMenuSelected = true;
			break;
		case "3":
			showOwnedItems();
			storeMenuSelected = true;
			break;
		case "4":
			account(true);
			break;
		case "5":
			//quit
			System.out.println("Thank you for shopping with us today. Goodbye.");
			storeMenuSelected = true;
			break;
		default:
			System.out.println("Input not found. Please try again.");
			break;
		}
	}
	
	static void storeMenuEmployee(String input) {
		switch(input) {
		case "1":
			showInventory();
			storeMenuSelected = true;
			break;
		case "2":
			purchaseHistory();
			storeMenuSelected = true;
			break;
		case "3":
			createItem();
			storeMenuSelected = true;
			break;
		case "4":
			account(false);
			storeMenuSelected = true;
			break;
		case "5":
			//quit
			System.out.println("Thank you for shopping with us today. Goodbye.");
			storeMenuSelected = true;
			break;
		default:
			System.out.println("Input not found. Please try again.");
			break;
		}
	}
	
	static void showInventory() {
		List<Item> theStore = ss.retrieveInventory();
	}
	
	static boolean makePurchase() {
		return false;
	}
	
	static void purchaseHistory() {
		
	}
	
	static boolean createItem() {
		return false;
	}
	
	static void account(boolean isCust) {
		
	}
	
	static void showOwnedItems() {
		
	}

	
	
	public static void main(String[] args) {
		cs = new CustomerService();

		try {
			ConnectionsUtil.getConnectionFromFile();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		sc = new Scanner(System.in);
		
		System.out.println("Welcome to Revature Flower Shop!");
		System.out.println("Please choose an option: \n 1: Register a new account\n 2: Login \n 3: Exit the program");
		
		String choice = sc.nextLine();
		
		
		boolean optionSelected = false;
		while(optionSelected == false){
			ApplicationDriver.menuScreen(choice);
		}
		 
		if(isCustomer) {
			System.out.println("Welcome " + c.getName());
			System.out.println("Please select an option from below.");
			while(storeMenuSelected == false){
			  	storeMenuCustomer(sc.nextLine());
			}
		}else {
			System.out.println("Welcome " + e.getName());
			System.out.println("Please select an option from below.");
			while(storeMenuSelected == false){
			  	storeMenuEmployee(sc.nextLine());
			}
		}
		
		sc.close();
		
	}

}
