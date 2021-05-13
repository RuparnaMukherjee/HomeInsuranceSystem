package com.cg.hims.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.UserTable;
import com.cg.hims.exceptions.UserNotFoundException;
import com.cg.hims.service.IUserServiceImpl;

@RestController
@RequestMapping("/Authenticate")
public class UserManagementController {
	
	@Autowired
	IUserServiceImpl userImpl;
	
	//check for usercontrol
	@GetMapping("/Check")
	public String check() {
		return "ok";
	}
	
	//Adding new user to Database
	@PostMapping("/addUser")
	public UserTable addUser(@RequestBody UserTable user) {
		return userImpl.addNewUser(user);
	}
	
	//User-Login
	@PostMapping("/Login")
	public String login(@RequestParam String userName,@RequestParam String password) throws UserNotFoundException {
		System.out.println(userName+","+password);
		return userImpl.signIn(userName, password);
	}
	
	//User-Logout
	@GetMapping("/Logout")
	public String logout(@RequestBody UserTable user) {
		return userImpl.signOut(user);
	}
}
