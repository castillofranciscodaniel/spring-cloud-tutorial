package com.springboot.app.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.users.models.User;
import com.springboot.app.users.service.UserService;

@RestController
public class UserController extends GenericController<User> {

	@SuppressWarnings("unused")
	private UserService userService;

	@Autowired
	UserController(UserService userService) {
		super(userService);
		this.userService = userService;
	}


}
