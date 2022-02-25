package com.jsp.action.member;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.controller.MultipartHttpServletRequestParser;
import com.jsp.controller.SaveFileResolver;
import com.jsp.dto.MemberVO;
import com.jsp.exception.NotMultipartFormDataException;
import com.jsp.service.MemberService;
import com.jsp.utils.GetUploadPath;

public class MemberModifyAction implements Action {
	
	//업로드 파일환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500;			//500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1;		//1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2;	//2MB
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/member/modify_success";
		
		try {
			MultipartHttpServletRequestParser multiReq
				= new MultipartHttpServletRequestParser(
					request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
			
			String id = multiReq.getParameter("id");
			String pwd = multiReq.getParameter("pwd");
			String email = multiReq.getParameter("email");
			int enabled = Integer.parseInt(multiReq.getParameter("enabled"));
			String phone = multiReq.getParameter("phone");
			String name = multiReq.getParameter("name");
			String address = multiReq.getParameter("address");
			String authority = multiReq.getParameter("authority");
			
			MemberVO memberVO = new MemberVO();
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setEmail(email);
			memberVO.setPhone(phone);
			memberVO.setName(name);
			memberVO.setEnabled(enabled);
			memberVO.setAddress(address);
			memberVO.setAuthority(authority);
			
			//FILE 처리
			//기존사진 변경여부 확인
			String uploadPicture = multiReq.getParameter("uploadPicture");
			if(uploadPicture != null && !uploadPicture.isEmpty()) {	//사진 변경
				//저장 경로
				String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
				File file = new File(uploadPath);
				file.mkdirs();
				
				//기존사진 삭제
				File deleteFile = new File(uploadPath, multiReq.getParameter("oldPicture"));
				if(deleteFile.exists()) {
					deleteFile.delete();
				}
				
				//최신사진 저장
				List<File> fileList = SaveFileResolver.fileUpload(multiReq.getFileItems("picture"), uploadPath);
				File saveFile = fileList.get(0);
				
				memberVO.setPicture(saveFile.getName());
			}else {
				memberVO.setPicture(multiReq.getParameter("oldPicture"));
			}
			
			//DB처리
			memberService.modifyMember(memberVO);
			request.setAttribute("memberVO", memberVO);
			
			//로그인 사용자 확인
			//request.setAttribute("parentReload", false);
			
			HttpSession session = request.getSession();
			MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
			if(loginUser != null && memberVO.getId().equals(loginUser.getId())) {
				//request.setAttribute("parentReload", true);
				session.setAttribute("loginUser", memberService.getMember(memberVO.getId()));
			}
		} catch (NotMultipartFormDataException e) {
			response.sendError(response.SC_BAD_REQUEST);
			url = null;
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;
		}
		return url;
	}
}
