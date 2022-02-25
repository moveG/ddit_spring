package com.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.IMemberDao;
import com.jsp.dao.MemberDaoImpl;
import com.jsp.dataSource.OracleMyBatisSqlSessionFactory;
import com.jsp.dto.MemberVO;
import com.jsp.dto.PagingVO;
import com.jsp.service.IMemberService;
import com.jsp.service.MemberServiceImpl;


public class MemberList extends HttpServlet {
	
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
		int pageNo = request.getParameter("pageNo") == null
				? 1
				: Integer.parseInt(request.getParameter("pageNo"));
		String searchKey = request.getParameter("searchKey") == null
				? ""
				: request.getParameter("searchKey");
		
		String searchWord = request.getParameter("searchWord") == null
				? ""
				: request.getParameter("searchWord");
		
		String url = "/member/memberList.jsp";
		
		PagingVO pagingVO = new PagingVO();
		pagingVO.setSearchKey(searchKey);
		pagingVO.setSearchWord(searchWord);
		
		int totalCnt = 0;
		
		try {
			totalCnt = memberService.countMember(pagingVO);
		} catch (Exception e) {
			url = "/error/500.jsp";
		}
		
		//한 페이지당 게시물 숫자
		pagingVO.setCountPerPage(10);
		pagingVO.setCurrentPageNo(pageNo);
		//페이징 버튼(이전, 이후) 등장
		pagingVO.setPageSize(5);
		pagingVO.setTotalCount(totalCnt);
		
		try {
			List<MemberVO> list = memberService.listMember(pagingVO);
			int listSize = list.size();
			
			request.setAttribute("list", list);
			request.setAttribute("listSize", listSize);
			request.setAttribute("pagingVO", pagingVO);
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
