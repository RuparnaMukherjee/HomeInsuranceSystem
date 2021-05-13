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

@Service
@Transactional
public class IUserServiceImpl implements IUserService {
	
	@Autowired
	IUserRepository userDao;
	@PersistenceContext
    private EntityManager entityManager;

	//add user
	@Override
	public UserTable addNewUser(UserTable user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		return user;
	}

	//User sign-in
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

	//User sign-out
	@Override
	public String signOut(UserTable user) {
		// TODO Auto-generated method stub
		entityManager.detach(user);
		return "Logged Out SuccessFully";
	}

}
