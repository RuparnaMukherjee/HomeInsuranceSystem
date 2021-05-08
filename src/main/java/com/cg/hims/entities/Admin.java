package com.cg.hims.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
	private int adminId;
	private String adminName;
	private String contact;
	public Admin() {
		
	}
	public Admin(int adminId,String adminName,String contact) {
		this.adminId=adminId;
		this.adminName=adminName;
		this.contact=contact;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
