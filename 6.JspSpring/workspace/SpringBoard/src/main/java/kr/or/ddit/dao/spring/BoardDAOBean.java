package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.BoardVO;

public interface BoardDAOBean {
	
	//자유게시판 리스트 출력
	public List<BoardVO> selectSearchBoardList(SearchCriteria cri) throws SQLException;
	
	//자유게시판 리스트 숫자 출력
	public int selectSearchBoardListCount(SearchCriteria cri) throws SQLException;
	
	//자유게시판 게시물 출력
	public BoardVO selectBoardByBno(int bno) throws SQLException;
	
	//사진 삭제
	public BoardVO selectBoardByImage(String imageFile) throws SQLException;
	
	//자유게시판 작성
	public void insertBoard(BoardVO boardVO) throws SQLException;
	
	//자유게시판 수정
	public void updateBoard(BoardVO boardVO) throws SQLException;
	
	//자유게시판 삭제
	public void deleteBoard(int bno) throws SQLException;
	
	//조회수 증가
	public void increaseViewCount(int bno) throws SQLException;
	
	//BOARD_SEQ.NEXTVAL 가져오기
	public int selectBoardSequenceNextValue() throws SQLException;
}
