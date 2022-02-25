package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.ReplyVO;

public class ReplyDAOImpl implements ReplyDAO {
	
	//댓글 리스트 출력
	@Override
	public List<ReplyVO> selectReplyList(SqlSession session, int bno, SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<ReplyVO> replyList = session.selectList("Reply-Mapper.selectReplyList", bno, rowBounds);
		return replyList;
	}
	
	//댓글수 출력
	@Override
	public int selectReplyListCount(SqlSession session, int bno) throws SQLException {
		int count=session.selectOne("Reply-Mapper.selectReplyListCount", bno);
		return count;
	}
	
	//댓글 등록
	@Override
	public void insertReply(SqlSession session, ReplyVO replyVO) throws SQLException {
		session.update("Reply-Mapper.insertReply", replyVO);
	}
	
	//댓글 수정
	@Override
	public void updateReply(SqlSession session, ReplyVO replyVO) throws SQLException {
		session.update("Reply-Mapper.updateReply", replyVO);
	}
	
	//댓글 삭제
	@Override
	public void deleteReply(SqlSession session, int rno) throws SQLException {
		session.update("Reply-Mapper.deleteReply", rno);
	}
	
	//REPLY_SEQ.NEXTVAL 가져오기
	@Override
	public int selectReplySequenceNextValue(SqlSession session) throws SQLException {
		int rno = session.selectOne("Reply-Mapper.selectReplySequenceNextValue");
		return rno;
	}
}
