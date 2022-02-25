package com.jsp.action.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.NoticeModifyCommand;
import com.jsp.controller.XSSHttpRequestParameterAdapter;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeModifyAction implements Action {
	
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "redirect:/notice/detail.do?from=modify&nno=" + request.getParameter("nno");
		
		try {
			NoticeModifyCommand modData = (NoticeModifyCommand) XSSHttpRequestParameterAdapter.execute(request, NoticeModifyCommand.class, true);
			
			NoticeVO noticeVO = modData.toNoticeVO();
			
			noticeVO.setContent(request.getParameter("content"));
			
			noticeService.modifyNotice(noticeVO);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;
		}
		return url;
	}
}
