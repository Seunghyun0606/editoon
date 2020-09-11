package com.nokk.editoon.account.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nokk.editoon.account.domain.dto.AccountSignUpDTO;
import com.nokk.editoon.domain.SuccessResponse;
import com.nokk.editoon.editoon.service.INonMemberService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/nonmember")
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

	@ApiOperation(value = "email duplicated check", httpMethod = "POST", notes = "Hello this is email duplicated check")
	@PostMapping("/email")
	public ResponseEntity emailCheck(@RequestBody Map<String, String> map) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		boolean ret = nonMemberService.emailCheck(map.get("email"));
		result.status = true;
		if (ret) {
			result.result = "success";
		} else {
			result.result ="fail";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@ApiOperation(value = "email auth send", httpMethod = "POST", notes = "Hello this is email authentication(SEND)")
	@PostMapping("/email/authSend")
	public ResponseEntity emailAuth(@RequestBody Map<String, String> map) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		boolean ret = nonMemberService.emailAuthSend(map.get("email"));
		result.status = true;
		if (ret) {
			result.result = "success";
		} else {
			result.result = "fail";
		}
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
