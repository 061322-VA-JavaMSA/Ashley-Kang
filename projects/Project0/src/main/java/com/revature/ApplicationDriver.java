package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Employee;


public class ApplicationDriver {
	static Scanner sc;

	public static void main(String[] args) {
		
		String url = "jdbc:postgresql://database-1.c0n1i0afb1oi.us-east-1.rds.amazonaws.com/postgres";
		String username = "";
		String password = "";
		
		/*try {
		Connection c = DriverManager.getConnection(url,username,password);	
		System.out.println(c.getMetaData().getDriverName());
		}catch (Exception e) {
			e.printStackTrace();
		}*/
		
		sc = new Scanner(System.in);
		//Customer newUser;
		Employee newUser;
		
		System.out.println("Welcome to Revature Flower Shop!");
		System.out.println("Please choose an option: \n 1: Register a new account");
		
		String choice = sc.nextLine();
		
		switch(choice) {
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
			System.out.println(newUser);*/
			
			
			
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
			
		}
		
		
		sc.close();
		
		
	}

}
