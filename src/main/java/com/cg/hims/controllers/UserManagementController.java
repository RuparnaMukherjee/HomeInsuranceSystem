package com.cg.hims.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.UserTable;
import com.cg.hims.service.IUserServiceImpl;

@RestController
@RequestMapping("/Authenticate")
public class UserManagementController {
	
	@Autowired
	IUserServiceImpl userImpl;
	
	@GetMapping("/Check")
	public String check() {
		return "ok";
	}
	
	@PostMapping("/addUser")
	public UserTable addUser(@RequestBody UserTable user) {
		return userImpl.addNewUser(user);
	}
}
