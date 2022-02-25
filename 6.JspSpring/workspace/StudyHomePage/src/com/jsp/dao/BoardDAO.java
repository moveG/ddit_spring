package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.BoardVO;

public interface BoardDAO {
	
	//자유게시판 리스트 출력
	public List<BoardVO> selectSearchBoardList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	//자유게시판 리스트 숫자 출력
	public int selectSearchBoardListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	//자유게시판 게시물 출력
	public BoardVO selectBoardByBno(SqlSession session, int bno) throws SQLException;
	
	//자유게시판 작성
	public void insertBoard(SqlSession session, BoardVO boardVO) throws SQLException;
	
	//자유게시판 수정
	public void updateBoard(SqlSession session, BoardVO boardVO) throws SQLException;
	
	//자유게시판 삭제
	public void deleteBoard(SqlSession session, int bno) throws SQLException;
	
	//조회수 증가
	public void increaseViewCount(SqlSession session, int bno) throws SQLException;
	
	//BOARD_SEQ.NEXTVAL 가져오기
	public int selectBoardSequenceNextValue(SqlSession session) throws SQLException;
}
