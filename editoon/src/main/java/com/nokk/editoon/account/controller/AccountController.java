package com.nokk.editoon.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokk.editoon.account.domain.dto.AccountDTO;
import com.nokk.editoon.account.service.IAccountService;
import com.nokk.editoon.domain.SuccessResponse;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/account")
@RestController
public class AccountController {
	@Autowired
	private IAccountService accountService;
	
	// 받아야하는것 - access token , id, pw , new pw, new name + header에 Email
	@ApiOperation(value = "accountModify", httpMethod = "POST", notes = "Hello this is accountModify")
	@PostMapping("/v1/modify")
	public ResponseEntity accountModify(HttpServletRequest request,
			@RequestBody(required = true) Map<String, String> map) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		String email = map.get("email");
		String password = map.get("password");
		String newPassword = map.get("newPassword");
		String newName = map.get("newName");

		boolean isValidAccount = accountService.validAccountCheck(email, password);

		if (isValidAccount) {
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setEmail(email);
			accountDTO.setPassword(newPassword);
			accountDTO.setName(newName);
			System.out.println(accountDTO.toString());
			boolean res = accountService.saveAccount(accountDTO);
			if (res) {
				result.status = true;
				result.result = "success";
				response = new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // NOT_FOUND
			}
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
	public ResponseEntity accountDelete(HttpServletRequest request,
			@RequestBody(required = true) Map<String, String> map) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		int no = Integer.parseInt(map.get("accountNo"));
		String email = map.get("email");
		String password = map.get("password");
		boolean isValidAccount = accountService.validAccountCheck(email, password);

		if (isValidAccount) {
			boolean res = accountService.deleteAccount(email, no);
			if (res) {
				result.status = true;
				result.result = "success";
				response = new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} else { // 유효한 회원정보가 아닐경우
			result.status = true;
			result.result = "fail";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response; // fail이 발생하게 되고, 이 경우에는 비밀번호를 다시 확인하라는 문구를 출력한다.
		}

		return response;
	} // 만약 Unauthorized가 뜨면 access token 이 변조된것이다. 로그아웃 시켜야함.
}

