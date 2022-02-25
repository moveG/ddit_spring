package kr.or.ddit.service.spring;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

import kr.or.ddit.dao.spring.BoardDAOBean;
import kr.or.ddit.dao.spring.ReplyDAOBean;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAOBean boardDAOBean;
	public void setBoardDAOBean(BoardDAOBean boardDAOBean) {
		this.boardDAOBean = boardDAOBean;
	}
	
	private ReplyDAOBean replyDAOBean;
	public void setReplyDAOBean(ReplyDAOBean replyDAOBean) {
		this.replyDAOBean = replyDAOBean;
	}
	
	//자유게시판 리스트 출력
	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		//현재 page 번호에 맞는 리스트를 perPageNum 개수 만큼 가져옮
		List<BoardVO> boardList = boardDAOBean.selectSearchBoardList(cri);
			
		//Reply Count 입력
		for(BoardVO boardVO : boardList) {
			int replycnt = replyDAOBean.selectReplyListCount(boardVO.getBno());
			boardVO.setReplyCnt(replycnt);
		}
			
		//전체 board 개수
		int totalCount = boardDAOBean.selectSearchBoardListCount(cri);

		// PageMaker 생성.
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("boardList", boardList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	
	//자유게시판 게시물 출력
	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		BoardVO boardVO = boardDAOBean.selectBoardByBno(bno);
		boardDAOBean.increaseViewCount(bno);
		return boardVO;
	}
	
	//자유게시판 게시물 출력
	@Override
	public BoardVO getBoardForModify(int bno) throws SQLException {
		BoardVO board = boardDAOBean.selectBoardByBno(bno);
		return board;
	}
	
	//자유게시판 작성
	@Override
	public void registBoard(BoardVO boardVO) throws SQLException {
		int bno = boardDAOBean.selectBoardSequenceNextValue();
		boardVO.setBno(bno);

		boardDAOBean.insertBoard(boardVO);
	}
	
	//자유게시판 수정
	@Override
	public void modifyBoard(BoardVO boardVO) throws SQLException {
		boardDAOBean.updateBoard(boardVO);
	}
	
	//자유게시판 삭제
	@Override
	public void removeBoard(int bno) throws SQLException {
		boardDAOBean.deleteBoard(bno);
	}
}
