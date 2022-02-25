package com.jsp.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.exception.NotMultipartFormDataException;

public class MultipartHttpServletRequestParser {
	
	Map<String, String[]> paramString = new HashMap<String, String[]>();
	Map<String, List<FileItem>> paramFile = new HashMap<String, List<FileItem>>();
	
	public MultipartHttpServletRequestParser(HttpServletRequest request, int memory_threshold, int max_file_size, int max_request_size) 
			throws NotMultipartFormDataException, UnsupportedEncodingException, FileUploadException {
		ServletFileUpload upload 
			= ServletFileUploadBuilder.build(request, memory_threshold, max_file_size, max_request_size);
		
		List<FileItem> formItems = upload.parseRequest(request);
		
		if(formItems != null) for(FileItem item : formItems) {
			String paramName = item.getFieldName();

			if(item.isFormField()) {	//1-1. 필드, 일반 parameter(text)
				String[] paramValues = item.getString("UTF-8").split(",");
				this.paramString.put(paramName, paramValues);
			}else {						//1-2. 파일(binary)
				List<FileItem> files = this.paramFile.get(paramName);
				
				if(files == null) {
					files = new ArrayList<FileItem>();
					this.paramFile.put(paramName, files);
				}//if
				files.add(item);
			}//if, else
		}//for
	}
	
	//파라미터 반환
	public String getParameter(String paramName) {
		String[] param = this.paramString.get(paramName);
		String result = null;
		if(param != null) result = param[0];
		return result;
	}
	
	//파라미터 여러개 반환
	public String[] getParameterValues(String paramName) {
		return this.paramString.get(paramName);
	}
	
	//파라미터 이름 반환
	public Enumeration<String> getParameterNames() {
		List<String> paramNames = new ArrayList<String>();
		
		if(paramString.size() > 0) {
			for(String paramName : paramString.keySet()) {
				paramNames.add(paramName);
			}
		}
		Enumeration<String> result = Collections.enumeration(paramNames);
		return result;
	}
	
	//파일 반환
	public FileItem getFileItem(String paramName) {
		List<FileItem> itemList = paramFile.get(paramName);
		FileItem result = null;
		if(itemList != null) result = itemList.get(0);
		return result;
	}
	
	//파일 여러개 반환
	public FileItem[] getFileItems(String paramName) {
		List<FileItem> items = paramFile.get(paramName);
		FileItem[] files = null;
		if(items != null) {
			files = new FileItem[items.size()];
			items.toArray(files);
		}
		return files;
	}
}
