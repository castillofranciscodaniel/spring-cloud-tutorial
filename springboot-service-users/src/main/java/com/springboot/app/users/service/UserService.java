package com.springboot.app.users.service;

import com.springboot.app.commons.service.GenericService;
import com.springboot.app.commons.user.models.User;

public interface UserService extends GenericService<User> {

	User findByUsernameLike(String username);
}
