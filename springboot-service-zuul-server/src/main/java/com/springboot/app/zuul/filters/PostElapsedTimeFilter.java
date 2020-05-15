package com.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


@Component
public class PostElapsedTimeFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PostElapsedTimeFilter.class);

	@Override
	public boolean shouldFilter() {
		// true para que lo ejecute siempre
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info("Entrando a post filter");
		Long startTime = (Long) request.getAttribute("startTime");
		Long endTime = System.currentTimeMillis();
		Long elapsedTime = endTime - startTime;

		log.info(String.format("Tiempo transcurrido en milisegundos: %s", elapsedTime));
		log.info(String.format("Tiempo transcurrido en segundos: %s", elapsedTime.doubleValue() / 1000.00));

		return null;
	}

	@Override
	public String filterType() {
		// post o route --> otras opciones
		return "post";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}
