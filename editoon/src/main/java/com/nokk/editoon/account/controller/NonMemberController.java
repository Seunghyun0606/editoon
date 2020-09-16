package com.nokk.editoon.account.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokk.editoon.account.domain.dto.AccountSignUpDTO;
import com.nokk.editoon.account.service.INonMemberService;
import com.nokk.editoon.domain.SuccessResponse;

import io.swagger.annotations.ApiOperation;
/*
NonMemberController(회원가입)

Function

1. Email 중복 확인 및 인증 코드 전송
    - Method : GET
    - URI : localhost:8080/editoon/nonmember/email/authSend/{email}
        - ex. [localhost:8080/editoon](http://localhost:8080/editoon)/nonmember/email/authSend/dkdlrnf0@gmail.com
    - Input : email
    - Output
        - 경우 1. 이미 가입된 이메일인 경우
            - HttpStatus : 200
            - result : fail
        - 경우 2. 가입 가능한 이메일인 경우 & 인증 코드 정상 송신
            - HttpStatus : 200
            - result : success
        - 경우 3. 가입 가능한 이메일인 경우 & 인증 코드 송신 불가 | DB 등 Server내 오작동
            - HttpStatus : 500

2. Email 인증 코드 확인
    - Method : POST
    - URI : [localhost:8080/editoon/nonmember/email/authCheck](http://localhost:8080/editoon/nonmember/email/authCheck)
    - Input :
        - Content-Type : application/json
        - email
        - authNum
    - Output
        - 경우 1. 인증 성공
            - HttpStatus : 200
            - result : success
        - 경우 2. 인증 실패
            - HttpStatus : 200
            - result : fail
        - 경우 3. Redis 등 Server 내 오작동
            - HttpStatus : 500

3. 회원가입
    - Method : POST
    - URI : [localhost:8080/editoon/nonmember/](http://localhost:8080/editoon/nonmember/email/authCheck)signUp
    - Input :
        - Content-Type : application/json
        - email
        - name
        - password
    - Output
        - 경우 1. 회원가입 성공
            - HttpStatus : 200
            - result : success
        - 경우 2. DB 등 Server 내 오작동
            - HttpStatus : 500
 */
@RequestMapping("/nonmember")
@RestController
public class NonMemberController {

	@Autowired
	private INonMemberService nonMemberService;

	@ApiOperation(value = "email auth send", httpMethod = "GET", notes = "Hello this is email authentication(SEND)")
	@GetMapping("/email/authSend/{email}")
	public ResponseEntity emailAuthSend(@PathVariable(name = "email") String email) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		// 회원가입 입력 email에 대한 중복 체크.
		boolean emailDuplicationCheck = nonMemberService.emailCheck(email);
		if (emailDuplicationCheck) {
			result.status = true;
			result.result = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = true;
			result.result = "fail";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
		// email 인증번호 송신
		nonMemberService.emailAuthSend(email);

		return response;
	}
	
	@ApiOperation(value = "email auth check", httpMethod = "POST", notes = "Hello this is email authentication(CHECK)")
	@PostMapping("/email/authCheck")
	public ResponseEntity emailAuthCheck(@RequestBody Map<String, String> map) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();

		// Input Data
		String email = map.get("email");
		String authNum = map.get("authNum");
		// 인증번호 검증
		boolean ret = nonMemberService.emailAuthCheck(email, authNum);
		result.status = true;
		if (ret) {
			result.result = "success";
		} else {
			result.result = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}
	
	@ApiOperation(value = "signUp", httpMethod = "POST", notes = "Hello this is signUp")
	@PostMapping("/signUp")
	public ResponseEntity signUp(@RequestBody(required = true) AccountSignUpDTO accountSignUpDTO) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		System.out.println(accountSignUpDTO.getEmail());
		System.out.println(accountSignUpDTO.getName());
		System.out.println(accountSignUpDTO.getPassword());
		nonMemberService.signUp(accountSignUpDTO);
		
		result.status = true;
		result.result = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}
}
