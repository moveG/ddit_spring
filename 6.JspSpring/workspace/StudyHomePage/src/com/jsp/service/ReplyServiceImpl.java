package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.MemberDAO;
import com.jsp.dao.ReplyDAO;
import com.jsp.dto.MemberVO;
import com.jsp.dto.ReplyVO;

public class ReplyServiceImpl implements ReplyService {

	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	//댓글 리스트 출력
	@Override
	public Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			List<ReplyVO> replyList = replyDAO.selectReplyList(session, bno, cri);
			
			if(replyList != null) for(ReplyVO reply : replyList) {
				MemberVO member = memberDAO.selectMemberById(session, reply.getReplyer());
				reply.setPicture(member.getPicture());
			}
			
			int count = replyDAO.selectReplyListCount(session, bno);
	
			PageMaker pageMaker = new PageMaker();
			
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(count);
	
			dataMap.put("replyList", replyList);
			dataMap.put("pageMaker", pageMaker);
	
			return dataMap;
		} finally {
			session.close();
		}
	}
	
	//댓글수 출력
	@Override
	public int getReplyListCount(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			int count = replyDAO.selectReplyListCount(session, bno);
			
			return count;
		} finally {
			session.close();
		}
	}
	
	//댓글 등록
	@Override
	public void registReply(ReplyVO replyVO) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			int rno = replyDAO.selectReplySequenceNextValue(session);
			replyVO.setRno(rno);

			replyDAO.insertReply(session, replyVO);
		} finally {
			session.close();
		}
	}
	
	//댓글 수정
	@Override
	public void modifyReply(ReplyVO replyVO) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			replyDAO.updateReply(session, replyVO);
		} finally {
			session.close();
		}		
	}
	
	//댓글 삭제
	@Override
	public void removeReply(int rno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			replyDAO.deleteReply(session, rno);
		} finally {
			session.close();
		}
	}
}
