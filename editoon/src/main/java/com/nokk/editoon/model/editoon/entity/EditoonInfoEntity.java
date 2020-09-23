package com.nokk.editoon.model.editoon.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("editoonNo")
@Data
public class EditoonInfoEntity {
	private int _id;
	private int no;
	
	public EditoonInfoEntity(int _id, int no) {
		this._id = _id;
		this.no = no;
	}
}
