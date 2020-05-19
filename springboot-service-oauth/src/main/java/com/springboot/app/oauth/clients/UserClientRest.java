package com.springboot.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.app.commons.clients.GenericClientRest;
import com.springboot.app.commons.user.models.User;

@FeignClient(name = "users-service")
public interface UserClientRest extends GenericClientRest<User> {

	@GetMapping
	public User findByUsernameLike(@RequestParam String find, @RequestParam String username);

}
