package com.jsp.action.notice;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeDetailAction implements Action {
	
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/notice/detail";
		
		try {
			int nno = Integer.parseInt(request.getParameter("nno"));
			String from = request.getParameter("from");	//modify를 통해서 왔을 때는 조회수를 올리면 안되기 때문에 가져옮
			
			NoticeVO notice = null;
			
			if(from != null && from.equals("modify")) {
				notice = noticeService.getNoticeForModify(nno);
			}else {
				notice = noticeService.getNotice(nno); 
			}
			request.setAttribute("notice", notice);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;
		}
		return url;
	}
}
