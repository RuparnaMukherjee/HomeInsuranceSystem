package com.cg.hims.service;

import com.cg.hims.entities.UserTable;

public interface IUserService {
	public UserTable addNewUser(UserTable user);

	public UserTable signIn(UserTable user);

	public UserTable signOut(UserTable user);
}
