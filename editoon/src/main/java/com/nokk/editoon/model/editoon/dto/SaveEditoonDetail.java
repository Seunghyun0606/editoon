package com.nokk.editoon.model.editoon.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SaveEditoonDetail {
	private int no;
	private String subject;
	private MultipartFile thumbnail;
	private List<MultipartFile> image;
}
