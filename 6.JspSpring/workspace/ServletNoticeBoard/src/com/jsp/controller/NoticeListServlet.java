package com.jsp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.Criteria;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.NoticeDAO;
import com.jsp.dao.NoticeDAOImpl;
import com.jsp.dataSource.OracleMyBatisSqlSessionFactory;
import com.jsp.dto.NoticeVO;
import com.jsp.exception.NotNumberException;
import com.jsp.service.NoticeService;
import com.jsp.service.NoticeServiceImpl;

public class NoticeListServlet extends HttpServlet {
	
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
		String url = "/notice/list.jsp";
		
		String pageParam = request.getParameter("page");
		String perPageNumParam = request.getParameter("perPageNum");
		String searchTypeParam = request.getParameter("searchType");
		String searchWordParam = request.getParameter("searchWord");
		
		try {
			Criteria cri = null;
			
			if(pageParam != null && perPageNumParam != null) {
				cri = new SearchCriteria(pageParam, perPageNumParam, searchTypeParam, searchWordParam);
			}else {
				cri = new SearchCriteria();
			}
			List<NoticeVO> pointList = noticeService.getPointListPage();
			Map<String, Object> dataMap = noticeService.getNoticeListPage(cri);
			
			request.setAttribute("pointList", pointList);
			request.setAttribute("dataMap", dataMap);
		} catch (NotNumberException e) {
			request.setAttribute("exception", e);
			url = "/error/message.jsp";
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
