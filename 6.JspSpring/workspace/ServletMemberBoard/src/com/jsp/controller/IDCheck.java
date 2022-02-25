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
import com.jsp.service.IMemberService;
import com.jsp.service.MemberServiceImpl;

public class IDCheck extends HttpServlet {
	
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
		String url = "/member/checkId.jsp";
		String id = request.getParameter("id");
		
		try {
			int cnt = memberService.idCheck(id);
			
			request.setAttribute("cnt", cnt);
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
