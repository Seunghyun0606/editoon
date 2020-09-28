package com.nokk.editoon.controller.editoon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokk.editoon.domain.SuccessResponse;
import com.nokk.editoon.model.editoon.dto.EditoonDetailDTO;
import com.nokk.editoon.model.editoon.dto.SaveEditoonDetailDTO;
import com.nokk.editoon.model.editoon.service.IEditoonService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/editoon")
@RestController
public class EditoonController {

	@Autowired
	private IEditoonService editoonService;

	@ApiOperation(value = "saveEditoonDetail", httpMethod = "POST", notes = "Hello this is saveEditoonDetail")
	@GetMapping("/v1/saveEditoonDetail")
	public ResponseEntity saveEditoonDetail(@ModelAttribute SaveEditoonDetailDTO saveEditoonDetailDTO) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();
		int retSaveEditoonDetial = editoonService.saveEditoonDetail(saveEditoonDetailDTO);
		
		result.status = true;
		if(retSaveEditoonDetial == -1) {
			result.result = "noImage";
		}else if(retSaveEditoonDetial == -2) {
			result.result = "noThumbnail";
		}else {
			result.result = "success";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@ApiOperation(value = "getEditoonThumbnails", httpMethod = "GET", notes = "Hello this is getEditoonThumbnails")
	@GetMapping("/v1/getEditoonThumbnails/{email}")
	public ResponseEntity getEditoonThumbnails(@PathVariable(name = "email") String email) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();

		List<EditoonDetailDTO> editoonDetailList = new ArrayList<EditoonDetailDTO>();
		editoonDetailList = editoonService.getEditoonThumbnails(email);

		result.status = true;
		if (editoonDetailList != null || !editoonDetailList.isEmpty()) {
			Map<String, Object> retMap = new HashMap<>();
			retMap.put("editoonDetailList", editoonDetailList);
			result.result = "success";
			result.map = retMap;
		} else {
			result.result = "empty";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}

	@ApiOperation(value = "getEditoonDetail", httpMethod = "GET", notes = "Hello this is getEditoonDetail")
	@GetMapping("/v1/getEditoonDetail/{email}/{_id}")
	public ResponseEntity getEditoonDetail(@PathVariable(name = "email") String email,
			@PathVariable(name = "_id") String _id) {
		ResponseEntity response = null;
		final SuccessResponse result = new SuccessResponse();

		EditoonDetailDTO editoonDetailDTO = new EditoonDetailDTO();
		editoonDetailDTO = editoonService.getEditoonDetail(email, Integer.parseInt(_id));

		result.status = true;
		if (editoonDetailDTO != null) {
			Map<String, Object> retMap = new HashMap<>();
			retMap.put("editoonDetailDTO", editoonDetailDTO);
			result.result = "success";
			result.map = retMap;
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.result = "fail";
			response = new ResponseEntity<>(result, HttpStatus.NO_CONTENT); // 204 에러
		}

		return response;
	}
}
