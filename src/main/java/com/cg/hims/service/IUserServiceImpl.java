package com.cg.hims.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.UserTable;
import com.cg.hims.repository.IUserRepository;

@Service
@Transactional
public class IUserServiceImpl implements IUserService {
	
	@Autowired
	IUserRepository userDao;

	@Override
	public UserTable addNewUser(UserTable user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		return user;
	}

	@Override
	public UserTable signIn(UserTable user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTable signOut(UserTable user) {
		// TODO Auto-generated method stub
		return null;
	}

}
