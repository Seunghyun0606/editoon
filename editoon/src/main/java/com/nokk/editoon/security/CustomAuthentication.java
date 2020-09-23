package com.nokk.editoon.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nokk.editoon.model.account.repository.AccountRepo;
import com.nokk.editoon.util.JwtTokenUtil;

@Component
public class CustomAuthentication {

	@Autowired
	private JwtTokenUtil jtu;

	public Authentication getAuthentication(String token) {
		Map<String, Object> parseInfo = jtu.getUserParseInfo(token);
		List<String> rs = (List) parseInfo.get("role");
		Collection<GrantedAuthority> tmp = new ArrayList<>();
		for (String a : rs) {
			tmp.add(new SimpleGrantedAuthority(a));
		}
		UserDetails userDetails = User.builder().username(String.valueOf(parseInfo.get("email"))).authorities(tmp)
				.password("xxxx").build();
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		return usernamePasswordAuthenticationToken;
	}
}
