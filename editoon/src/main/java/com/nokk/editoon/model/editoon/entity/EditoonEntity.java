package com.nokk.editoon.model.editoon.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("editoon")
@Data
public class EditoonEntity {
	@Id
	private int _id;
	private List<EditoonDetailEntity> editoonDetails;
	public EditoonEntity(int _id, List<EditoonDetailEntity> editoonDetails) {
		this._id = _id;
		this.editoonDetails = editoonDetails;
	}
}