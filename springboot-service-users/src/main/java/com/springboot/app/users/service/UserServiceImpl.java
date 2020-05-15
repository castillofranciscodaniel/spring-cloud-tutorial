package com.springboot.app.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.commons.service.GenericServiceImpl;
import com.springboot.app.users.models.User;
import com.springboot.app.users.repository.UsersRepository;

@Service
public class UserServiceImpl extends GenericServiceImpl<User>
		implements UserService {

	@SuppressWarnings("unused")
	private UsersRepository userRepository;

	@Autowired
	public UserServiceImpl(UsersRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}


}
