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
		//String url = "redirect:/pds/detail.do?from=modify&pno=" + request.getParameter("pno");
		String url = "/pds/modify_success";
		
		try {
			PdsVO pds = modifyAttaches(request, response);	//파일 삭제, 추가
			pdsService.modify(pds);
			
			request.setAttribute("pds", pds);
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
		PdsVO pds = null;
		
		//request parser
		XSSMultipartHttpServletRequestParser multi 
			= new XSSMultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		
		//삭제한 파일 실제 삭제 및 DB 삭제
		String[] deleteFiles = multi.getParameterValues("deleteFile");	//deleteFile : ano(파일번호) 배열
		
		if(deleteFiles != null && deleteFiles.length > 0) {
			for(String anoStr : deleteFiles) {
				
				int ano = Integer.parseInt(anoStr);
				AttachVO attach = pdsService.getAttachByAno(ano);
				
				String filePath = attach.getUploadPath() + File.separator + attach.getFileName();
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
				AttachVO attach = new AttachVO();
				attach.setFileName(file.getName());
				attach.setUploadPath(uploadPath);
				attach.setFileType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
		
				attachList.add(attach);
			}
		}
		
		//PdsVO 생성
		pds = new PdsVO();
		pds.setPno(Integer.parseInt(multi.getParameter("pno")));
		pds.setTitle(multi.getXSSParameter("title"));
		pds.setWriter(multi.getParameter("writer"));
		pds.setContent(multi.getParameter("content"));
		pds.setAttachList(attachList);
		
		return pds;
	}
}
