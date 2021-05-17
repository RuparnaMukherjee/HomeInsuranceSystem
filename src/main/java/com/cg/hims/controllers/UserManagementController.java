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

import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/Authenticate")
public class UserManagementController {
	@Autowired
	HttpSession session;
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
	@PostMapping("/Login")
	public String login(@RequestParam String userName,@RequestParam String password) throws UserNotFoundException {
		System.out.println(userName+","+password);
		return userImpl.signIn(userName, password);
	}
	@GetMapping("/Logout")
	public ResponseEntity<String> logout(@RequestBody UserTable user) {
		session.invalidate();
		return new ResponseEntity<String>("logged out",HttpStatus.OK);
	}
}
