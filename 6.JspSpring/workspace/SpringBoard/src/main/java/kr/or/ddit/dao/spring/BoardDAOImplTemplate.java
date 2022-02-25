package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dao.BoardDAO;
import com.jsp.dto.BoardVO;

public class BoardDAOImplTemplate implements BoardDAOBean {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	//자유게시판 리스트 출력
	@Override
	public List<BoardVO> selectSearchBoardList(SearchCriteria cri) throws SQLException {
		return boardDAO.selectSearchBoardList(session, cri);
	}
	
	//자유게시판 리스트 숫자 출력
	@Override
	public int selectSearchBoardListCount(SearchCriteria cri) throws SQLException {
		return boardDAO.selectSearchBoardListCount(session, cri);
	}

	//자유게시판 게시물 출력
	@Override
	public BoardVO selectBoardByBno(int bno) throws SQLException {
		return boardDAO.selectBoardByBno(session, bno);
	}

	//사진 삭제
	@Override
	public BoardVO selectBoardByImage(String imageFile) throws SQLException {
		return session.selectOne("Board-Mapper.selectBoardByImage", imageFile);
	}
	
	//자유게시판 작성
	@Override
	public void insertBoard(BoardVO boardVO) throws SQLException {
		boardDAO.insertBoard(session, boardVO);
	}
	
	//자유게시판 수정
	@Override
	public void updateBoard(BoardVO boardVO) throws SQLException {
		boardDAO.updateBoard(session, boardVO);
	}
	
	//자유게시판 삭제
	@Override
	public void deleteBoard(int bno) throws SQLException {
		boardDAO.deleteBoard(session, bno);
	}

	//조회수 증가
	@Override
	public void increaseViewCount(int bno) throws SQLException {
		boardDAO.increaseViewCount(session, bno);
	}
	
	//BOARD_SEQ.NEXTVAL 가져오기
	@Override
	public int selectBoardSequenceNextValue() throws SQLException {
		return boardDAO.selectBoardSequenceNextValue(session);
	}	
}
