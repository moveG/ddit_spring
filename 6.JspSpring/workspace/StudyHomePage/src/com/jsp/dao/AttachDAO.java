package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.AttachVO;

public interface AttachDAO {
	
	//첨부파일 리스트 출력
	public List<AttachVO> selectAttachByPno(SqlSession session, int pno) throws SQLException;
	
	//첨부파일 출력
	public AttachVO selectAttachByAno(SqlSession session, int ano) throws SQLException;
	
	//첨부파일 삽입
	public void insertAttach(SqlSession session, AttachVO attachVO) throws SQLException;
	
	//첨부파일 삭제
	public void deleteAttach(SqlSession session, int ano) throws SQLException;
	
	//첨부파일 전체삭제(첨부파일 게시물 삭제)
	public void deleteAllAttach(SqlSession session, int pno) throws SQLException;
}
