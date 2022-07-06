package com.revature.models;

import java.util.Objects;

public class Ticket {
	enum Type{
		LODGING,
		TRAVEL,
		FOOD,
		OTHER
	}
	
	enum Status{
		APPROVED,
		PENDING,
		DENIED
	}
	
	private int id;
	private String ticket_desc;
	private float ticket_amount;
	private String employee_name;
	private String manager_name;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTicket_desc() {
		return ticket_desc;
	}
	public void setTicket_desc(String ticket_desc) {
		this.ticket_desc = ticket_desc;
	}
	public float getTicket_amount() {
		return ticket_amount;
	}
	public void setTicket_amount(float ticket_amount) {
		this.ticket_amount = ticket_amount;
	}
	
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(User employee) {
		this.employee_name = employee.getUser_name();
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(User manager) {
		this.manager_name = manager.getUser_name();
	}
	@Override
	public int hashCode() {
		return Objects.hash(employee_name, id, manager_name, ticket_amount, ticket_desc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(employee_name, other.employee_name) && id == other.id
				&& Objects.equals(manager_name, other.manager_name)
				&& Float.floatToIntBits(ticket_amount) == Float.floatToIntBits(other.ticket_amount)
				&& Objects.equals(ticket_desc, other.ticket_desc);
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", ticket_desc=" + ticket_desc + ", ticket_amount=" + ticket_amount
				+ ", employee_name=" + employee_name + ", manager_name=" + manager_name + "]";
	}
	
	
	
}
