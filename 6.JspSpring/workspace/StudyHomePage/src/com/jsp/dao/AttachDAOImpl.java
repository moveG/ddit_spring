package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.AttachVO;

public class AttachDAOImpl implements AttachDAO {
	
	//첨부파일 리스트 출력
	@Override
	public List<AttachVO> selectAttachByPno(SqlSession session, int pno) throws SQLException {
		List<AttachVO> attachList = session.selectList("Attach-Mapper.selectAttachByPno", pno);
		return attachList;
	}
	
	//첨부파일 출력
	@Override
	public AttachVO selectAttachByAno(SqlSession session, int ano) throws SQLException {
		AttachVO attach = session.selectOne("Attach-Mapper.selectAttachByAno", ano);
		return attach;
	}
	
	//첨부파일 삽입
	@Override
	public void insertAttach(SqlSession session, AttachVO attachVO) throws SQLException {
		session.update("Attach-Mapper.insertAttach", attachVO);
	}
	
	//첨부파일 삭제
	@Override
	public void deleteAttach(SqlSession session, int ano) throws SQLException {
		session.update("Attach-Mapper.deleteAttach", ano);		
	}
	
	//첨부파일 전체삭제(첨부파일 게시물 삭제)
	@Override
	public void deleteAllAttach(SqlSession session, int pno) throws SQLException {
		session.update("Attach-Mapper.deleteAllAttach", pno);		
	}
}
