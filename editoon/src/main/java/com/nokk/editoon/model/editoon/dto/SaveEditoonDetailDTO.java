package com.nokk.editoon.model.editoon.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SaveEditoonDetailDTO {
	private int no;
	private String subject;
	private MultipartFile thumbnail;
	private MultipartFile[] image;
	private String createDate;
}
