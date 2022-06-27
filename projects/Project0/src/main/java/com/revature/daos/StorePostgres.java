package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Item;
import com.revature.util.ConnectionsUtil;

public class StorePostgres implements StoreDAO{

	@Override
	public Item createItem(Item i) {
		String sql = "insert into inventory (item_name,item_desc,item_cost) values (?,?,?) returning id;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, i.getItemName());
			ps.setString(2, i.getItemDescription());
			
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				i.setItemID(rs.getInt("id"));
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				i.setItemID(rs.getInt("id"));
				i.setItemCost(rs.getFloat("item_cost"));
				i.setItemDescription(rs.getString("item_desc"));
				i.setItemName(rs.getString("item_name"));
				if(rs.getInt("owners_id")!=0) {
					i.setOwned(true);
					i.setOwnerID(rs.getInt("owners_id"));
				}
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<Item> retrieveInventory() {
		Item i = new Item();
		List<Item> items = new ArrayList<Item>();
		String sql = "select * from inventory;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
					
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				i.setItemID(rs.getInt("id"));
				i.setItemCost(rs.getFloat("item_cost"));
				i.setItemDescription(rs.getString("item_desc"));
				i.setItemName(rs.getString("item_name"));
				if(rs.getInt("owners_id")!=0) {
					i.setOwned(true);
					i.setOwnerID(rs.getInt("owners_id"));
				}
				items.add(i);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public boolean deleteByID(int id) {
		String sql = "delete from inventory where item_id = ?;";
		int rowsChanged = -1;
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,id);
			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	if(rowsChanged > 0) {
		System.out.println("Deletion Successful");
		return true;
	}
		return false;
	}

	@Override
	public boolean updateItem(int id) {
		Scanner sc = new Scanner(System.in);
		String sql="";
		boolean isQuit = false;
		while(isQuit == false) {
		System.out.println("What would you like to update?\n1: Name\n2: Cost"
				+ "\n3: Description\n4: Quit");
		switch(sc.nextLine()) {
		case "1":
			sql = "update inventory set item_name = ? where id = ?;";
			System.out.println("Enter new name.");
			int rowsChanged = -1;
			try(Connection c = ConnectionsUtil.getConnectionFromFile()){
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, sc.nextLine());
				ps.setInt(2, id);
				rowsChanged = ps.executeUpdate();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(rowsChanged > 0) {
			System.out.println("Update Successful");
		}
			break;
		case "2":
			System.out.println("Enter new cost. \nPlease only enter whole numerical or decimal values.");
			sql = "update inventory set item_cost = ? where id = ?;";
			
			rowsChanged = -1;
			try(Connection c = ConnectionsUtil.getConnectionFromFile()){
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setFloat(1, sc.nextFloat());
				ps.setInt(2, id);
				rowsChanged = ps.executeUpdate();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(rowsChanged > 0) {
			System.out.println("Update Successful");
		}
			break;
		case "3":
			System.out.println("Enter new description.");
			sql = "update inventory set item_desc = ? where id = ?;";
			
			rowsChanged = -1;
			try(Connection c = ConnectionsUtil.getConnectionFromFile()){
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, sc.nextLine());
				ps.setInt(2, id);
				rowsChanged = ps.executeUpdate();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(rowsChanged > 0) {
			System.out.println("Update Successful");
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
		sc.close();
		return true;
	}

	@Override
	public boolean deleteAll() {
		String sql = "delete * from inventory";
		int rowsChanged = -1;
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	if(rowsChanged > 0) {
		System.out.println("Deletion Successful");
		return true;
	}
		return false;
	}

	
	@Override
	public void viewPayments() {
		System.out.println("These are all the payments still active");
		String sql = "select * from bids join customers on cus_id = owners_id;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
					System.out.println("Item Name: " + rs.getString("item_name"));
					System.out.println("Item Cost: " + rs.getString("item_cost"));
					System.out.println("Amount Owed: " + rs.getFloat("payments"));
					System.out.println("Owners Name" + rs.getString("cus_name"));
				
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Item retrieveByName(String name) {
		String sql = "select from inventory where item_name = ?;";
		Item i = new Item();
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,name);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				i.setItemCost(rs.getFloat("Item_cost"));
				i.setItemDescription(rs.getString("Item_desc"));
				i.setItemID(rs.getInt("item_id"));
				i.setItemName(rs.getString("item_name"));
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return i;
	}

	@Override
	public boolean itemOffer(int itemID) {
		// TODO Auto-generated method stub
		/*
		 * While(offer not accepted)
		 * show current offer
		 * 	if rejected, delete
		 * 	else if accepted
		 * 		accept offer
		 * 		delete all other records
		 * 		return true
		 * else 
		 * 		continue while loop
		 * 
		 */
		String sql = "select * from bids where item_id=?";
		boolean isAccepted = false;
		Scanner sc = new Scanner(System.in);
		//information to hold the winning bid
		int cusId = 0;
		float offered = 0;
		float payments =0;
		//customerid
		//item id
		//offered
		//payments
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,itemID);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				while(isAccepted == false) {
					if(!rs.next()) {
						System.out.println("No offer accepted. Leaving Screen");
						isAccepted = true;
					}
					System.out.println("Item Bid:" + rs.getFloat("item_bid"));
					System.out.println("Do you accept this bid? [Y] or [N]");
					if(sc.nextLine().equals("Y")||sc.nextLine().equals("y")) {
						cusId = rs.getInt("cus_id");
						offered = rs.getFloat("offered");
						payments = offered;
						isAccepted = true;
						//accept offer
						//delete all other entries
					}else {
						System.out.println("Offer Rejected");
						//reject the offer
					}
				}
			}
			deleteByID(itemID);
			sql = "insert into bids (cus_id,item_id,offered,payments) values (?,?,?,?);";
			ps = c.prepareStatement(sql);
			ps.setInt(1, cusId);
			ps.setInt(1, itemID);
			ps.setFloat(3, offered);
			ps.setFloat(4, payments);
			rs = ps.executeQuery();
			sc.close();
			return true;
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
		return false;
	}

}
