package com.springboot.app.oauth.models;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.springboot.app.oauth.service.IUserService;

@Component
public class TokenAditionalInfo implements TokenEnhancer {

	@Autowired
	private IUserService userDetailsService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> extraInfo = new HashMap<String, Object>();

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		// User user = this.userDetailsService.findByUsername(authentication.getName());
		extraInfo.put("name", userPrincipal.getName());
		extraInfo.put("lastName", userPrincipal.getLastName());
		extraInfo.put("mail", userPrincipal.getMail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(extraInfo);

		return accessToken;
	}

}
