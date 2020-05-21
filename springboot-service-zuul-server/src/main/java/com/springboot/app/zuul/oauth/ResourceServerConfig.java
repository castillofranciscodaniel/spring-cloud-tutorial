package com.springboot.app.zuul.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(this.jwtTokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// tienen prioridad por el orden ...
		http.authorizeRequests().antMatchers("/api/security/oauth/token").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products", "/api/items", "/api/users").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/{id}", "/api/users/{id}").hasAnyRole("ADMIN", "USER")
				.antMatchers("/api/items/**", "/api/items/**", "/api/users/**").hasRole("ADMIN").anyRequest()
				.authenticated().and().cors().configurationSource(corsConfigurationSource());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		// * --> cualquier origen
		corsConfig.addAllowedOrigin("*");
		// corsConfig.addAllowedOrigin("http://localhost:4200");
		// corsConfig.setAllowedOrigins(Arrays.asList("", ""));
		corsConfig.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
				source.registerCorsConfiguration("/**", corsConfig);;
				
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(this.corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
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
