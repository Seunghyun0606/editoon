package com.nokk.editoon.controller.editoon;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nokk.editoon.domain.SuccessResponse;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/test")
@RestController
public class test {

	@ApiOperation(value = "test", httpMethod = "test", notes = "test")
	@PostMapping("/photos")
	public ResponseEntity test(@RequestParam MultipartFile one, MultipartFile two, MultipartFile three) {
		ResponseEntity response = null;
		System.out.println("in");
		System.out.println(one.getName());
		System.out.println(one.getContentType());
		System.out.println(StringUtils.getFilenameExtension(one.getOriginalFilename()));
		System.out.println("-----------------------------------------------------");
		System.out.println(two.getName());
		System.out.println(two.getContentType());
		System.out.println(StringUtils.getFilenameExtension(two.getOriginalFilename()));
		System.out.println("-----------------------------------------------------");
		System.out.println(three.getName());
		System.out.println(three.getContentType());
		System.out.println(StringUtils.getFilenameExtension(three.getOriginalFilename()));
		
		
		
		final SuccessResponse result = new SuccessResponse();

		result.status = true;
		result.result = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}
}
