package com.nokk.editoon.model.editoon.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nokk.editoon.exception.ForbidenUserException;
import com.nokk.editoon.exception.InternalServerException;
import com.nokk.editoon.model.account.entity.AccountEntity;
import com.nokk.editoon.model.account.repository.AccountRepo;
import com.nokk.editoon.model.editoon.dto.EditoonDetailDTO;
import com.nokk.editoon.model.editoon.dto.SaveEditoonDetailDTO;
import com.nokk.editoon.model.editoon.entity.EditoonDetailEntity;
import com.nokk.editoon.model.editoon.entity.EditoonEntity;
import com.nokk.editoon.model.editoon.repository.EditoonDetailRepo;
import com.nokk.editoon.model.editoon.repository.EditoonImageRepo;
import com.nokk.editoon.model.editoon.repository.EditoonRepo;
import com.nokk.editoon.util.CreateUUID;
import com.nokk.editoon.util.MapperUtil;

@Service
public class EditoonServiceImpl implements IEditoonService{
	private static final String IMAGE_FOLDER = "/resource/image/editoonImg";
	
//	private static final String IMAGE_FOLDER = "c:/image";
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private EditoonDetailRepo editoonDetailRepo;
	
	@Autowired
	private EditoonImageRepo editoonImageRepo;
	
	@Autowired
	private EditoonRepo editoonRepo;
	
	@Autowired
	private CreateUUID createUUID;
	
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
		EditoonEntity editoonEntity = null;
		editoonEntity = editoonDetailRepo.findEditoonDetailList(accountNo);
		List<EditoonDetailDTO> list = new ArrayList<EditoonDetailDTO>();
		
		if(editoonEntity != null && (editoonEntity.getEditoonDetails() != null && !editoonEntity.getEditoonDetails().isEmpty())) {
			System.out.println("들어오나");
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
		EditoonEntity editoonEntity = null;
		editoonEntity = editoonDetailRepo.findEditoonDetailContent(accountNo, _id);
		EditoonDetailDTO editoonDetailDTO = null;
		
		if(editoonEntity != null && (editoonEntity.getEditoonDetails() != null && !editoonEntity.getEditoonDetails().isEmpty())) {
			editoonDetailDTO = mapperUtil.convertToDTO(editoonEntity.getEditoonDetails().get(0), EditoonDetailDTO.class);
		}
		
		return editoonDetailDTO;
	}
	
	@Override // 이것도 boolean으로 바꿔줘야할듯 바꼈는지 안바꼈는지. 아무것도 없으면 저장 실패하도록 하게
	public int saveEditoonDetail(SaveEditoonDetailDTO saveEditoonDetailDTO) {
		boolean hasThumbNail = false;
		boolean hasEditoonImage = false;
		String thumbNailName = "";
		List<String> imageNameList = new ArrayList<>();
		String createDate = "";
		int ret = -1;
		try {
			if(saveEditoonDetailDTO.getThumbnail() != null && !saveEditoonDetailDTO.getThumbnail().isEmpty()) {
//				String fileExtension = StringUtils
//						.getFilenameExtension(saveEditoonDetailDTO.getThumbnail().getOriginalFilename());
				thumbNailName = createUUID.createUUID("png");
				editoonImageRepo.saveFile(saveEditoonDetailDTO.getThumbnail(), IMAGE_FOLDER + "/" + saveEditoonDetailDTO.getNo(), thumbNailName);
				hasThumbNail = true;
			}
			
			if(!saveEditoonDetailDTO.getImage().isEmpty()) {
				for(MultipartFile multipartFile : saveEditoonDetailDTO.getImage()) {
					String newImageName = "";
//					String fileExtension = StringUtils
//							.getFilenameExtension(multipartFile.getOriginalFilename());
					newImageName = createUUID.createUUID("png");
					editoonImageRepo.saveFile(multipartFile, IMAGE_FOLDER + "/" + saveEditoonDetailDTO.getNo(), newImageName);
					imageNameList.add(newImageName);
				}
				
				
				DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
				createDate = dateFormat.format(new Date());
				hasEditoonImage = true;
			}
			
			if(hasThumbNail && hasEditoonImage) {
				ret = editoonRepo.insertEditoonDetail(saveEditoonDetailDTO, thumbNailName, imageNameList, createDate);
				return ret; // 0 ~ 은 정상적으로 저장되었고, 저장된 id 값
			}else {
				if(!hasThumbNail) {
					ret = -2; // -2 일 경우 썸네일이 없음
					return ret;
				}else if(!hasEditoonImage) {
					ret = -1; // -1 일 경우 이미지가 없음
					return ret;
				}
				return ret;
			}
		}catch(Exception e) {
			throw new InternalServerException("saveEditoonDetail \n" + "detail Exception Info : " + e.getMessage());
		}
		
	}
}
