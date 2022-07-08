package com.revature.dtos;

import java.util.Objects;

import com.revature.models.User;
import com.revature.models.User.Role;


public class UserDTO {
	
	private int id;
	private String user_name;
	private User.Role role;
	
	public UserDTO(User u) {
		id = u.getId();
		user_name = u.getUser_name();
		role = u.getRole();
	}

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

	public User.Role getRole() {
		return role;
	}

	public void setRole(User.Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, role, user_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return id == other.id && role == other.role && Objects.equals(user_name, other.user_name);
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", user_name=" + user_name + ", role=" + role + "]";
	}

	
}
