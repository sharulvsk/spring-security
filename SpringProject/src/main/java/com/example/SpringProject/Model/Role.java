package com.example.SpringProject.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Role")
public class Role {
	@Id
	private int rid;
	private String role;
	
	public Role() {
	}
	public Role(int rid, String role) {
		this.rid = rid;
		this.role = role;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
