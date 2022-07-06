package com.revature.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
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
	
	@Id
	@Column(name = "ticket_id")
	private int id;
	
	@Column(name="ticket_desc")
	private String ticket_desc;
	
	@Column(name = "ticket_amount")
	private float ticket_amount;
	
	@Column (name = "employee_id")
	private String employee_name;
	
	@Column (name = "manager_id")
	private String manager_name;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "ticket_ty")
	private Type type;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "ticket_stat")
	private Status status;
	
	
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
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return Objects.hash(employee_name, id, manager_name, status, ticket_amount, ticket_desc, type);
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
				&& Objects.equals(manager_name, other.manager_name) && status == other.status
				&& Float.floatToIntBits(ticket_amount) == Float.floatToIntBits(other.ticket_amount)
				&& Objects.equals(ticket_desc, other.ticket_desc) && type == other.type;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", ticket_desc=" + ticket_desc + ", ticket_amount=" + ticket_amount
				+ ", employee_name=" + employee_name + ", manager_name=" + manager_name + ", type=" + type + ", status="
				+ status + "]";
	}
	
	
	
}
