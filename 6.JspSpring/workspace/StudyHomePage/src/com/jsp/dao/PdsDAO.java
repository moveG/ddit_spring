package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.PdsVO;

public interface PdsDAO {
	
	//자료게시판 출력
	public List<PdsVO> selectSearchPdsList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	//자료게시물 숫자
	public int selectSearchPdsListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	//자료 게시물 출력
	public PdsVO selectPdsByPno(SqlSession session, int pno) throws SQLException;
	
	//자료 게시물 조회수 증가
	public void increaseViewCount(SqlSession session, int pno) throws SQLException;
	
	//시퀀스 가져오기
	public int selectPdsSequenceNextValue(SqlSession session) throws SQLException;
	
	//자료 게시물 작성
	public void insertPds(SqlSession session, PdsVO pdsVO) throws SQLException;

	//자료 게시물 수정
	public void updatePds(SqlSession session, PdsVO pdsVO) throws SQLException;
	
	//자료 게시물 삭제
	public void deletePds(SqlSession session, int pno) throws SQLException;	
}
