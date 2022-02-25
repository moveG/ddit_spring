package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.SaveFileResolver;
import com.jsp.controller.XSSMultipartHttpServletRequestParser;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.exception.NotMultipartFormDataException;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;

public class PdsRegistAction implements Action {
	//업로드 파일환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;	//3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;		//1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200;	//2MB
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/pds/regist_success";
		
		XSSMultipartHttpServletRequestParser multi = null;
		List<AttachVO> attachList = null;
		
		try {
			//1. request 변환(파싱)
			multi = new XSSMultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
			
			//2. 저장경로 설정
			String uploadPath = GetUploadPath.getUploadPath("pds.upload");
			
			//3. 파일저장 및 List<File> 리턴
			List<File> fileList = SaveFileResolver.fileUpload(multi.getFileItems("uploadFile"), uploadPath);
			
			//4. List<File>을 List<AttachVO>로 변환
			if(fileList != null) {
				attachList = new ArrayList<AttachVO>();
				
				for(File file : fileList) {//DB에 저장할 attachVO에 file내용 추가
					AttachVO attachVO = new AttachVO();
					attachVO.setFileName(file.getName());
					attachVO.setUploadPath(uploadPath);
					attachVO.setFileType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
					
					attachList.add(attachVO);
				}
			}
			
			PdsVO pdsVO = new PdsVO();
			pdsVO.setTitle(multi.getXSSParameter("title"));
			pdsVO.setContent(multi.getParameter("content"));
			pdsVO.setWriter(multi.getParameter("writer"));
			pdsVO.setAttachList(attachList);
			
			pdsService.registPds(pdsVO);
		} catch (NotMultipartFormDataException e) {
			e.printStackTrace();
			response.sendError(response.SC_BAD_REQUEST);
			url = null;
		} catch (IOException e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;
		}
		return url;
	}
}
