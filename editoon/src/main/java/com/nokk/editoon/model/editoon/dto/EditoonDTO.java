package com.nokk.editoon.model.editoon.dto;

import java.util.List;

import com.nokk.editoon.model.editoon.entity.EditoonDetailEntity;

import lombok.Data;

@Data
public class EditoonDTO {
	private int _id;
	private List<EditoonDetailEntity> editoonDetails;
}
