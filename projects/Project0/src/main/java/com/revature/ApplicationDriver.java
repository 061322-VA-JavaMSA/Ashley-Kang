package com.revature;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Item;
import com.revature.models.Manager;
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
	static boolean isManager;
	
	static CustomerService cs;
	static StoreService ss;
	static Manager m;
	static Employee e;
	static Customer c;
	
	
	private static Logger log = LogManager.getLogger(ApplicationDriver.class);
	
	static void menuScreen(String input) {
		switch(input) {
		case "1":
			System.out.println("Create Account");
			System.out.println("Do you have an authorization key? [Y] or [N]");
			if(sc.nextLine().equals("Y")) {
				System.out.println("Are you a manager? [Y] or [N]");
				if(sc.nextLine().equals("Y")) {
					m = new Manager();
					System.out.println("Please enter your name");
					m.setName(sc.nextLine());
					System.out.println("Please enter your Username");
					m.setUserName(sc.nextLine());
					System.out.println("Please enter your Password");
					m.setUserPassword(sc.nextLine());
					System.out.println("Please enter your Email");
					m.setUserEmail(sc.nextLine());
					System.out.println("Please enter your authorization key");
					String key = sc.nextLine();
					if(m.checkKey(key)) {
						isCustomer = false;
						isManager = true;
						cs.createManager(m);
						loginOptionSelected = true;
						log.info("Manager Created: "+ m);
					}else {
						System.out.println("Incorrect credentials. Exiting.");
						break;
					}
				}else{
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
				if(!e.checkKey(sc.nextLine())) {
					System.out.println("Incorrect credentials. Exiting.");
					break;
				}
				isCustomer = false;
				isManager = false;
				cs.createEmployee(e);
				loginOptionSelected = true;
				log.info("Employee Created: "+ e);
				}
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
				log.info("Customer Created: "+ c);
			}else {
				System.out.println("Input not recognized. Please try again.");
			}	
			break;
		case "2":
			System.out.println("Login");
			System.out.println("Are you logging in as a customer or an employee?\n\"1\" for Customer, \"2\" for Employee \"3\" for Manager");
			String choice = sc.nextLine();
			if(choice.equals("1")) {
				isCustomer = true;
			}else if(choice.equals("2")) {
				isCustomer = false;
			}else if(choice.equals("3")){
				isCustomer = false;
				isManager = true;
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
					log.info("Logged in user: " + c);
				}else {
					System.out.println("Invalid password");
					break;
				}
				
			}else {
				if(isManager) {
					m = cs.retrieveMByUsername(username);
					if(m.getUserPassword().equals(password)){
						System.out.println("Login Successful");
						log.info("Logged in user: " + m);
					}else {
						System.out.println("Invalid password");
						break;
					}
				}else {
					e = cs.retrieveEByUsername(username);
					if(e.getUserPassword().equals(password)){
						System.out.println("Login Successful");
						log.info("Logged in user: " + e);
					}else {
						System.out.println("Invalid password");
						break;
					}
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
		System.out.println("Please select an option from below. \n 1: Show Store Inventory\n 2: Make a Purchase\n 3: Show Owned Items\n 4: Update Account\n 5: Show current payments\n 6: Quit");
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
			showPayments();
			break;
		case "6":
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
		System.out.println("Please select an option from below. \n 1: Show Store Inventory\n 2: Show Purchase History\n 3: Create an Item\n 4: View Item Offers\n 5: Delete a Single Item\n 6: Delete All Items\n 7: Update an Item\n 8: Update Account\n 9: Quit");
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
			viewItemOffers();
			break;
		case "5":
			deleteID();
			break;
		case "6":
			deleteAll();
			break;
		case "7":
			updateItem();
			break;
		case "8":
			account(false);
			//storeMenuSelected = true;
			break;
		case "9":
			//quit
			System.out.println("Thank you for shopping with us today. Goodbye.");
			storeMenuSelected = true;
			break;
		default:
			System.out.println("Input not found. Please try again.");
			break;
		}
	}
	
	static void storeMenuManager() {
		System.out.println("Please select an option from below. \n 1: Show Store Inventory\n 2: Show Purchase History\n 3: Create an Item\n 4: View Item Offers\n 5: Delete a Single Item\n 6: Delete All Items\n 7: Update an Item\n 8: View Customers\n 9: View Employees\n 10: Fire a Single Employee\n 11: Fire all Employees\n12: Account\n 13: Quit");
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
			viewItemOffers();
			break;
		case "5":
			deleteID();
			break;
		case "6":
			deleteAll();
			break;
		case "7":
			updateItem();
			break;
		case "8":
			retrieveCustomers();
			//storeMenuSelected = true;
			break;
		case "9":
			retrieveEmployees();
			//storeMenuSelected = true;
			break;
		case "10":
			fireEmployee();
			//storeMenuSelected = true;
			break;
		case "11":
			fireAllEmployees();
			//storeMenuSelected = true;
			break;
		case "12":
			account(false);
			//storeMenuSelected = true;
			break;
		case "13":
			//quit
			System.out.println("Thank you for shopping with us today. Goodbye.");
			storeMenuSelected = true;
			break;
		default:
			System.out.println("Input not found. Please try again.");
			break;
		}
	}
	
	private static void fireAllEmployees() {
		System.out.println("Are you sure you want to fire all? [Y] or [N]");
		String choice = sc.nextLine();
		if(choice.equals("Y")) {
			cs.deleteAll(false);
		}
		
	}

	private static void fireEmployee() {
		System.out.println("What is the id number of the employee you want to fire?");
		int id = sc.nextInt();
		sc.nextLine();
		cs.deleteByID(id,false);
		
	}

	private static void retrieveEmployees() {
		cs.retrieveEmployees();
		
	}

	private static void retrieveCustomers() {
		cs.retrieveCustomers();
		
	}

	static void showInventory() {
		System.out.println("Inventory\nPlease note the Item ID of any items you would like.");
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
	
	static void showPayments() {
		System.out.println("Your current payments");
		cs.paymentsLeft(c.getUserID());
	}
	
	static void viewItemOffers() {
		System.out.println("What is the id of the item you want to view?");
		int id = sc.nextInt();
		sc.nextLine();
		ss.itemOffer(id, sc);
	}
	
	static void deleteID() {
		System.out.println("What is the id number of the item you want to delete?");
		int id = sc.nextInt();
		sc.nextLine();
		ss.deleteByID(id);
	}
	
	static void deleteAll() {
		System.out.println("Are you sure you want to delete all? [Y] or [N]");
		String choice = sc.nextLine();
		if(choice.equals("Y")) {
			ss.deleteAll();
		}
		
	}
	
	static void updateItem() {
		System.out.println("What is the id number of the item you want to update?");
		int id = sc.nextInt();
		sc.nextLine();
		ss.updateItem(id, sc);
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
			if(isManager) {
				cs.updateManager(m.getUserID(),sc);
			}else {
				cs.updateUser(e.getUserID(), isCust,sc);
			}
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
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
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
			if(isManager) {
				System.out.println("Welcome " + m.getName());
				while(storeMenuSelected == false){
					storeMenuManager();
				}
			}else {
				System.out.println("Welcome " + e.getName());
				while(storeMenuSelected == false){
					storeMenuEmployee();
				}
			}
			
		}
		
		sc.close();
		
	}

}
