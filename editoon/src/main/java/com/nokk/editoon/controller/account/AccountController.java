package com.nokk.editoon.controller.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.nokk.editoon.domain.SuccessResponse;
import com.nokk.editoon.exception.UnAuthorizationException;
import com.nokk.editoon.model.account.dto.AccountDTO;
import com.nokk.editoon.model.account.dto.AccountModifyDTO;
import com.nokk.editoon.model.account.dto.LoginInfoDTO;
import com.nokk.editoon.model.account.service.IAccountService;

import io.swagger.annotations.ApiOperation;
/*
### 3. 회원관리(로그인, 로그아웃, 정보수정 등)

1. login
    - Method : POST
    - URI : [localhost:8080/editoon/](http://localhost:8080/editoon/nonmember/email/authCheck)login
    - Input :
        - header :
            - csrf token
        - body :
            - email
            - password
    - Output
        - 경우 1. 로그인 성공
            - HttpStatus : 200
            - result : success
        - 경우 2. 로그인 실패
            - HttpStatus : 401
        - 경우 3. 토큰 만료
            - HttpStatus : 406
        - 경우 4. 토큰 위조
            - HttpStatus : 401
        - 경우 5. DB 등 서버 내 오작동
            - HttpStatus : 500

         —→> 경우 2 4 는 어차피 권한이 없는것이기 때문에 똑같은 처리입니다!

2. logout
- Method : POST
- URI : [localhost:8080/editoon/](http://localhost:8080/editoon/nonmember/email/authCheck)account/logout
- Input :
    - header :
        - csrf
- Output
    - 경우 1. 로그아웃 성공
        - HttpStatus : 200
        - result : success

    모든 경우(SUCCESS 포함) 그냥 프론트에서도 쿠키에 남아있으면 다 날려버리면 됩니당

3. name 만 변경

- Method : POST
- URI : [localhost:8080/editoon/](http://localhost:8080/editoon/nonmember/email/authCheck)account/v1/nameModify
- Input :
    - header :
        - csrf token
        - email
    - body :
        - email
        - name
- Output
    - 경우 1. 수정성공
        - HttpStatus : 200
        - result : success
    - 경우 2. 토큰 만료
        - HttpStatus : 406
    - 경우 3. 토큰 위조 or 권한 없음
        - HttpStatus : 401
    - 경우 4. DB 등 서버 내 오작동
        - HttpStatus : 500

    → 로그인이 되어있는데 수정이 안되는 경우는 없고 수정이 안된다면 db문제라고 생각합니다.

4. password 변경

- Method : POST
- URI : [localhost:8080/editoon/](http://localhost:8080/editoon/nonmember/email/authCheck)account/v1/passwordModify
- Input :
    - header :
        - csrf token
        - email
    - body :
        - email
        - password
        - newPassword
- Output
    - 경우 1. 수정성공
        - HttpStatus : 200
        - result : success
    - 경우 2. 비밀번호 틀림
        - HttpStatus : 200
        - result : fail
    - 경우 3. 토큰 만료
        - HttpStatus : 406
    - 경우 4. 토큰 위조 or 권한 없음
        - HttpStatus : 401
    - 경우 5. DB 등 서버 내 오작동
        - HttpStatus : 500

    → 로그인이 되어있는데 수정이 안되는 경우는 없고 수정이 안된다면 db문제라고 생각합니다.

5. 회원 delete

- Method : POST
- URI : [localhost:8080/editoon/](http://localhost:8080/editoon/nonmember/email/authCheck)account/v1/delete
- Input :
    - header :
        - csrf token
        - email
    - body :
        - email
        - password
- Output
    - 경우 1. 삭제성공
        - HttpStatus : 200
        - result : success
    - 경우 2. 비밀번호 틀림
        - HttpStatus : 200
        - result : fail
    - 경우 3. 토큰 만료
        - HttpStatus : 406
    - 경우 4. 토큰 위조 or 권한 없음
        - HttpStatus : 401
    - 경우 5. DB 등 서버 내 오작동
        - HttpStatus : 500

    → 로그인이 되어있는데 삭제가 안되는 경우는 없고 삭제가 안된다면 db문제라고 생각합니다.
 */


@RequestMapping("/account")
@RestController
public class AccountController {
	@Autowired
	private IAccountService accountService;
	
	@ApiOperation(value = "getLoginInfo", httpMethod = "POST", notes = "Hello this is getLoginInfo")
	@PostMapping("/getLoginInfo")
	public ResponseEntity getLoginInfo(HttpServletRequest request) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();

		Cookie cookie = WebUtils.getCookie(request, "access-token");
		String accessToken = cookie.getValue();
		LoginInfoDTO loginInfoDTO = accountService.getLoginInfo(accessToken);
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("loginInfoDTO", loginInfoDTO);
		result.status = true;
		result.result = "success";
		result.map = retMap;
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	} // 만약 Unauthorized가 뜨면 access token 이 변조된것이다. 로그아웃 시켜야함.

	@ApiOperation(value = "nameAndImageModify - name", httpMethod = "POST", notes = "Hello this is nameAndImageModify - name and image")
	@PostMapping("/v1/nameAndImageModify")
	public ResponseEntity accountNameAndImageModify(@ModelAttribute AccountModifyDTO accountModifyDTO) {
		ResponseEntity response = null;
		System.out.println(accountModifyDTO.toString());
		final SuccessResponse result = new SuccessResponse();
		accountService.saveAccountNameAndImage(accountModifyDTO);
		result.status = true;
		result.result = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	} // 만약 Unauthorized가 뜨면 access token 이 변조된것이다. 로그아웃 시켜야함.

	@ApiOperation(value = "accountModify - pw", httpMethod = "POST", notes = "Hello this is accountModify - pw")
	@PostMapping("/v1/passwordModify")
	public ResponseEntity accountPasswordModify(@RequestBody(required = true) Map<String, String> map) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		String email = map.get("email");
		String password = map.get("password");
		String newPassword = map.get("newPassword");

		boolean isValidAccount = accountService.validAccountCheck(email, password);

		if (isValidAccount) {
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setEmail(email);
			accountDTO.setPassword(newPassword);
			System.out.println(accountDTO.toString());
			accountService.saveAccountPassword(accountDTO);
			result.status = true;
			result.result = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else { // 유효한 회원정보가 아닐경우
			result.status = true;
			result.result = "fail";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response; // fail이 발생하게 되고, 이 경우에는 비밀번호를 다시 확인하라는 문구를 출력한다.
		}

		return response;
	} // 만약 Unauthorized가 뜨면 access token 이 변조된것이다. 로그아웃 시켜야함.

	// email, password 넘겨주면 됨 + header에 Email
	@ApiOperation(value = "accountDelete", httpMethod = "POST", notes = "Hello this is accountDelete")
	@PostMapping("/v1/delete")
	public ResponseEntity accountDelete(@RequestBody(required = true) Map<String, String> map) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		String email = map.get("email");
		String password = map.get("password");
		boolean isValidAccount = accountService.validAccountCheck(email, password);

		if (isValidAccount) {
			accountService.deleteAccount(email);
			result.status = true;
			result.result = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else { // 유효한 회원정보가 아닐경우
			result.status = true;
			result.result = "fail";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}

		return response;
	} // 만약 Unauthorized가 뜨면 access token 이 변조된것이다. 로그아웃 시켜야함.
}
