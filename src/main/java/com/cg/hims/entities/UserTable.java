package com.cg.hims.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserTable {
	
	
	@Id
	private String userName;
	private String password;
	private String role;
	public UserTable()
	{}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}