package com.nokk.editoon.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.nokk.editoon.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class CustomJwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private CustomAuthentication customAuthentication;

	@Bean
	public FilterRegistrationBean JwtRequestFilterRegistration(CustomJwtRequestFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		logger.info("JwtRequestFilter Entered...");
		logger.info("[JwtRequestFilter] requestUrl : " + request.getRequestURI());

//		String token = request.getHeader("Authorization");
		Cookie cookie = WebUtils.getCookie(request, "access-token");
		if (cookie == null) {
			chain.doFilter(request, response);
		}else {
			String token = cookie.getValue();
			System.out.println(token);
			String email = null;
			String accessToken = null;
			if (token != null) {
				accessToken = token;
				try {
					email = jwtTokenUtil.getUsernameFromToken(accessToken);
				} catch (IllegalArgumentException e) {
					logger.warn("Unable to get JWT Token");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				} catch (ExpiredJwtException e) {
					logger.warn("ExpiredJwtException");
					response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				} catch (MalformedJwtException e) {
					logger.warn("MalformedJwtException");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
			
			if (email == null) {
				logger.info("token maybe expired: username is null.");
			} else if (redisTemplate.opsForValue().get(accessToken) != null) { // 이런 부분들을 어떻게 처리하면 좋을지에 대한 고민해봐야할것같음
				logger.warn("this token already logout!");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			} else {
				Authentication authen = customAuthentication.getAuthentication(accessToken);
				SecurityContextHolder.getContext().setAuthentication(authen);
				logger.info("Authentication create success!!");
			}
			
			chain.doFilter(request, response);
		}
	}
}