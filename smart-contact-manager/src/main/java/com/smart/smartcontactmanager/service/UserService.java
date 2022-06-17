package com.smart.smartcontactmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.smartcontactmanager.entity.User;
import com.smart.smartcontactmanager.repository.UserRepository;

/**
 * @author KKVIVEK
 *
 */
@Service
public class UserService {
	
	@Autowired
	public UserRepository repository;
	
	
	public User addNewUser(User user) {
		return repository.save(user);
	}

}
