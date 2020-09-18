package com.nokk.editoon.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.nokk.editoon.exception.ExpiredTokenException;
import com.nokk.editoon.exception.UnAuthorizationException;
import com.nokk.editoon.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class VerifyAccountInterceptor implements HandlerInterceptor{
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("v1 handler");
		String email = request.getHeader("email");
		
		Cookie cookie = WebUtils.getCookie(request, "access-token");
		if (cookie == null) {
			throw new UnAuthorizationException("UnAuthorization : Access Token Cookie is null ****" + email + "******");
		}
		String token = cookie.getValue();
		String emailByToken = "";
		
		try {
			emailByToken = jwtTokenUtil.getUsernameFromToken(token);
		}catch (MalformedJwtException e) {
			throw new UnAuthorizationException(token);
		}catch (ExpiredJwtException e) {
			throw new ExpiredTokenException("AccessToken " + token);
		}
		if(email == null || email.equals(""))
			throw new UnAuthorizationException(token);
		
		if(email.equals(emailByToken)) {
			return true;
		}else {
			throw new UnAuthorizationException("AccessToken " + token);
		}
	}
}