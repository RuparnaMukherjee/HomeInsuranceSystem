package com.cg.hims.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserTable {
	
	
	@Id
	@Pattern(regexp="[a-zA-Z0-9_.@]{5,10}")
	private String userName;
	@Column(nullable=false)
	@Length(min=8,max=15)
	private String password;
	@Column(nullable=false)
	@Pattern(regexp="(User)||(Admin)||(Agent)")
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