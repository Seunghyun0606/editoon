package com.nokk.editoon.model.editoon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nokk.editoon.exception.ForbidenUserException;
import com.nokk.editoon.model.account.entity.AccountEntity;
import com.nokk.editoon.model.account.repository.AccountRepo;
import com.nokk.editoon.model.editoon.dto.EditoonDetailDTO;
import com.nokk.editoon.model.editoon.entity.EditoonDetailEntity;
import com.nokk.editoon.model.editoon.entity.EditoonEntity;
import com.nokk.editoon.model.editoon.repository.EditoonDetailRepo;
import com.nokk.editoon.util.MapperUtil;

@Service
public class EditoonServiceImpl implements IEditoonService{
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private EditoonDetailRepo editoonDetailRepo;
	
	@Autowired
	private MapperUtil mapperUtil;
	
	@Override
	public int verifyOwnEditoon(String email) {
		Integer isInDatabase = -1;
		Optional<AccountEntity> optional = accountRepo.findAccountByEmail(email);
		if(optional.isPresent()) {
			isInDatabase = optional.get().getNo();
		}else {
			throw new ForbidenUserException("getLoginInfo \n This email is cannot access : " + email);
		}
		return isInDatabase;
	}
	
	@Override
	public List<EditoonDetailDTO> getEditoonThumbnails(String email) {
		int accountNo = verifyOwnEditoon(email);
		
		EditoonEntity editoonEntity = editoonDetailRepo.findEditoonDetailList(accountNo);
		
		List<EditoonDetailDTO> list = new ArrayList<EditoonDetailDTO>();
		
		if(editoonEntity.getEditoonDetails() != null || !editoonEntity.getEditoonDetails().isEmpty()) {
			for(EditoonDetailEntity editoonDetailEntity : editoonEntity.getEditoonDetails()) {
				EditoonDetailDTO editoonDetailDTO = mapperUtil.convertToDTO(editoonDetailEntity, EditoonDetailDTO.class);
				list.add(editoonDetailDTO);
			}
		}
		
		return list;
	}

	@Override
	public EditoonDetailDTO getEditoonDetail(String email, int _id) {
		int accountNo = verifyOwnEditoon(email);
		
		EditoonEntity editoonEntity = editoonDetailRepo.findEditoonDetailContent(accountNo, _id);
		EditoonDetailDTO editoonDetailDTO = new EditoonDetailDTO();
		
		if(editoonEntity.getEditoonDetails() != null || !editoonEntity.getEditoonDetails().isEmpty()) {
			editoonDetailDTO = mapperUtil.convertToDTO(editoonEntity.getEditoonDetails().get(0), EditoonDetailDTO.class);
		}
		
		return editoonDetailDTO;
	}
	
}
