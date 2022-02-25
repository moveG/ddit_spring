package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dao.ReplyDAO;
import com.jsp.dto.ReplyVO;

public class ReplyDAOImplTemplate implements ReplyDAOBean {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
		
	//댓글 리스트 출력
	@Override
	public List<ReplyVO> selectReplyList(int bno, SearchCriteria cri) throws SQLException {
		return replyDAO.selectReplyList(session, bno, cri);
	}
	
	//댓글수 출력
	@Override
	public int selectReplyListCount(int bno) throws SQLException {
		return replyDAO.selectReplyListCount(session, bno);
	}
	
	//댓글 등록
	@Override
	public void insertReply(ReplyVO replyVO) throws SQLException {
		replyDAO.insertReply(session, replyVO);
	}
	
	//댓글 수정
	@Override
	public void updateReply(ReplyVO replyVO) throws SQLException {
		replyDAO.updateReply(session, replyVO);
	}
	
	//댓글 삭제
	@Override
	public void deleteReply(int rno) throws SQLException {
		replyDAO.deleteReply(session, rno);
	}
	
	//REPLY_SEQ.NEXTVAL 가져오기
	@Override
	public int selectReplySequenceNextValue() throws SQLException {
		return replyDAO.selectReplySequenceNextValue(session);
	}
}
