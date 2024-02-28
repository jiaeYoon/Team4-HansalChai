package com.hansalchai.haul.common.auth.config;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansalchai.haul.common.auth.jwt.JwtProvider;

import jakarta.servlet.Filter;

@Configuration
public class WebAuthConfig implements WebMvcConfigurer {

	@Bean
	public FilterRegistrationBean<Filter> jwtValidationFilter(JwtProvider jwtProvider, ObjectMapper objectMapper) {
		FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
		filterFilterRegistrationBean.setFilter(new JwtValidationFilter(jwtProvider, objectMapper));
		filterFilterRegistrationBean.setOrder(1);
		return filterFilterRegistrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserAuthorizationInterceptor())
			.order(1)
			.excludePathPatterns("/api/v1/users/sign-in", "/api/v1/users/sign-up", "/api/v2/users/customers/sign-in",
				"/api/v2/users/drivers/sign-in", "/api/v1/reservations/guest", "/api/v1/reservations/guest/**")
			.excludePathPatterns("*/h2-console*", "/swagger-ui/**", "*/api-docs*", "/css/**", "/*.ico", "/error");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoggedInUserArgumentResolver());
	}
}
