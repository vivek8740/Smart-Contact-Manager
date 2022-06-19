package com.smart.smartcontactmanager.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.smartcontactmanager.entity.User;
import com.smart.smartcontactmanager.helper.Message;
import com.smart.smartcontactmanager.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService service;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart ContactManager");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("title", "Login - Smart ContactManager");
		return "login";
	}

	@PostMapping(value = "/do_register")
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			@RequestParam(value = "aggrement", defaultValue = "false") boolean aggrement, Model model,HttpSession session) {

		try {
			if (!aggrement) {
				System.out.println("You have not agreed the terms and conditions");
				throw new Exception("You have not agreed the terms and conditions");
			}
			
			if(bindingResult.hasErrors()) {
				System.out.println(bindingResult.toString());
				model.addAttribute("user",user);
				return "signup";
			}

			user.setRole("USER");
			user.setEnabled(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User result = service.addNewUser(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully Registered", "alert-success"));
			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong!!!"+e.getMessage(),"alert-danger"));
			return "signup";
		}

	}

}
