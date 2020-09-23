package com.nokk.editoon.model.editoon.dto;

import lombok.Data;

@Data
public class EditoonDetailDTO {
	private int _id;
	private String subject;
	private String thumbnail;
	private String image;

	public EditoonDetailDTO() {}
	
	public EditoonDetailDTO(int _id, String subject, String thumbnail, String image) {
		this._id = _id;
		this.subject = subject;
		this.thumbnail = thumbnail;
		this.image = image;
	}
	
	
}
