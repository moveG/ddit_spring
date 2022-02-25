package com.jsp.action.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.command.ReplyRemoveCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.service.ReplyService;

public class ReplyRemoveAction implements Action {
	
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = null;
		
		try {
			//파라미터 가져옮
			ReplyRemoveCommand removeCMD = (ReplyRemoveCommand) HttpRequestParameterAdapter.execute(request, ReplyRemoveCommand.class);
						
			//댓글 삭제
			replyService.removeReply(removeCMD.getRnoInt());
			
			PageMaker pageMaker = new PageMaker();
			
			pageMaker.setCri(new SearchCriteria());
			pageMaker.setTotalCount(replyService.getReplyListCount(removeCMD.getBnoInt()));
			
			int page = removeCMD.getPageInt();
			int realEndPage = pageMaker.getRealEndPage();
			
			if(page > realEndPage) {//더 작은걸 넘긴다.
				page = realEndPage;
			}
			
			PrintWriter out = response.getWriter();
			out.print(page);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			//response.sendError(response.SC_INTERNAL_SERVER_ERROR);			//둘 다 사용 가능
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);	//둘 다 사용 가능
			url = null;
		}
		return url;
	}
}
