package com.cg.hims.service;

import java.util.Optional;
import java.util.stream.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.UserTable;
import com.cg.hims.exceptions.UserNotFoundException;
import com.cg.hims.repository.IUserRepository;


/*Author:Jeeta Makhal
 * Version: 1.0
 * Date:15/05/2021
 * Description: This is User Service Implementation
 */

@Service 
@Transactional 
public class IUserServiceImpl implements IUserService {
	
	
	@Autowired
	IUserRepository userDao;

	@Override//
	public UserTable addNewUser(UserTable user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		return user;
	}
	
	/* Method: User service implementation implements User service
	 * Description: It helps to implement the business logic..
	 * @Service: It is a stereotype for the service layer and are used to write business logic in a different layer, separated from @RestController class file..
	 * @Transactional: It itself defines the scope of a single database.
	 * @Autowired: It enables to inject object dependency implicitly.
	 * @Override: It indicates that the child class method is over-writing its base class method.
    
    * Created By-Jeeta Makhal
    * Created Date - 16-05-2021 
    */
	

	@Override
	public String signIn(String userName,String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(!userDao.existsById(userName))
			throw new UserNotFoundException("User doesnt exist");
		UserTable user1=userDao.findById(userName).orElse(null);
		if(!user1.getPassword().equals(password))
			return "Password didnt match";
		return "Logged In SuccessFully";
	}

	/* Method: Business logic 
	 * Description: Business logic when user tries to login.
	 * @Override: It indicates that the child class method is over-writing its base class method.
    
    * Created By-Jeeta Makhal
    * Created Date - 16-05-2021 
    */
	

	
}