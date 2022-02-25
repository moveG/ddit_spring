package kr.or.ddit.service.spring;

import java.sql.SQLException;

import com.jsp.dto.BoardVO;

import kr.or.ddit.dao.spring.BoardDAOBean;

public class ScheduledBoardServiceImpl extends BoardServiceImpl implements ScheduledBoardService {
	
	private BoardDAOBean boardDAOBeanChild;
	public void setBoardDAOBeanChild(BoardDAOBean boardDAOBeanChild) {
		this.boardDAOBeanChild = boardDAOBeanChild;
	}
	
	//사진 삭제
	@Override
	public BoardVO getBoardByImage(String imageFile) throws SQLException {
		BoardVO boardVO = boardDAOBeanChild.selectBoardByImage(imageFile); 
		return boardVO;
	}
}
