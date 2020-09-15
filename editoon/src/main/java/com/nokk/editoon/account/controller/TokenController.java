package com.nokk.editoon.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokk.editoon.account.service.ITokenService;
import com.nokk.editoon.domain.SuccessResponse;

import io.swagger.annotations.ApiOperation;

/*
Token Controller(토큰 갱신)

1. new AccessToken by AccessToken
    - Method : POST
    - URI : [localhost:8080/editoon/](http://localhost:8080/editoon/nonmember/email/authCheck)token/newATBA
    - Input :
        - -
    - Output
        - 경우 1. 갱신 성공
            - HttpStatus : 200
            - result : success
        - 경우 2. 토큰 만료
            - HttpStatus : 406
        - 경우 3. 토큰 위조
            - HttpStatus : 401
        - 경우 4. DB 등 서버 내 오작동
            - HttpStatus : 500

1. new AccessToken by RefreshToken
    - Method : POST
    - URI : [localhost:8080/editoon/](http://localhost:8080/editoon/nonmember/email/authCheck)token/newATBR
    - Input :
        - -
    - Output
        - 경우 1. 갱신 성공
            - HttpStatus : 200
            - result : success
        - 경우 2. 토큰 만료
            - HttpStatus : 406
        - 경우 3. 토큰 위조
            - HttpStatus : 401
        - 경우 4. DB 등 서버 내 오작동
            - HttpStatus : 500
 */

@RequestMapping("/token")
@CrossOrigin(exposedHeaders = "Authorization, AccessTokenExpiraionDate, RefreshToken, RefreshTokenExpiraionDate")
@RestController
public class TokenController {

	@Autowired
	private ITokenService tokenService;

	// newAccessTokenByAccessToken
	@ApiOperation(value = "newAccessTokenByAccessToken", httpMethod = "POST", notes = "Hello this is newAccessTokenByAccessToken")
	@PostMapping("/v2/newATBA")
	public ResponseEntity newAccessTokenByAccessToken(HttpServletRequest request) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();

		String accessToken = request.getHeader("Authorization").substring(7); // 7글자 이상일경우만 조사하도록 변경
		tokenService.newAccessTokenByAccessToken(accessToken);
		
		result.status = true;
		result.result = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	// newAccessTokenByRefreshToken
	@ApiOperation(value = "newAccessTokenByRefreshToken", httpMethod = "POST", notes = "Hello this is newAccessTokenByRefreshToken")
	@PostMapping("/v2/newATBR")
	public ResponseEntity newAccessTokenByRefreshToken(HttpServletRequest request) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();

		String refreshToken = request.getHeader("RefreshToken").substring(7);
		tokenService.newAccessTokenByRefreshToken(refreshToken);
		
		result.status = true;
		result.result = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;

	}
}