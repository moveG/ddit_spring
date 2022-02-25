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
import com.jsp.service.MemberServiceForModify;
import com.jsp.utils.GetUploadPath;

public class MemberModifyAction implements Action {
	
	//업로드 파일환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500;			//500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1;		//1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2;	//2MB
	
	private MemberServiceForModify memberServiceForModify;
	public void setMemberServiceForModify(MemberServiceForModify memberServiceForModify) {
		this.memberServiceForModify = memberServiceForModify;
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
			String authority = multiReq.getParameter("authority");
			String name = multiReq.getParameter("name");
			String phone = multiReq.getParameter("phone");
			
			MemberVO member = new MemberVO();
			member.setId(id);
			member.setPwd(pwd);
			member.setPhone(phone);
			member.setEmail(email);
			member.setAuthority(authority);
			member.setName(name);
			
			//file처리
			//기존사진 변경유무 확인
			String uploadPicture = multiReq.getParameter("uploadPicture");
			if(uploadPicture != null && !uploadPicture.isEmpty()) {	//사진 변경 O
				//저장 경로
				String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
				File file = new File(uploadPath);
				file.mkdirs();
				
				//기존 사진 삭제
				File deleteFile = new File(uploadPath, multiReq.getParameter("oldPicture"));
				if(deleteFile.exists()) {
					deleteFile.delete();
				}
				
				//최신 사진 저장
				List<File> fileList = SaveFileResolver.fileUpload(multiReq.getFileItems("picture"), uploadPath);
				File saveFile = fileList.get(0);
				
				member.setPicture(saveFile.getName());
			}else {	//사진 변경 X
				member.setPicture(multiReq.getParameter("oldPicture"));
			}
			
			//DB처리
			memberServiceForModify.modifyMember(member);
			request.setAttribute("member", member);
			
			//로그인 사용자 확인
			//request.setAttribute("parentReload", false);
			
			HttpSession session = request.getSession();
			MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
			if(loginUser != null && member.getId().equals(loginUser.getId())) {
				//request.setAttribute("parentReload", true);
				session.setAttribute("loginUser", memberServiceForModify.getMember(member.getId()));
			}
		} catch (NotMultipartFormDataException e) {
			response.sendError(response.SC_BAD_REQUEST);
			url = null;
		} catch (Exception e) {
			e.printStackTrace();
			//......exception 처리
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;
		}
		return url;
	}

}
