package com.jsp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;

@WebServlet("/test/login.do")
public class MockLoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MemberVO member = new MemberVO();
		
		switch(id) {
		case "mimi":	//활성
			member.setEnabled(1);
			break;
		case "mama":	//비활성
			member.setEnabled(0);
			break;
		default :
		}
		HttpSession session = request.getSession();
		
		session.setAttribute("loginUser", member);
		
		response.sendRedirect(request.getContextPath() + "/member/list.do");
	}
}
