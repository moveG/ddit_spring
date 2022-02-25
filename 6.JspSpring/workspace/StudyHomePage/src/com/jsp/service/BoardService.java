package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.BoardVO;

public interface BoardService {
	
	//자유게시판 리스트 출력
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException;
	
	//자유게시판 게시물 출력
	public BoardVO getBoard(int bno) throws SQLException;
	public BoardVO getBoardForModify(int bno) throws SQLException;
	
	//자유게시판 작성
	public void registBoard(BoardVO boardVO) throws SQLException;
	
	//자유게시판 수정
	public void modifyBoard(BoardVO boardVO) throws SQLException;
	
	//자유게시판 삭제
	public void removeBoard(int bno) throws SQLException;
}
