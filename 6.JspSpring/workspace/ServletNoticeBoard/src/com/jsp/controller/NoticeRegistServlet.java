package com.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.NoticeRegistCommand;
import com.jsp.dao.NoticeDAO;
import com.jsp.dao.NoticeDAOImpl;
import com.jsp.dataSource.OracleMyBatisSqlSessionFactory;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;
import com.jsp.service.NoticeServiceImpl;

public class NoticeRegistServlet extends HttpServlet {
	
	private NoticeService noticeService;
	
	{
		noticeService = new NoticeServiceImpl();
		SqlSessionFactory factory = new OracleMyBatisSqlSessionFactory();
		NoticeDAO noticeDAO = new NoticeDAOImpl();
		((NoticeServiceImpl)noticeService).setNoticeDAO(noticeDAO);
		((NoticeServiceImpl)noticeService).setSqlSessionFactory(factory);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String url = "/notice/regist.jsp";
		
		try {
			List<String> idList = noticeService.getNoticeWriter();
			
			request.setAttribute("idList", idList);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_BAD_REQUEST);
			return;
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String url = "/notice/regist_success.jsp";
		NoticeVO noticeVO = null;
		try {
			NoticeRegistCommand regData
			= (NoticeRegistCommand) HttpRequestParameterAdapter
			.execute(request, NoticeRegistCommand.class);
			
			noticeVO = regData.toNoticeVO();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_BAD_REQUEST);
			return;
		}
		
		try {
			noticeService.registNotice(noticeVO);
		} catch (Exception e) {
			e.printStackTrace();
			url = "/notice/regist_fail.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
