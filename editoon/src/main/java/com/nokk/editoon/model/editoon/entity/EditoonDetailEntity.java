package com.nokk.editoon.model.editoon.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("editoon")
@Data
public class EditoonDetailEntity {
	@Id
	private int _id;
	private String subject;
	private String thumbnail;
	private String image;
	private String createDate;
}

