package com.nokk.editoon.account.domain.dto;

import lombok.Data;

@Data
public class AccountSignUpDTO {
	private String email;
	private String name;
	private String password;
}
