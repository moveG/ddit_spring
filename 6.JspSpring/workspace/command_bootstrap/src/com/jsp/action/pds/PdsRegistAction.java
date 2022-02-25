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
	
	//1.입력 : commons-fileupload.jar 패키지를 이용하여 FileItem형태로 변환된 MultipartResolver를 받아 Pds를 완성함.
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
			
			//2. 저장할 경로
			String uploadPath = GetUploadPath.getUploadPath("pds.upload");
			
			//3. 업로드된 파일 저장 후 List<File>을 리턴
			List<File> fileList = SaveFileResolver.fileUpload(multi.getFileItems("uploadFile"), uploadPath);
			
			//4. List<File>을 List<Attach>로 변환
			if(fileList != null) {
				attachList = new ArrayList<AttachVO>();
				
				for(File file : fileList) {
					//DB에 저장할 attach에 file내용 추가
					AttachVO attach = new AttachVO();
					attach.setFileName(file.getName());
					attach.setUploadPath(uploadPath);
					attach.setFileType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
					
					attachList.add(attach);
				}
			}
			
			PdsVO pds = new PdsVO();
			pds.setTitle(multi.getXSSParameter("title"));
			pds.setContent(multi.getParameter("content"));	//summernote에서 한번 거르기 때문에 getXSSParameter()를 사용하지 않음
			pds.setWriter(multi.getParameter("writer"));
			pds.setAttachList(attachList);
			
			pdsService.regist(pds);
		} catch (NotMultipartFormDataException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			url = null;
		} catch (IOException e) {
			e.printStackTrace();
			//ExceptionLoggerHelper.write(request, e, new FileUploadResolver());
			//response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			url = null;
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			url = null;
		}
		return url;
	}
}
