package com.jsp.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


public class MemberInsert extends HttpServlet {
	
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
		String url = "/member/memberInsert.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String url = "/member/checkInsert.jsp";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MemberVO vo = new MemberVO();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String picture = request.getParameter("picture") == ""
				? "noImage.jpg"
				: request.getParameter("picture");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String strDate = request.getParameter("regdate");
		String register = request.getParameter("register");
		try {
			Date regdate = sdf.parse(strDate);
			vo.setRegdate(regdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setEmail(email);
		vo.setPicture(picture);
		vo.setPhone(phone);
		vo.setName(name);
		vo.setAddress(address);
		vo.setRegister(register);
		
		try {
			int cnt = memberService.insertMember(vo);
			
			request.setAttribute("cnt", cnt);
			request.setAttribute("id", id);
		} catch (Exception e) {
			url = "/error/500.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
