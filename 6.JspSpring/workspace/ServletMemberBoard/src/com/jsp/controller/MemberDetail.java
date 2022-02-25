package com.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.IMemberDao;
import com.jsp.dao.MemberDaoImpl;
import com.jsp.dataSource.OracleMyBatisSqlSessionFactory;
import com.jsp.dto.MemberVO;
import com.jsp.service.IMemberService;
import com.jsp.service.MemberServiceImpl;

public class MemberDetail extends HttpServlet {

	private IMemberService memberService;
	
	{
		memberService = new MemberServiceImpl();
		SqlSessionFactory factory = new OracleMyBatisSqlSessionFactory();
		IMemberDao memberDao = new MemberDaoImpl();
		((MemberServiceImpl)memberService).setMemberDao(memberDao);
		((MemberServiceImpl)memberService).setSqlSessionFactory(factory);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String url = "/member/memberDetail.jsp";
		
		try {
			MemberVO vo = memberService.detailMember(id);
			
			String password = "";
			for(int i = 0; i < vo.getPwd().length(); i++) {
				password += "•";
			}
			vo.setPwd(password);
			
			String authority = "미등록";
			if(vo.getAuthority().equals("ROLE_MANAGER")){
				authority = "매니저";
			}else if(vo.getAuthority().equals("ROLE_USER")){
				authority = "직원";
			}else if(vo.getAuthority().equals("ROLE_ADMIN")){
				authority = "관리자";
			}
			vo.setAuthority(authority);
			
			String enabled = "미등록";
			if(vo.getEnabled() == 0){
				enabled = "퇴사";
			}else if(vo.getEnabled() == 1){
				enabled = "재직";
			}else if(vo.getEnabled() == 2){
				enabled = "휴직";
			}
			
			request.setAttribute("enabled", enabled);
			request.setAttribute("vo", vo);
		} catch (Exception e) {
			url = "/error/500.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
