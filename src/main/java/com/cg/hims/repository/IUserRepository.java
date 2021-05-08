package com.cg.hims.repository;

import com.cg.hims.entities.UserTable;

public interface IUserRepository {
	public UserTable addNewUser(UserTable user);

	public UserTable signIn(UserTable user);

	public UserTable signOut(UserTable user);
}
