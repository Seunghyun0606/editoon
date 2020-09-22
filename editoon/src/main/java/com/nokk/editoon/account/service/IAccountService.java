package com.nokk.editoon.account.service;

import com.nokk.editoon.account.domain.dto.AccountDTO;
import com.nokk.editoon.account.domain.dto.PrimitiveAccountDTO;

public interface IAccountService {
	public boolean validAccountCheck(String email, String password);

	public void saveAccountName(PrimitiveAccountDTO primitiveAccountDTO);
	public void saveAccountPassword(AccountDTO accountDTO);

	public void deleteAccount(String email);
}
