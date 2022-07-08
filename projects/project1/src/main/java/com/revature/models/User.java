package com.revature.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	public enum Role{
		EMPLOYEE,
		MANAGER
	}
	
	
	@Id
	@Column(name = "user_id")
	private int id;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "user_pass")
	private String user_pass;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private Role role;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, role, user_name, user_pass, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id && role == other.role && Objects.equals(user_name, other.user_name)
				&& Objects.equals(user_pass, other.user_pass) && Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", username=" + username + ", user_pass=" + user_pass
				+ ", role=" + role + "]";
	}
	
	
	

}
