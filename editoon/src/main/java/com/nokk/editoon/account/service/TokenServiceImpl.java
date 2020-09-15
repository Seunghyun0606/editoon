package com.nokk.editoon.account.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.nokk.editoon.domain.Token;
import com.nokk.editoon.exception.ExpiredTokenException;
import com.nokk.editoon.exception.InternalServerException;
import com.nokk.editoon.exception.UnAuthorizationException;
import com.nokk.editoon.exception.UnknownException;
import com.nokk.editoon.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Service
public class TokenServiceImpl implements ITokenService{

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	JwtTokenUtil jtu;

	@Autowired
	private HttpServletResponse response;

	/*
	 * AccessToken 으로 AccessToken 을 갱신하는 방법을 사용한 이유 : Session sliding 방식을 도입하며
	 * access token 을 사용하고 있을 때 최대한 refreshtoken 에 대한 접근을 줄이고 보안성을 높이기위함. 사용자는 30분
	 * 이상 사용하지 않은 경우에만 refresh token을 서버에 전달한다.
	 * 
	 * 
	 * 후에 조금 더 보안을 높이기 위해 interceptor에서 검증에 대한 부분을 추가시켜주는게 좋을 것 같음. 추가해야하는 부분 ->
	 * header 에 함께 보낸 email 값과 토큰의 값이 같은지? 권한은 어떤지? 이런 부분들에 대한 추가적인 검증이 필요할것으로 생각됨
	 */
	@Override
	public void newAccessTokenByAccessToken(String accessToken) {
		boolean ret = false;
		try {
			Map<String, Object> parseInfo = jtu.getUserParseInfo(accessToken);
			String emailFromAccessToken = String.valueOf(parseInfo.get("email"));
			
			List<String> rs = (List) parseInfo.get("role");
			String authorityFromAccessToken = rs.get(0);
			ValueOperations<String, Object> vop = redisTemplate.opsForValue();
			Token token = (Token) vop.get(emailFromAccessToken);
			if(token != null) {
				Map<String, Object> parseInfo2 = jtu.getUserParseInfo(token.getToken());
				String emailFromRefreshToken = String.valueOf(parseInfo2.get("email"));
				List<String> rs2 = (List) parseInfo.get("role");
				String authorityFromRefreshToken = rs.get(0);
				boolean checkEmail = emailFromAccessToken.equals(emailFromRefreshToken) ? true : false;
				boolean checkAuthority = authorityFromAccessToken.equals(authorityFromRefreshToken) ? true : false;

				if (checkEmail && checkAuthority) {
					List<GrantedAuthority> tmp = new ArrayList<>();
					tmp.add(new SimpleGrantedAuthority(authorityFromAccessToken));
					final String newAccessToken = jwtTokenUtil.generateAccessToken(emailFromAccessToken, tmp);

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

					Calendar newAccessTokenCal = Calendar.getInstance();
					newAccessTokenCal.add(Calendar.MINUTE, 30);
					String newAccessTokenExpirationDate = simpleDateFormat.format(newAccessTokenCal.getTime());

					// response.setHeader("Access-Control-Expose-Headers", "Authorization, AccessTokenExpiraionDate");
					response.addHeader("Authorization", "Bearer " + newAccessToken);
					response.addHeader("AccessTokenExpiraionDate", newAccessTokenExpirationDate);
					ret = true;
				}else {
					throw new UnAuthorizationException(emailFromAccessToken);
				}
			}else {
				throw new UnAuthorizationException(emailFromAccessToken);
			}
		} catch (ExpiredJwtException e) {
			throw new ExpiredTokenException("AccessToken " + accessToken);
		} catch (MalformedJwtException e) {
			throw new UnAuthorizationException(accessToken);
		} catch (Exception e) {
			throw new InternalServerException("newAccessTokenByAccessToken Internal Server Exception \n" + "detail Exception Info :" + e.getMessage());
		}
		
		if(!ret) {
			throw new UnknownException("newAccessTokenByAccessToken");
		}
		
//		Cookie accessCookie = new Cookie("access-token", newAccessToken);
//		accessCookie.setMaxAge(10 * 60 * 60); // 10분 * 60
//		accessCookie.setPath("/");
//		response.addCookie(accessCookie); // path 설정 / 기간 설정

	}

	@Override
	public void newAccessTokenByRefreshToken(String refreshToken) {
		boolean ret = false;
		
		try {
			String email = jwtTokenUtil.getUsernameFromToken(refreshToken); // refresh token 이 만료된 상태라면 어차피 frontend에서
																			// 재로그인을 요구할것임
			ValueOperations<String, Object> vop = redisTemplate.opsForValue();
			Token token = (Token) vop.get(email);
			if(token != null) {
				String redisRefreshToken = token.getToken();

				if (refreshToken.equals(redisRefreshToken)) { // db에 있는 데이터랑 같은지 비교해주면 valid check 는 끝
					Map<String, Object> parseInfo = jtu.getUserParseInfo(redisRefreshToken);
					List<String> rs = (List) parseInfo.get("role");
					List<GrantedAuthority> tmp = new ArrayList<>();
					for (String a : rs) {
						tmp.add(new SimpleGrantedAuthority(a));
					}
					
					final String newAccessToken = jwtTokenUtil.generateAccessToken(email, tmp);
					
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					
					Calendar accessTokenCal = Calendar.getInstance();
					accessTokenCal.add(Calendar.MINUTE, 30);
					String newAccessTokenExpirationDate = simpleDateFormat.format(accessTokenCal.getTime());
					
					response.addHeader("Authorization", "Bearer " + newAccessToken);
					response.addHeader("AccessTokenExpiraionDate", newAccessTokenExpirationDate);
					ret = true;
					//
				} else {
					throw new UnAuthorizationException(email);
				}
			}else {
				throw new UnAuthorizationException(email);
			}
		} catch (ExpiredJwtException e) {
			throw new ExpiredTokenException("RefreshToken" + refreshToken);
		} catch (MalformedJwtException e) {
			throw new UnAuthorizationException(refreshToken);
		} catch (Exception e) {
			throw new InternalServerException("newAccessTokenByRefreshToken Internal Server Exception \n" + "detail Exception Info :" + e.getMessage());
		}
		
		if(!ret) {
			throw new UnknownException("newAccessTokenByRefreshToken");
		}
	}

}
