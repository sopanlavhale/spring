package com.sopan.api.controller;

import java.lang.reflect.Constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sopan.api.model.User;
import com.sopan.api.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User Construct(){
		return new User();
	}
	
	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
	@RequestMapping("/users/{userId}")
	public String userDetails(Model model, @PathVariable int userId) {
		model.addAttribute("user", userService.findOneWithBlogs(userId));
		return "userDetails";
	}
	
	@RequestMapping("/register")
	public String register(Model model) {

		return "userRegister";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User user) {
		if(user.validate())
			return "Invalid Request";
		userService.save(user);
		return "userRegister";
	}
}
