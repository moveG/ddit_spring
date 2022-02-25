package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.BoardDAO;
import com.jsp.dao.ReplyDAO;
import com.jsp.dto.BoardVO;

public class BoardServiceImpl implements BoardService {

	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	public ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	//자유게시판 리스트 출력
	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();

			//현재 page 번호에 맞는 리스트를 perPageNum 개수 만큼 가져옮
			List<BoardVO> boardList = boardDAO.selectSearchBoardList(session, cri);
			
			//Reply Count 입력
			for(BoardVO board : boardList) {
				int replycnt = replyDAO.selectReplyListCount(session, board.getBno());
				board.setReplyCnt(replycnt);
			}
			
			//전체 board 개수
			int totalCount = boardDAO.selectSearchBoardListCount(session, cri);

			// PageMaker 생성.
			PageMaker pageMaker = new PageMaker();
			
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);

			dataMap.put("boardList", boardList);
			dataMap.put("pageMaker", pageMaker);

			return dataMap;
		} finally {
			session.close();
		}
	}
	
	//자유게시판 게시물 출력
	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			BoardVO board = boardDAO.selectBoardByBno(session, bno);
			boardDAO.increaseViewCount(session, bno);
			
			return board;
		} finally {
			session.close();
		}
	}
	
	//자유게시판 게시물 출력
	@Override
	public BoardVO getBoardForModify(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			BoardVO board = boardDAO.selectBoardByBno(session, bno);
			
			return board;
		} finally {
			session.close();
		}
	}
	
	//자유게시판 작성
	@Override
	public void registBoard(BoardVO boardVO) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			int bno = boardDAO.selectBoardSequenceNextValue(session);

			boardVO.setBno(bno);

			boardDAO.insertBoard(session, boardVO);
		} finally {
			session.close();
		}
	}
	
	//자유게시판 수정
	@Override
	public void modifyBoard(BoardVO boardVO) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			boardDAO.updateBoard(session, boardVO);
		} finally {
			session.close();
		}
	}
	
	//자유게시판 삭제
	@Override
	public void removeBoard(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			boardDAO.deleteBoard(session, bno);
		} finally {
			session.close();
		}
	}
}
