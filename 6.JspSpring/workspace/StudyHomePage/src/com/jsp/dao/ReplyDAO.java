package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.ReplyVO;

public interface ReplyDAO {
	
	//댓글 리스트 출력
	public List<ReplyVO> selectReplyList(SqlSession session, int bno, SearchCriteria cri) throws SQLException;

	//댓글수 출력
	public int selectReplyListCount(SqlSession session, int bno) throws SQLException;

	//댓글 등록
	public void insertReply(SqlSession session, ReplyVO replyVO) throws SQLException;
	
	//댓글 수정
	public void updateReply(SqlSession session, ReplyVO replyVO) throws SQLException;
	
	//댓글 삭제
	public void deleteReply(SqlSession session, int rno) throws SQLException;
	
	//REPLY_SEQ.NEXTVAL 가져오기
	public int selectReplySequenceNextValue(SqlSession session) throws SQLException;
}
