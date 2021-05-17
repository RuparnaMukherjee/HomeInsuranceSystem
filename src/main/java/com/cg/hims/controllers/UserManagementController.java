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


/*Author:Jeeta Makhal
 * Version: 1.0
 * Date:15/05/2021
 * Description: This is User Management Controller
 */

@RestController
@RequestMapping("/Authenticate")
public class UserManagementController {
	@Autowired
	HttpSession session;
	@Autowired
	IUserServiceImpl userImpl;
	
	/* Method: Authenticate
	 * Description: It allows to authenticate the user.
	 * @RequestMapping: It is used to map HTTP requests to handler methods of MVC and REST controllers.
	 * @RestController: It is used to create RESTful web services using MVC.
	 * @Autowired: It enables to inject object dependency implicitly.
    * Created By-Jeeta Makhal
    * Created Date - 15-05-2021 
    */
	
	
	@GetMapping("/Check")
	public String check() {
		return "ok";
	}
	
	/* Method: Check Operation
	 * Description: It allows to check the controller.
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	
    * Created By-Jeeta Makhal
    * Created Date - 15-05-2021 
    */
	
	@PostMapping("/addUser")
	public UserTable addUser(@RequestBody UserTable user) {
		return userImpl.addNewUser(user);
	}
	
	/* Method: Add User
	 * Description: It allows to add new user.
	 * @RequestBody: It is used to bind HTTP request body with a domain object in method parameter or return type.
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	
    * Created By-Jeeta Makhal
    * Created Date - 15-05-2021 
    */
	
	
	@PostMapping("/Login")
	public String login(@RequestParam String userName,@RequestParam String password) throws UserNotFoundException {
		System.out.println(userName+","+password);
		return userImpl.signIn(userName, password);
	}

	
	 /* Method: Login
	 * Description: It allows user to login.
	 * @RequestParam: extract query, parameters, form parameters, and even files from the request.
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * UserNotFoundException:When user fails to sign in/ log in user.
      
     * Created By-Jeeta Makhal
     * Created Date - 15-05-2021
     */
	 
	
	
	@GetMapping("/Logout")
	public ResponseEntity<String> logout(@RequestBody UserTable user) {
		session.invalidate();
		return new ResponseEntity<String>("logged out",HttpStatus.OK);
	}
	
	/* Method: Logout
	 * Description: It allows user to logout.
	 * @RequestBody: It is used to bind HTTP request body with a domain object in method parameter or return type.
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	
    * Created By-Jeeta Makhal
    * Created Date - 15-05-2021 
    */
}