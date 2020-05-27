package com.springboot.app.users.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.app.commons.controller.GenericController;
import com.springboot.app.commons.service.ResourceNotFoundException;
import com.springboot.app.commons.user.models.User;
import com.springboot.app.users.service.UserService;

@RestController
public class UserController extends GenericController<User, Long> {

	private UserService userService;

	@Autowired
	UserController(UserService userService) {
		super(userService);
		this.userService = userService;
	}

	@Override
	@PostMapping(name = "createBatch", path = "batch")
	public ResponseEntity<?> createBatch(@Valid List<User> listT, BindingResult result)
			throws ResourceNotFoundException {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EndPoint bloqueado");
	}

	@Override
	@PutMapping(name = "updateBatch", path = "batch")
	public ResponseEntity<?> updateBatch(@Valid List<User> listT, BindingResult result)
			throws ResourceNotFoundException {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EndPoint bloqueado");
	}

	@Override
	@DeleteMapping(name = "deleteBatch", path = "batch")
	public ResponseEntity<?> delete(@Valid List<User> listT, BindingResult result) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EndPoint bloqueado");
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
