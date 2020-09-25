package com.nokk.editoon.model.editoon.dto;

import java.util.List;

import lombok.Data;

@Data
public class EditoonDetailDTO {
	private int _id;
	private String subject;
	private String thumbnail;
	private List<String> image;
	private String createDate;

	public EditoonDetailDTO() {}

	public EditoonDetailDTO(int _id, String subject, String thumbnail, List<String> image, String createDate) {
		this._id = _id;
		this.subject = subject;
		this.thumbnail = thumbnail;
		this.image = image;
		this.createDate = createDate;
	}
	
}
