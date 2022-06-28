package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.daos.StorePostgres;
import com.revature.models.Item;

public class StoreService {
	StorePostgres sps = new StorePostgres();
	
	
	//Create items
		public Item createItem(Item i) {
			Item item = sps.createItem(i);
			//System.out.println("Item: " + item + "was created");
			return item;
		}
			
		
		//Retrieve item
		public Item retrieveById(int id) {
			Item item = sps.retrieveById(id);
			//System.out.println("Item: " + item + "was retrieved");
			return item;
			
		}
		public Item retrieveByName(String name) {
			Item item = sps.retrieveByName(name);
			//System.out.println("Item: " + item + "was retrieved");
			return item;
		}
		
		//Retrieve list of all items
		public void retrieveInventory(){
			ArrayList<Item> inventory = sps.retrieveInventory();
			//System.out.println(inventory.size());
			for(int i = 0; i<inventory.size(); i++) {
				System.out.println("\tItem ID: " + inventory.get(i).getItemID());
				System.out.println("\tItem Name: " + inventory.get(i).getItemName());
				System.out.println("\tItem Description: " + inventory.get(i).getItemDescription());
				System.out.println("\tItem Cost: " + inventory.get(i).getItemCost() + "\n");
			}
		}
		
		//View items offer and reject or accept
		public boolean itemOffer(int itemID, Scanner sc) {
			return sps.itemOffer(itemID, sc);
		}
		
		
		//view all payments left
		public void viewPayments() {
			sps.viewPayments();
		}
		
		//delete items
		public boolean deleteByID(int id) {
			return sps.deleteByID(id);
		}
		public boolean deleteAll() {
			return sps.deleteAll();
		}
		
		//update item information
		public boolean updateItem(int id, Scanner sc) {
			return sps.updateItem(id,sc);
		}
	
}
