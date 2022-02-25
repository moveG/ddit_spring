package com.jsp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.jsp.utils.MakeFileName;

public class SaveFileResolver {
	
	public static List<File> fileUpload(FileItem[] items, String uploadPath) 
			throws Exception {
		List<File> uploadFileList = new ArrayList<File>();
		
		//파일의 경로를 만듦
		File file = new File(uploadPath);
		file.mkdirs();
		
		if(items != null) for(FileItem item : items) {
			String fileName = new File(item.getName()).getName();	//사용자 파일명
			fileName = MakeFileName.toUUIDFileName(fileName, "$$");	//고유한 파일명(UUID 적용)
			
			String filePath = uploadPath + File.separator + fileName;
			File storeFile = new File(filePath);
			
			//local HDD에 저장
			try {
				item.write(storeFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			uploadFileList.add(storeFile);
		}
		return uploadFileList;
	}
}
