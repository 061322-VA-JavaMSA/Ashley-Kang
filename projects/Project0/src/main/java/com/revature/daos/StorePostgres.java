package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Item;
import com.revature.util.ConnectionsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StorePostgres implements StoreDAO{
	private static Logger log = LogManager.getLogger(StorePostgres.class);
	@Override
	public Item createItem(Item i) {
		String sql = "insert into inventory (item_name,item_desc,item_cost) values (?,?,?) returning item_id;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, i.getItemName());
			ps.setString(2, i.getItemDescription());
			ps.setFloat(3, i.getItemCost());
			
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				i.setItemID(rs.getInt("item_id"));
			}
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
		log.info("Item Created: " + i);
		return i;
	}

	@Override
	public Item retrieveById(int id) {
		Item i = new Item();
		String sql = "select * from inventory where item_id = ?;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
		
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				i.setItemID(rs.getInt("item_id"));
				i.setItemCost(rs.getFloat("item_cost"));
				i.setItemDescription(rs.getString("item_desc"));
				i.setItemName(rs.getString("item_name"));
				if(rs.getInt("owners_id")!=0) {
					i.setOwned(true);
					i.setOwnerID(rs.getInt("owners_id"));
				}
			}
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
		log.info("Item Retrieved: " + i);
		return i;
	}

	@Override
	public ArrayList<Item> retrieveInventory() {
		Item i = new Item();
		ArrayList<Item> items = new ArrayList<Item>();
		String sql = "select * from inventory;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
					
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				i.setItemID(rs.getInt("item_id"));
				i.setItemCost(rs.getFloat("item_cost"));
				i.setItemDescription(rs.getString("item_desc"));
				i.setItemName(rs.getString("item_name"));
				items.add(i);
				log.info("Item retrieved: " + i);
			}
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
		return items;
	}

	@Override
	public boolean deleteByID(int id) {
		deleteBidsID(id);
		String sql = "delete from inventory where item_id = ?;";
		int rowsChanged = -1;
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,id);
			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
	
	if(rowsChanged > 0) {
		System.out.println("Deletion Successful");
		return true;
	}
		return false;
	}

	@Override
	public boolean updateItem(int id, Scanner sc) {
		//Scanner sc = new Scanner(System.in);
		String sql = "";
		boolean isQuit = false;
		while(isQuit == false) {
		System.out.println("What would you like to update?\n1: Name\n2: Cost"
				+ "\n3: Description\n4: Quit");
		switch(sc.nextLine()) {
		case "1":
			sql = "update inventory set item_name = ? where item_id = ?;";
			System.out.println("Enter new name.");
			int rowsChanged = -1;
			try(Connection c = ConnectionsUtil.getConnectionFromFile()){
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, sc.nextLine());
				ps.setInt(2, id);
				rowsChanged = ps.executeUpdate();
			} catch (SQLException | IOException e) {
				log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
			}
		
		if(rowsChanged > 0) {
			System.out.println("Name changed successfully.");
		}
			break;
		case "2":
			System.out.println("Enter new cost. \nPlease only enter whole numerical or decimal values.");
			sql = "update inventory set item_cost = ? where item_id = ?;";
			
			rowsChanged = -1;
			try(Connection c = ConnectionsUtil.getConnectionFromFile()){
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setFloat(1, sc.nextFloat());
				//sc.nextLine();
				ps.setInt(2, id);
				rowsChanged = ps.executeUpdate();
			} catch (SQLException | IOException e) {
				log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
			}
		if(rowsChanged > 0) {
			System.out.println("Cost changed successfully.");
		}
			sc.nextLine();
			break;
		case "3":
			System.out.println("Enter new description");
			sql = "update inventory set item_desc = ? where item_id = ?;";
			
			rowsChanged = -1;
			try(Connection c = ConnectionsUtil.getConnectionFromFile()){
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, sc.nextLine());
				ps.setInt(2, id);
				rowsChanged = ps.executeUpdate();
			} catch (SQLException | IOException e) {
				log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
			}
		
		if(rowsChanged > 0) {
			System.out.println("Description changed successfully.");
		}
			break;
		case "4":
			isQuit = true;
			break;
		default:
			System.out.println("Input not found. Try again");
			break;
			}
		}
		//sc.close();
		return true;
	}

	@Override
	public boolean deleteAll() {
		deleteBidsAll();
		String sql = "delete from inventory;";
		int rowsChanged = -1;
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
	
	if(rowsChanged > 0) {
		System.out.println("Deletion Successful");
		return true;
	}
		return false;
	}
	
	public boolean deleteBidsAll() {
		String sql = "delete from bids;";
		int rowsChanged = -1;
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
	
	if(rowsChanged > 0) {
		log.info("Deletion successful ");
		return true;
	}
		return false;
	}

	
	@Override
	public void viewPayments() {
		System.out.println("These are all the payments still active");
		String sql = "select * from bids join customers on bcus_id = id;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					if(rs.getFloat("payments") != 0) {
					System.out.println("Customer Name : " + rs.getString("cus_name"));
					System.out.println("Amount Owed: " + rs.getFloat("payments"));
					}
			}
			
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
		
	}

	@Override
	public Item retrieveByName(String name) {
		String sql = "select * from inventory where item_name = ?;";
		Item i = new Item();
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,name);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				i.setItemCost(rs.getFloat("item_cost"));
				i.setItemDescription(rs.getString("item_desc"));
				i.setItemID(rs.getInt("item_id"));
				i.setItemName(rs.getString("item_name"));
			}
			
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
	
		return i;
	}
	
	public boolean deleteBidsID(int id) {
		String sql = "delete from bids where bitem_id = ?;";
		int rowsChanged = -1;
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,id);
			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
	
	if(rowsChanged > 0) {
		log.info("Deletion successful ");
		return true;
	}
		return false;
	}

	@Override
	public boolean itemOffer(int itemID, Scanner sc) {
		String sql = "select * from bids join inventory on bitem_id = item_id;";
		boolean isAccepted = false;
		
		int cusId = 0;
		float offered = 0;
		float payments = 0;
		
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(isAccepted == false) {
					System.out.println("Item Name: " + rs.getString("item_name"));
					System.out.println("Item Bid: " + rs.getFloat("offered"));
					System.out.println("Do you accept this bid? [Y] or [N]");
					if(sc.nextLine().equals("Y")) {
						cusId = rs.getInt("bcus_id");
						offered = rs.getFloat("offered");
						payments = offered;
						isAccepted = true;
						deleteBidsID(itemID);
						insertBid(cusId,itemID, offered, payments);
						updateOwner(cusId,itemID);
					}else {
						System.out.println("Offer Rejected");
						}
					}
			}
			
			return true;
			
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
		return false;
	}
	static void updateOwner(int cus, int item) {
		String sql = "update inventory set owners_id = ? where item_id = ?;";
		
		int rowsChanged = -1;
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cus);
			ps.setInt(2, item);
			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
	
	if(rowsChanged > 0) {
		//System.out.println("Name changed successfully.");
	}
	}
	
	
	static void insertBid(int cusId, int itemId, float offered, float payments) {
		String sql = "insert into bids (bcus_id,bitem_id,offered,payments) values (?,?,?,?);";
		int rowsChanged = -1;
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cusId);
			ps.setInt(2, itemId);
			ps.setFloat(3, offered);
			ps.setFloat(4, payments);
			rowsChanged = ps.executeUpdate();
			
		}catch(SQLException | IOException e) {
			log.error("Connection error. Exception thrown: " + e.fillInStackTrace());
		}
		
		if(rowsChanged > 0) {
			log.info("Insert successful");
		}

	}
}
