package com.spring.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.command.MultiPartCommand;

@Controller
public class FileUploadController {
	
	@RequestMapping("/fileUploadForm")
	public void fileUpload() {}
	
	@RequestMapping(value = "/multipartFile"
				  , method = RequestMethod.POST
				  , produces = "text/plain;charset=utf-8")
	public ModelAndView uploadByMultipartFile(@RequestParam(value = "title", defaultValue = "제목없음") String title
											, @RequestParam("file") MultipartFile multi
											, HttpServletRequest request
											, HttpServletResponse response
											, ModelAndView mnv) throws Exception {
		mnv.setViewName("fileUploaded");
		
		fileUpload(multi, title, request, response, mnv);
		
		return mnv;
	}
	
	@RequestMapping(value = "/multipartHttpServletRequest"
				  , method = RequestMethod.POST)
	public ModelAndView uploadByMultipartHttpServletRequest(MultipartHttpServletRequest request
														  , HttpServletResponse response
														  , ModelAndView mnv) throws Exception {
		String title = request.getParameter("title");
		MultipartFile multi = request.getFile("file");
		
		mnv.setViewName("fileUploaded");
		
		fileUpload(multi, title, request, response, mnv);
		
		return mnv;
	}
	
	@RequestMapping(value = "/commandObject"
				  , method = RequestMethod.POST)
	public ModelAndView uploadByCommandObject(MultiPartCommand command
											, HttpServletRequest request
											, HttpServletResponse response
											, ModelAndView mnv) throws Exception {
		MultipartFile multi = command.getFile();
		String title = command.getTitle();
		
		mnv.setViewName("fileUploaded");
		
		fileUpload(multi, title, request, response, mnv);
		
		return mnv;
	}
	
	private void fileUpload(MultipartFile multi
						  , String title
						  , HttpServletRequest request
						  , HttpServletResponse response
						  , ModelAndView mnv) throws Exception {
		//파일유무 확인
		if(multi.isEmpty()) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('파일이 없습니다!');</script>");
			out.println("<script>history.go(-1);</script>");
			
			mnv.setViewName(null);
			return;
		}
		
		//파일용량 제한(5MB)
		if(multi.getSize() > 1024 * 1024 * 5) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('용량초과 입니다!');</script>");
			out.println("<script>history.go(-1);</script>");
			
			mnv.setViewName(null);
			return;
		}
		
		//파일저장 폴더 설정
		String uploadPath = request.getSession().getServletContext().getRealPath("resources/upload");
		
		//파일명 중복 방지
		String fileName = UUID.randomUUID().toString().replace("-", "") + "$$" + multi.getOriginalFilename(); 
		
		//파일경로 확인 및 파일 생성
		File file = new File(uploadPath, fileName);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//파일 저장
		multi.transferTo(file);
		
		//model.addAttribute("", ): request.setAttribute("", );의 spring버전
		mnv.addObject("title", title);	//addObject() : Model에 넣어줌
		mnv.addObject("originalFileName", multi.getOriginalFilename());
		mnv.addObject("uploadedFileName", file.getName());
		mnv.addObject("uploadPath", file.getAbsolutePath());
	}
}
