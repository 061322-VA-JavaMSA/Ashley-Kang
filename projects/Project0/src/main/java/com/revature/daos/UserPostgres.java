package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Item;
import com.revature.models.User;
import com.revature.util.ConnectionsUtil;

public class UserPostgres implements UserDAO{
	
	@Override
	public User createUser(User u) {
		String sql = "insert into customers (username,user_pass,email,cus_name) values (?,?,?,?) returning id;";
		try(Connection c = ConnectionsUtil.getConnectionFromFile()){
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getUserPassword());
			ps.setString(3, u.getUserEmail());
			ps.setString(4, u.getName());
			//ps.setString(5, u.getCard());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				u.setUserID(rs.getInt("id"));
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	
	public Customer createUser(Customer cc) {
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
		return cc;
	}
	
	public Employee createUser(Employee e) {
		String sql = "insert into employees (username,user_pass,email,emp_name) values (?,?,?,?) returning id;";
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
		return e;
	}

	@Override
	public User retrieveUserbyId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User retrieveByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> retrieveUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteByID(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeOffer(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> ownedItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void paymentsLeft() {
		// TODO Auto-generated method stub
		
	}

}
