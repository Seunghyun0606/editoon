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

@RequestMapping("/nonmember")
@RestController
public class NonMemberController {

	@Autowired
	private INonMemberService nonMemberService;

	
	@ApiOperation(value = "signUp", httpMethod = "POST", notes = "Hello this is signUp")
	@PostMapping("/signUp")
	public ResponseEntity signUp(@RequestBody(required = true) AccountSignUpDTO accountSignUpDTO) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		boolean ret = nonMemberService.signUp(accountSignUpDTO);
		if (ret) {
			result.status = true;
			result.result = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return response;
	}

//	@ApiOperation(value = "email duplicated check", httpMethod = "POST", notes = "Hello this is email duplicated check")
//	@PostMapping("/email")
//	public ResponseEntity emailCheck(@RequestBody Map<String, String> map) {
//		ResponseEntity response = null;
//		final SuccessResponse result = new SuccessResponse();
//		boolean ret = 	
//		result.status = true;
//		if (ret) {
//			result.result = "success";
//		} else {
//			result.result ="fail";
//		}
//		response = new ResponseEntity<>(result, HttpStatus.OK);
//		return response;
//	}

	@ApiOperation(value = "email auth send", httpMethod = "POST", notes = "Hello this is email authentication(SEND)")
	@GetMapping("/email/authSend/{email}")
	public ResponseEntity emailAuth(@PathVariable(name = "email") String email) {
		System.out.println(email);
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		
		// 회원가입 입력 email에 대한 중복 체크.
		boolean emailDuplicationCheck = nonMemberService.emailCheck(email);
		if (emailDuplicationCheck) {
			result.status = true;
			result.result = "success";
		} else {
			result.status = true;
			result.result = "fail";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
		
		
		boolean ret = nonMemberService.emailAuthSend(email);
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}

	@ApiOperation(value = "email auth check", httpMethod = "POST", notes = "Hello this is email authentication(CHECK)")
	@PostMapping("/email/authCheck")
	public ResponseEntity emailAuthCheck(@RequestBody Map<String, String> map) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		boolean ret = nonMemberService.emailAuthCheck(map.get("email"), map.get("authNum"));
		result.status = true;
		if (ret) {
			result.result = "success";
		} else {
			result.result = "fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}
}
