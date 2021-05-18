package com.cg.hims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.hims.entities.UserTable;

public interface IUserRepository extends JpaRepository<UserTable, String> {

}
