package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.BoardVO;

public class BoardDAOImpl implements BoardDAO {
	
	//자유게시판 리스트 출력
	@Override
	public List<BoardVO> selectSearchBoardList(SqlSession session, SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();		
		RowBounds rowBounds = new RowBounds(offset,limit);		
		
		List<BoardVO> boardList = session.selectList("Board-Mapper.selectSearchBoardList", cri, rowBounds);
		return boardList;
	}
	
	//자유게시판 리스트 숫자 출력
	@Override
	public int selectSearchBoardListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count = session.selectOne("Board-Mapper.selectSearchBoardListCount", cri);
		return count;
	}
	
	//자유게시판 게시물 출력
	@Override
	public BoardVO selectBoardByBno(SqlSession session, int bno) throws SQLException {
		BoardVO boardVO = session.selectOne("Board-Mapper.selectBoardByBno", bno);
		return boardVO;
	}

	//자유게시판 작성
	@Override
	public void insertBoard(SqlSession session, BoardVO boardVO) throws SQLException {
		session.update("Board-Mapper.insertBoard", boardVO);
	}
	
	//자유게시판 수정
	@Override
	public void updateBoard(SqlSession session, BoardVO boardVO) throws SQLException {
		session.update("Board-Mapper.updateBoard", boardVO);
	}

	//자유게시판 삭제
	@Override
	public void deleteBoard(SqlSession session, int bno) throws SQLException {
		session.update("Board-Mapper.deleteBoard", bno);
	}
	
	//조회수 증가
	@Override
	public void increaseViewCount(SqlSession session, int bno) throws SQLException {
		session.update("Board-Mapper.increaseViewCount", bno);
	}
	
	//BOARD_SEQ.NEXTVAL 가져오기
	@Override
	public int selectBoardSequenceNextValue(SqlSession session) throws SQLException {
		int seq_num = session.selectOne("Board-Mapper.selectBoardSequenceNextValue");
		return seq_num;
	}
}
