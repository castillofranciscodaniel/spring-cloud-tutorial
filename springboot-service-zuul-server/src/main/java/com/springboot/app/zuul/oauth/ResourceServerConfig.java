package com.springboot.app.zuul.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${config.security.oauth.jwt.key}")
	private String  jwtKey;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(this.jwtTokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// tienen prioridad por el orden ...
		http.authorizeRequests()
				.antMatchers("api/security/oauth/token").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products", "/api/items", "/api/users").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/{id}", "/api/users/{id}").hasAnyRole("ADMIN","USER")
				.antMatchers("/api/items/**", "/api/items/**", "/api/users/**").hasRole("ADMIN");
	}

	@Bean
	public JwtTokenStore jwtTokenStore() {
		return new JwtTokenStore(this.accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(this.jwtKey);
		
		return tokenConverter;
	}
}
