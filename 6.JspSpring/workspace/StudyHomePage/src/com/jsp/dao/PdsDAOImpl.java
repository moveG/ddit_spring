package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.PdsVO;

public class PdsDAOImpl implements PdsDAO {
	
	//자료게시판 출력
	@Override
	public List<PdsVO> selectSearchPdsList(SqlSession session, SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<PdsVO> pdsList = session.selectList("Pds-Mapper.selectSearchPdsList", cri, rowBounds);	
		return pdsList;
	}
	
	//자료게시물 숫자
	@Override
	public int selectSearchPdsListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count = session.selectOne("Pds-Mapper.selectSearchPdsListCount", cri);
		return count;
	}
	
	//자료 게시물 출력
	@Override
	public PdsVO selectPdsByPno(SqlSession session, int pno) throws SQLException {
		PdsVO pds = session.selectOne("Pds-Mapper.selectPdsByPno", pno);
		return pds;
	}
	
	//자료 게시물 조회수 증가
	@Override
	public void increaseViewCount(SqlSession session, int pno) throws SQLException {
		session.update("Pds-Mapper.increaseViewCount", pno);
	}
	
	//시퀀스 가져오기
	@Override
	public int selectPdsSequenceNextValue(SqlSession session) throws SQLException {
		int pno = session.selectOne("Pds-Mapper.selectPdsSequenceNextValue");
		return pno;
	}
	
	//자료 게시물 작성
	@Override
	public void insertPds(SqlSession session, PdsVO pdsVO) throws SQLException {
		session.update("Pds-Mapper.insertPds", pdsVO);
	}
	
	//자료 게시물 수정
	@Override
	public void updatePds(SqlSession session, PdsVO pdsVO) throws SQLException {
		session.update("Pds-Mapper.updatePds", pdsVO);
	}
	
	//자료 게시물 삭제
	@Override
	public void deletePds(SqlSession session, int pno) throws SQLException {
		session.update("Pds-Mapper.deletePds", pno);
	}
}
