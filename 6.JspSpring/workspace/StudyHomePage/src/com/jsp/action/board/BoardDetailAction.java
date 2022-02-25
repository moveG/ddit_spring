package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

public class BoardDetailAction implements Action {
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/board/detail";
		
		try {
			int bno = Integer.parseInt(request.getParameter("bno"));
			String from = request.getParameter("from");
			
			BoardVO boardVO = null;
			
			if(from != null && from.equals("modify")) {
				boardVO = boardService.getBoardForModify(bno);
			}else {
				boardVO = boardService.getBoard(bno);
			}
			request.setAttribute("boardVO", boardVO);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;
		}
		return url;
	}
}
