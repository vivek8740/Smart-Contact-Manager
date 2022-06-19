package com.smart.smartcontactmanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.smartcontactmanager.entity.User;
import com.smart.smartcontactmanager.repository.UserRepository;

public class USerDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.getUserByUserName(username);
		if (user == null)
			throw new UsernameNotFoundException("User could not be found");

		CustomerUserDetails customerUserDetails = new CustomerUserDetails(user);
		return customerUserDetails;
	}

}
