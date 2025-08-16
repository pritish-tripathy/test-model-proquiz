package com.quizpro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class QpWebConfig implements WebMvcConfigurer {
	private static final Logger log = LoggerFactory.getLogger(QpWebConfig.class);
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("WEB MVC CONFIGURER STARTED");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}