package com.revature.scanner;
import java.util.Scanner;

public class ScannerPractice {
	static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		
		//Welcome to the menu
		System.out.println("Welcome!");
		
		//used to check while loop condition
		String condInput = "0";
		
		//arbitary number used for the switch case
		int input = 9999999;
		
		
		//loops the menu until the user chooses to quit
		while(!condInput.equals("3")) {
		System.out.println("Please select an option from the menu:");
		System.out.println("1: Generate a random number between 1 and 100.");
		System.out.println("2: Reverse a word of your choice.");
		System.out.println("3: Exit the program.");
		
		//tests for integer input
		try {
			input = sc.nextInt();
		}catch (Exception e){
			System.out.println("Invalid input! Please enter a number.");
		}
		
		sc.nextLine();
		
		//menu options
		//Case 1: Random number generation
		//Case 2: reverse a user input string
		//Defualt: input not recognized
			switch(input){
			case 1:
				int rndNum = (int) (Math.random() * 100) + 1;
				System.out.println("Your random number is: "+ rndNum);
				break;
			case 2:
				System.out.println("Please enter a word or phrase to be reversed.");
				String wordInput = sc.nextLine();
				StringBuilder sb = new StringBuilder(wordInput);
				sb.reverse();
				System.out.println("Your reversed word is: " + sb.toString());
				break;
			case 3:
				condInput = "3";
				break;
			case 9999999:
				break;
			default:
				System.out.println("Input not recognized");
			}
		}
		
		//Closing message
		System.out.println("Thank you and goodbye!");
	}
	

	public static void main(String[] args) {
		//Starts the menu
		menu();
		
		//closes the scanner
		sc.close();

	}

}
