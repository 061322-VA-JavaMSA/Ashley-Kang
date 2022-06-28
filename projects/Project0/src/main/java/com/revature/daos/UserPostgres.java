package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Item;
import com.revature.util.ConnectionsUtil;

public class UserPostgres implements UserDAO{	
	
	@Override
	public void createCustomer(Customer cc) {
		String sql = "insert into customers (username,user_pass,email,cus_name,card_num) values (?,?,?,?,?) returning id;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, cc.getUserName());
			ps.setString(2, cc.getUserPassword());
			ps.setString(3, cc.getUserEmail());
			ps.setString(4, cc.getName());
			ps.setString(5, cc.getCard());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cc.setUserID(rs.getInt("id"));
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void createEmployee(Employee e) {
		String sql = "insert into employees (username,user_pass,email,emp_name, auth_num) values (?,?,?,?,?) returning id;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getUserName());
			ps.setString(2, e.getUserPassword());
			ps.setString(3, e.getUserEmail());
			ps.setString(4, e.getName());
			ps.setString(5, e.getKey());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				e.setUserID(rs.getInt("id"));
			}
		} catch (SQLException | IOException except) {
			// TODO Auto-generated catch block
			except.printStackTrace();
		}
	}

	
	
	@Override
	public boolean deleteByID(int id, boolean isCust) {
		if(isCust) {
			String sql = "delete from customers where id = ?;";
			int rowsChanged = -1;
			try(Connection c = ConnectionsUtil.getConnectionFromFile()){
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setInt(1, id);
				rowsChanged = ps.executeUpdate();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(rowsChanged > 0) {
			System.out.println("Deletion Successful");
		}
			return true;
		}else if(isCust == false) {
			String sql = "delete from employees where id = ?;";
			int rowsChanged = -1;
			try(Connection c = ConnectionsUtil.getConnectionFromFile()){
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setInt(1, id);
				rowsChanged = ps.executeUpdate();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(rowsChanged > 0) {
			System.out.println("Deletion Successful");
		}
			return true;
		}
		return false;
	}


	@Override
	public boolean deleteAll(boolean isCust) {
		if(isCust) {
			String sql = "delete * from customers;";
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
		}
			return true;
		}else if(isCust == false) {
			String sql = "delete * from employees;";
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
		}
			return true;
		}
		return false;
	}

	@Override
	public void makeOffer(float amount, int userID, int itemID) {
		String sql = "insert into bids (cus_id,item_id,offered) values (?,?,?);";
		int rowsChanged = -1;
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setInt(2, itemID);
			ps.setFloat(3,amount);
			rowsChanged = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	if(rowsChanged > 0) {
		System.out.println("Offer has been placed.");
	}
		
	}

	@Override
	public ArrayList<Item> ownedItems(int userID) {
		Item item;
		ArrayList<Item> items = new ArrayList<Item>();
		String sql = "select * from inventory where owners_id = ?;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);	
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				item = new Item();
				item.setItemID(rs.getInt("item_id"));
				item.setItemDescription(rs.getString("item_desc"));
				item.setItemName(rs.getString("item_name"));
			    item.setOwned(true);
				item.setOwnerID(rs.getInt("owners_id"));
				
				items.add(item);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public void paymentsLeft(int userID) {
		String sql = "select * from bids join inventory on cus_id = owners_id;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(Integer.parseInt(rs.getString("owners_id")) == userID){
					System.out.println("Item Name: " + rs.getString("item_name"));
					System.out.println("Item Cost: " + rs.getString("item_cost"));
					System.out.println("Amount owed: " + rs.getFloat("payments"));
				}
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Customer retrieveCByID(int id) {
		String sql = "select * from customers where id = ?;";
		Customer user = null;
		
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new Customer();
				user.setUserID(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("user_pass"));
				user.setCard(rs.getString("card_num"));
				user.setName(rs.getString("cus_name"));
				user.setUserEmail(rs.getString("email"));
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Employee retrieveEByID(int id) {
		String sql = "select * from employees where id = ?;";
		Employee user = null;
		
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new Employee();
				user.setUserID(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("user_pass"));
				user.setKey(rs.getString("auth_num"));
				user.setName(rs.getString("emp_name"));
				user.setUserEmail(rs.getString("email"));
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Customer retrieveCByUsername(String username) {
		String sql = "select * from customers where username = ?;";
		Customer user = null;
		
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new Customer();
				user.setUserID(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("user_pass"));
				user.setCard(rs.getString("card_num"));
				user.setName(rs.getString("cus_name"));
				user.setUserEmail(rs.getString("email"));
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Employee retrieveEByUsername(String username) {
		String sql = "select * from employees where username = ?;";
		Employee user = null;
		
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new Employee();
				user.setUserID(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("user_pass"));
				user.setKey(rs.getString("auth_num"));
				user.setName(rs.getString("emp_name"));
				user.setUserEmail(rs.getString("email"));
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<Customer> retrieveCustomers() {
		String sql = "select * from customers;";
		Customer user = null;
		List<Customer> cusList = new ArrayList<Customer>();
		
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new Customer();
				user.setUserID(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("user_pass"));
				user.setCard(rs.getString("card_num"));
				user.setName(rs.getString("cus_name"));
				user.setUserEmail(rs.getString("email"));
				cusList.add(user);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cusList;
	}

	@Override
	public List<Employee> retrieveEmployees() {
		String sql = "select * from employees;";
		Employee user = null;
		List<Employee> empList = new ArrayList<Employee>();
		
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new Employee();
				user.setUserID(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("user_pass"));
				user.setKey(rs.getString("auth_num"));
				user.setName(rs.getString("emp_name"));
				user.setUserEmail(rs.getString("email"));
				empList.add(user);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public boolean updateUser(int id, boolean isCust, Scanner sc) {
		//Scanner sc = new Scanner(System.in);
		if(isCust) {
			String sql = "";
			boolean isQuit = false;
			while(isQuit == false) {
			System.out.println("What would you like to update?\n1: Name\n2: Username"
					+ "\n3: Password\n4: Email\n5: Card Number\n6: Quit");
			switch(sc.nextLine()) {
			case "1":
				sql = "update customers set cus_name = ? where id = ?;";
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
				System.out.println("Name changed successfully.");
			}
				break;
			case "2":
				System.out.println("Enter new username.");
				sql = "update customers set username = ? where id = ?;";
				
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
				System.out.println("Username changed successfully.");
			}
				break;
			case "3":
				System.out.println("Enter new password.");
				sql = "update customers set user_pass = ? where id = ?;";
				
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
				System.out.println("Password changed successfully.");
			}
				break;
			case "4":
				System.out.println("Enter new email.");
				sql = "update customers set email = ? where id = ?;";
			
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
				System.out.println("Email changed successfully.");
			}
				break;
			case "5":
				System.out.println("Enter new card number.");
				sql = "update customers set card_num = ? where id = ?;";
				
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
				System.out.println("Card number changed successfully.");
			}
				break;
			case "6":
				isQuit = true;
				break;
			default:
				System.out.println("Input not found. Try again.");
				break;
				}
			}
			//2
			//sc.close();
			return true;
		}else if(isCust = false){
			String sql="";
			boolean isQuit = false;
			while(isQuit == false) {
			System.out.println("What would you like to update?\n1: Name\n2: Username"
					+ "\n3: Password\n4: Email\n5: Authorization Number\n6: Quit");
			switch(sc.nextLine()) {
			case "1":
				sql = "update employees set emp_name = ? where id = ?;";
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
				System.out.println("Name changed successfully.");
			}
				break;
			case "2":
				System.out.println("Enter new username.");
				sql = "update employees set username = ? where id = ?;";
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
				System.out.println("Username changed successfully.");
			}
				break;
			case "3":
				System.out.println("Enter new password.");
				sql = "update employees set user_pass = ? where id = ?;";
				
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
				System.out.println("Password changed successfully.");
			}
				break;
			case "4":
				System.out.println("Enter new email.");
				sql = "update employees set email = ? where id = ?;";
				
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
				System.out.println("Email changed successfully.");
			}
				break;
			case "5":
				System.out.println("Enter new authorization key.");
				sql = "update employees set auth_num = ? where id = ?;";
			
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
				System.out.println("Authorization key changed successfully.");
			}
				break;
			case "6":
				isQuit = true;
				//sc.close();
				break;
			default:
				System.out.println("Input not found. Try again");
				break;
				}
			}
			//sc.close();
			return true;
		}
		//sc.close();
		return false;
	}

}
