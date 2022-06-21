package com.smart.smartcontactmanager.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.smartcontactmanager.entity.User;
import com.smart.smartcontactmanager.repository.UserRepository;

@Controller()
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping("/dashboard")
	public String dashboard(Model model, Principal principal ) {
		String username = principal.getName();
		System.out.println("USERNAME :"+username);

		try {
			User u = userRepository.getUserByUserName(username);
			model.addAttribute(u);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "user/dashboard";
	}

}
