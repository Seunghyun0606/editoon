package com.nokk.editoon.account.domain.dto;

import lombok.Data;

@Data
public class AccountDTO {
	private int no;
	private String email;
	private String name;
	private String password;
	private String image;
}
