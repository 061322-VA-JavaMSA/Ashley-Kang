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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ApplicationDriver {
	static Scanner sc;
	
	static boolean loginOptionSelected;
	static boolean storeMenuSelected;
	static boolean isCustomer;
	
	static CustomerService cs;
	static StoreService ss;
	static Employee e;
	static Customer c;
	
	//private static Logger log = LogManager.getLogger(Driver.class);
	//log.info(as.login(username, password));
	//log.error("Login exception was thrown: " + e.fillInStackTrace());
	//static Item i;
	
	static void menuScreen(String input) {
		switch(input) {
		case "1":
			System.out.println("Create Account");
			System.out.println("Do you have an authorization key? [Y] or [N]");
			if(sc.nextLine().equals("Y")) {
				e = new Employee();
				System.out.println("Please enter your name");
				e.setName(sc.nextLine());
				System.out.println("Please enter your Username");
				e.setUserName(sc.nextLine());
				System.out.println("Please enter your Password");
				e.setUserPassword(sc.nextLine());
				System.out.println("Please enter your Email");
				e.setUserEmail(sc.nextLine());
				System.out.println("Please enter your authorization key");
				e.setKey(sc.nextLine());
				isCustomer = false;
				cs.createEmployee(e);
				loginOptionSelected = true;
			}else if(sc.nextLine().equals("N")) {
				c = new Customer();
				System.out.println("Please enter your name");
				c.setName(sc.nextLine());
				System.out.println("Please enter your Username");
				c.setUserName(sc.nextLine());
				System.out.println("Please enter your Password");
				c.setUserPassword(sc.nextLine());
				System.out.println("Please enter your Email");
				c.setUserEmail(sc.nextLine());
				System.out.println("Please enter your payment card number");
				c.setCard(sc.nextLine());
				cs.createCustomer(c);
				isCustomer = true;
				loginOptionSelected = true;
			}else {
				System.out.println("Input not recognized. Please try again.");
			}	
			break;
		case "2":
			System.out.println("Login");
			System.out.println("Are you logging in as a customer or an employee?\n\"1\" for Customer, \"2\" for Employee");
			String choice = sc.nextLine();
			if(choice.equals("1")) {
				isCustomer = true;
			}else if(choice.equals("2")) {
				isCustomer = false;
			}else {
				System.out.println("Input not understood.");
				break;
			}
			System.out.println("Please enter your username");
			String username = sc.nextLine();
			System.out.println("Please enter your password");
			String password = sc.nextLine();
			
			if(isCustomer) {
				c = cs.retrieveCByUsername(username);
				if(c.getUserPassword().equals(password)){
					System.out.println("Login Successful");
					System.out.println(c);
				}else {
					System.out.println("Invalid password");
				}
				
			}else {
				e = cs.retrieveEByUsername(username);
				if(e.getUserPassword().equals(password)){
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
	
	static void storeMenuCustomer() {
		System.out.println("Please select an option from below. \n 1: Show Store Inventory\n 2: Make a Purchase\n 3: Show Owned Items\n 4: Update Account\n 5: Quit");
		String input = sc.nextLine();
		switch(input) {
		case "1":
			showInventory();
			//storeMenuSelected = true;
			break;
		case "2":
			makePurchase();
			//storeMenuSelected = true;
			break;
		case "3":
			showOwnedItems();
			//storeMenuSelected = true;
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
	
	static void storeMenuEmployee() {
		System.out.println("Please select an option from below. \n 1: Show Store Inventory\n 2: Show Purchase History\n 3: Create an Item\n 4: Update Account\n 5: Quit");
	  	String input = sc.nextLine();
	  	
		switch(input) {
		case "1":
			showInventory();
			//storeMenuSelected = true;
			break;
		case "2":
			purchaseHistory();
			//storeMenuSelected = true;
			break;
		case "3":
			createItem();
			//storeMenuSelected = true;
			break;
		case "4":
			account(false);
			//storeMenuSelected = true;
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
		System.out.println("Inventory\nPlease note the Item ID of any items you would like to purchase.");
		ss.retrieveInventory();
	}
	
	static void makePurchase() {
		System.out.println("What is the item ID?");
		int itemID = sc.nextInt();
		sc.nextLine();
		
		System.out.println("How much would you like to offer for this item?");
		cs.makeOffer(sc.nextFloat(), c.getUserID(), itemID);
		sc.nextLine();
	}
	
	static void purchaseHistory() {
		if(isCustomer) {
			cs.ownedItems(c.getUserID());
		}
		else {
			ss.viewPayments();
		}
	}
	
	static void createItem() {
		Item i = new Item();
		System.out.println("Enter Item Name");
		i.setItemName(sc.nextLine());
		System.out.println("Enter Item Cost");
		i.setItemCost(sc.nextFloat());
		sc.nextLine();
		System.out.println("Enter Item Description");
		i.setItemDescription(sc.nextLine());
		ss.createItem(i);
	}
	
	static void account(boolean isCust) {
		if(isCust) {
			cs.updateUser(c.getUserID(), isCust,sc);
		}else {
			cs.updateUser(e.getUserID(), isCust,sc);
		}
	}
	
	static void showOwnedItems() {
		cs.ownedItems(c.getUserID());
	}

	
	
	public static void main(String[] args) {
		cs = new CustomerService();
		ss = new StoreService();

		try {
			ConnectionsUtil.getConnectionFromFile();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		sc = new Scanner(System.in);
		
		System.out.println("Welcome to Revature Flower Shop!");
		System.out.println("Please choose an option: \n 1: Register a new account\n 2: Login \n 3: Exit the program");
		
		String choice = sc.nextLine();
		
		while(loginOptionSelected == false){
			ApplicationDriver.menuScreen(choice);
		}
		 
		if(isCustomer) {
			System.out.println("Welcome " + c.getName());
			while(storeMenuSelected == false){
				storeMenuCustomer();
			}
		}else {
			System.out.println("Welcome " + e.getName());
			while(storeMenuSelected == false){
				storeMenuEmployee();
			}
		}
		
		sc.close();
		
	}

}
