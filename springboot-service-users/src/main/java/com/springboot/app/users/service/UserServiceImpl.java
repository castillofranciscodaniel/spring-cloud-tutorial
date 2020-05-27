package com.springboot.app.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.commons.service.GenericServiceImpl;
import com.springboot.app.commons.service.ResourceNotFoundException;
import com.springboot.app.commons.user.models.User;
import com.springboot.app.users.repository.UsersRepository;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

	private UsersRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UsersRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		super(userRepository);
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User findByUsernameLike(String username) {
		return this.userRepository.findByUsernameLike(username);
	}

	@Override
	@Transactional
	public User save(User user) throws ResourceNotFoundException {
		// If id is null, it is a post, if not.. it is a put

		if (user.getId() == null) {
			this.postAction(user);
		} else {
			this.putAction(user);
		}

		return super.save(user);
	}

	private void putAction(User user) throws ResourceNotFoundException {
		User userStorage = this.findById(user.getId());
		if (userStorage == null) {
			throw new ResourceNotFoundException("El id no existe.");
		}
		user.setPassword(userStorage.getPassword());
		user.setUsername(userStorage.getUsername());
		user.setMail(userStorage.getMail());
		user.setEnabled(userStorage.getEnabled());
		user.setLoginTry(userStorage.getLoginTry());
		user.setRoles(userStorage.getRoles());
	}

	private void postAction(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setLoginTry(0);
		// falta hacer que se cree por defecto ROLE_USER
	}

}
