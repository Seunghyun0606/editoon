package com.nokk.editoon.model.account.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AccountModifyDTO {
	private int no;
	private String email;
	private String name;
	private String image;
	private MultipartFile multipartFile;
	private String isChange;
}
