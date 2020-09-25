package com.nokk.editoon.model.editoon.entity;

import java.util.List;

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
	private List<String> image;
	private String createDate;
}

