package com.springboot.app.oauth.service;

import com.springboot.app.commons.user.models.User;

public interface IUserService {

	User findByUsername(String username);

}
