package com.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.NoticeModifyCommand;
import com.jsp.command.NoticeRegistCommand;
import com.jsp.dao.NoticeDAO;
import com.jsp.dao.NoticeDAOImpl;
import com.jsp.dataSource.OracleMyBatisSqlSessionFactory;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;
import com.jsp.service.NoticeServiceImpl;

public class NoticeModifyServlet extends HttpServlet {
	
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
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		String url = "/notice/modify.jsp";
		
		try {
			NoticeVO noticeVO = noticeService.getNotice(nNo);
			
			request.setAttribute("noticeVO", noticeVO);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_BAD_REQUEST);
			return;
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String url = "/notice/modify_success.jsp";
		NoticeVO noticeVO = null;

		try {
			NoticeModifyCommand regData
			= (NoticeModifyCommand) HttpRequestParameterAdapter
			.execute(request, NoticeModifyCommand.class);

			noticeVO = regData.toNoticeVO();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_BAD_REQUEST);
			return;
		}
		
		try {
			noticeService.modifyNotice(noticeVO);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
