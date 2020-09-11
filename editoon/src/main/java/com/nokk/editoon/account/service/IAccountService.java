package com.nokk.editoon.account.service;

import com.nokk.editoon.account.domain.dto.AccountDTO;

public interface IAccountService {
	public boolean validAccountCheck(String email, String password);

	public boolean saveAccount(AccountDTO accountDTO);

	public boolean deleteAccount(String email, int no);
}
