package com.jsp.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberDetailAction implements Action {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/member/detail";
		
		String id = request.getParameter("id");
		
		try {
			MemberVO memberVO = memberService.getMember(id);
			
			String password = "";
			for(int i = 0; i < memberVO.getPwd().length(); i++) {
				password += "•";
			}
			memberVO.setPwd(password);

			request.setAttribute("memberVO", memberVO);
		} catch (Exception e) {
			//url = "/member/detail_fail.jsp";
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;
		}
		return url;
	}
}
