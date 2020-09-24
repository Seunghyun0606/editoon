package com.nokk.editoon.model.account.service;

import com.nokk.editoon.model.account.dto.AccountDTO;
import com.nokk.editoon.model.account.dto.AccountModifyDTO;
import com.nokk.editoon.model.account.dto.LoginInfoDTO;

public interface IAccountService {
	public LoginInfoDTO getLoginInfo(String accessToken);
	public boolean validAccountCheck(String email, String password);

	public void saveAccountNameAndImage(AccountModifyDTO accountModifyDTO);
	public void saveAccountPassword(AccountDTO accountDTO);

	public void deleteAccount(String email);
	
	public boolean canUseFileExtension(String fileExtension);
	
}
