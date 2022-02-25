package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.ReplyVO;

public interface ReplyService {
	
	//댓글 리스트 출력
	Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException;
	
	//댓글수 출력
	int getReplyListCount(int bno) throws SQLException;
	
	//댓글 등록
	void registReply(ReplyVO replyVO) throws SQLException;
	
	//댓글 수정
	void modifyReply(ReplyVO replyVO) throws SQLException;
	
	//댓글 삭제
	void removeReply(int rno) throws SQLException;
}
