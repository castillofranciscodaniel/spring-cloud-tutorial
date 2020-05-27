package com.springboot.app.users.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Configuration
public class WebMvcConfiguration {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().setTimeZone(TimeZone.getTimeZone("GMT-3"))
				.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
