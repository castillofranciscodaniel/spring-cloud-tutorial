package com.springboot.app.products.config;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Primary
	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		return localeResolver;
	}

	/**
	 * TODO Auto-generated method documentation
	 *
	 * @return LocaleChangeInterceptor
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().setTimeZone(TimeZone.getTimeZone("GMT-3"))
				.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
	}

	@Bean
	public RestTemplate restTemplate() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		FormHttpMessageConverter fmConverter = new FormHttpMessageConverter();
		List<MediaType> fmlist = new ArrayList<MediaType>();
		fmlist.add(MediaType.APPLICATION_FORM_URLENCODED);
		messageConverters.add(fmConverter);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.APPLICATION_JSON);
		converter.setSupportedMediaTypes(list);
		messageConverters.add(converter);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;
	}

	/*
	 * public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST",
	 * "DELETE", "PATCH"); }
	 */

}
