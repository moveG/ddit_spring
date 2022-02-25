package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.jsp.action.Action;
import com.jsp.controller.SaveFileResolver;
import com.jsp.controller.XSSMultipartHttpServletRequestParser;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;

public class PdsModifyAction implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/pds/modify_success";
		
		try {
			PdsVO pdsVO = modifyAttaches(request, response);
			pdsService.modifyPds(pdsVO);
			
			request.setAttribute("pdsVO", pdsVO);
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
	
	//업로드 파일환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;	//3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;		//1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200;	//2MB
	
	private PdsVO modifyAttaches(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		PdsVO pdsVO = null;
		
		//1. request parser
		XSSMultipartHttpServletRequestParser multi 
			= new XSSMultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		
		//2. 파일 삭제 및 DB 삭제
		String[] deleteFiles = multi.getParameterValues("deleteFile");	//deleteFile : ano(파일번호) 배열

		if(deleteFiles != null && deleteFiles.length > 0) {
			for(String anoStr : deleteFiles) {
				
				int ano = Integer.parseInt(anoStr);
				AttachVO attachVO = pdsService.getAttachByAno(ano);
				
				String filePath = attachVO.getUploadPath() + File.separator + attachVO.getFileName();
				File targetFile = new File(filePath);
				
				if(targetFile.exists()) {
					targetFile.delete();			//파일 삭제
				}
				pdsService.removeAttachByAno(ano);	//DB 삭제
			}
		}
		
		//추가된 파일 저장
		FileItem[] fileItems = multi.getFileItems("uploadFile");
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		
		List<File> fileList = SaveFileResolver.fileUpload(fileItems, uploadPath);
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		if(fileList.size() > 0) {
			for(File file : fileList) {
				AttachVO attachVO = new AttachVO();
				attachVO.setFileName(file.getName());
				attachVO.setUploadPath(uploadPath);
				attachVO.setFileType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
		
				attachList.add(attachVO);
			}
		}
		
		//PdsVO 생성
		pdsVO = new PdsVO();
		pdsVO.setPno(Integer.parseInt(multi.getParameter("pno")));
		pdsVO.setTitle(multi.getXSSParameter("title"));
		pdsVO.setWriter(multi.getParameter("writer"));
		pdsVO.setContent(multi.getParameter("content"));
		pdsVO.setAttachList(attachList);
		
		return pdsVO;
	}
}
