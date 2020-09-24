package com.nokk.editoon.model.account.repository;

import java.io.File;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.nokk.editoon.exception.InternalServerException;

@Repository
public class ProfileImageRepo {
	
	public void saveFile(MultipartFile multipartFile, String rootPath, String saveFileName){
		
	//	rootPath = "/home/jenkins/workspace/joinmeeting/backend/resources";

		File dir = new File(rootPath);
		if(!dir.exists())
			dir.mkdirs();
		
		try {
			File file = new File(rootPath, saveFileName);
			multipartFile.transferTo(file);
		} catch (Exception e) {
			throw new InternalServerException("Cannot save user upload file" + e.getMessage());
		}
		
	}
	
	public void deleteFile(String rootPath, String deleteFileName) {
		File dir = new File(rootPath);

		try {
			File file = new File(rootPath, deleteFileName);
			
			File[] deleteFolderList = file.listFiles();
			
			for (File f : deleteFolderList) {
				if (f.getPath().contains(deleteFileName))
					f.delete();
			}
		} catch (Exception e) {
			throw new InternalServerException("Cannot save user upload file" + e.getMessage());
		}
	}
}
