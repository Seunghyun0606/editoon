package com.nokk.editoon.model.account.service;

import com.nokk.editoon.model.account.dto.AccountSignUpDTO;

public interface INonMemberService {
	// signUp process
	public void signUp(AccountSignUpDTO accountSignUpDTO);

	public boolean emailCheck(String email);

	public void emailAuthSend(String email);

	public boolean emailAuthCheck(String email, String authNum);
}
