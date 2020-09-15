package com.nokk.editoon.account.service;

import com.nokk.editoon.account.domain.dto.AccountSignUpDTO;

public interface INonMemberService {
	// signUp process
	public void signUp(AccountSignUpDTO accountSignUpDTO);

	public boolean emailCheck(String email);

	public void emailAuthSend(String email);

	public boolean emailAuthCheck(String email, String authNum);
}
