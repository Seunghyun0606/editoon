package com.nokk.editoon.model.editoon.repository;

import java.io.File;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.nokk.editoon.exception.InternalServerException;

@Repository
public class EditoonImageRepo {
	public void saveFile(MultipartFile multipartFile, String rootPath, String saveFileName){
		
			File dir = new File(rootPath);
			System.out.println(dir.getAbsolutePath());
			if(!dir.exists())
				dir.mkdirs();
			
			try {
				File file = new File(rootPath, saveFileName);
				multipartFile.transferTo(file);
			} catch (Exception e) {
				throw new InternalServerException("Cannot save user upload file" + e.getMessage());
			}
			
		}
}
