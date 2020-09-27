package com.nokk.editoon.model.editoon.service;

import java.util.List;

import com.nokk.editoon.model.editoon.dto.EditoonDetailDTO;

public interface IEditoonService {
	public int verifyOwnEditoon(String email);
	public List<EditoonDetailDTO> getEditoonThumbnails(String email);
	public EditoonDetailDTO getEditoonDetail(String email, int _id);
//	public boolean saveEditoon();
}