package com.smart.smartcontactmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.smartcontactmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
