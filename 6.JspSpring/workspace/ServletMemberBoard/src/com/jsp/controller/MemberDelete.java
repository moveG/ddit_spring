package com.jsp.controller;

import java.io.IOException;
import java.net.URLEncoder;

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

public class MemberDelete extends HttpServlet {
	
private IMemberService memberService;
	
	{
		memberService = new MemberServiceImpl();
		SqlSessionFactory factory = new OracleMyBatisSqlSessionFactory();
		IMemberDao memberDao = new MemberDaoImpl();
		((MemberServiceImpl)memberService).setMemberDao(memberDao);
		((MemberServiceImpl)memberService).setSqlSessionFactory(factory);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String url = "/member/checkDelete.jsp";
		String id = request.getParameter("id");
		
		try {
			int cnt = memberService.deleteMember(id);
			
			request.setAttribute("cnt", cnt);
			request.setAttribute("id", id);
		} catch (Exception e) {
			url = "/error/500.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
