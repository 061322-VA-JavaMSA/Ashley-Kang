package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.util.ConnectionsUtil;


public class ApplicationDriver {
	static Scanner sc;
	//static boolean optionSelected;
	//static User?
	
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
				System.out.println(newUser);
				//optionSelected = true;
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
				System.out.println(newUser);
				//optionSelected = true;
			}else {
				//System.out.println("Input not recognized. Please try again.");
			}	
			break;
		case "2":
			System.out.println("Login");
			System.out.println("Please enter your username");
			String username = sc.nextLine();
			System.out.println("Please enter your password");
			String password = sc.nextLine();
			//Something retrieves the user
			//UserPostgres.retrievebyusername?
			break;
		case "3":
			System.out.println("Thank you for shopping with us today. Goodbye.");
			//optionSelected = true;
			break;
		default:
			System.out.println("Input not found. Please try again.");
			break;
			
		}
	}

	public static void main(String[] args) {
		
		//String url = "";
		//String username = "";
		//String password = "";
	
		
		try {
		//Connection c = DriverManager.getConnection(url,username,password);	
		//System.out.println(c.getMetaData().getDriverName());
			ConnectionsUtil.getConnectionFromFile();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		sc = new Scanner(System.in);
		//Customer newUser;
		//Employee newUser;
		
		System.out.println("Welcome to Revature Flower Shop!");
		System.out.println("Please choose an option: \n 1: Register a new account\n 2: Login \n 3: Exit the program");
		
		String choice = sc.nextLine();
		
		
		/*boolean optionSelected = false;
		 * while(optionSelected == false){
		 * ApplicationDriver.menuScreen(choice);
		 * }
		 */
		
		/*switch(choice) {
		case "1":
			/*newUser = new Customer();
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
			System.out.println(newUser);
			
			
			
			newUser = new Employee();
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
			System.out.println(newUser);
			break;
		case "2":
			System.out.println("The Login Screen");
			break;
		case "3":
			System.out.println("Thank you for shopping with us today. Goodbye.");
			break;
		default:
			System.out.println("Input not found. Please try again.");
			break;
			
		}*/
		
		sc.close();
		
	}

}
