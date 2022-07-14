package com.revature.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets", schema = "public")
public class Ticket {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private int id;
	
	@Column(name="ticket_desc")
	private String ticket_desc;
	
	@Column(name = "ticket_amount")
	private float ticket_amount;
	
	@Column(name = "employee_id")
	private int employee_id;
	
	
	@Column(name = "manager_id")
	private int manager_id;
	
	@Column (name = "ticket_ty")
	private String type;
	

	@Column (name = "ticket_stat")
	private String status;
	
	
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
	
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int id) {
		this.employee_id = id;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int id) {
		this.manager_id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return Objects.hash(employee_id, id, manager_id, status, ticket_amount, ticket_desc, type);
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
		return employee_id == other.employee_id && id == other.id && manager_id == other.manager_id
				&& Objects.equals(status, other.status)
				&& Float.floatToIntBits(ticket_amount) == Float.floatToIntBits(other.ticket_amount)
				&& Objects.equals(ticket_desc, other.ticket_desc) && Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", ticket_desc=" + ticket_desc + ", ticket_amount=" + ticket_amount
				+ ", employee_id=" + employee_id + ", manager_id=" + manager_id + ", type=" + type + ", status="
				+ status + "]";
	}
	
}
