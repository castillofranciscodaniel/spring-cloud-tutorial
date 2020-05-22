package com.springboot.app.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.commons.controller.GenericController;
import com.springboot.app.commons.user.models.User;
import com.springboot.app.users.service.UserService;

@RestController
public class UserController extends GenericController<User> {

	private UserService userService;

	@Autowired
	UserController(UserService userService) {
		super(userService);
		this.userService = userService;
	}

	@GetMapping(params = "find=ByUsernameLike")
	public ResponseEntity<User> findByUsernameLike(@RequestParam String username) {
		User user = this.userService.findByUsernameLike(username);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

}
