package com.nokk.editoon.security;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.nokk.editoon.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
    JwtTokenUtil jtu;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("[onLogoutSuccess] entered..");
		/*
		 * 1. refresh-token 삭제 2. access-token 쿠기가 있다면 삭제. 3. blacklist 삽입(10분) - 이미 발급한
		 * 토큰은 어떻게 할 수 없기 때문에 해당 토큰을 사용하지 못하도록 처리해야한다.
		 */
		// 1. redis에 저장된 refresh token 을 삭제시켜줌
		Cookie cookie = WebUtils.getCookie(request, "access-token");
//		String accessToken = request.getHeader("Authorization").substring(7);
		String accessToken = WebUtils.getCookie(request, "access-token").getValue();
		String email = null;
		boolean check = false;

		try {
			email = jwtTokenUtil.getUsernameFromToken(accessToken);
			if (redisTemplate.opsForValue().get(email) != null) {
				redisTemplate.delete(email);
			} else {
				check = true;
			}
		} catch (ExpiredJwtException e) {
			email = e.getClaims().getSubject();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			if (!check) {
				redisTemplate.opsForValue().set(accessToken, true);
				redisTemplate.expire(accessToken, 10 * 60, TimeUnit.SECONDS);
			}
			
			Cookie[] cookies = new Cookie[4];
			cookies[0] = WebUtils.getCookie(request, "access-token");
			cookies[1] = WebUtils.getCookie(request, "refresh-token");
			cookies[2] = WebUtils.getCookie(request, "access-token-expiration-date");
			cookies[3] = WebUtils.getCookie(request, "XSRF-TOKEN");
//			cookies[4] = WebUtils.getCookie(request, "refresh-token-expiration-date");
			
			for(Cookie c : cookies) {
				if(c != null) {
					c.setDomain("localhost");
					c.setHttpOnly(true);
					c.setPath("editoon");
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
			
			response.setStatus(HttpStatus.OK.value());
		}
	}
}
