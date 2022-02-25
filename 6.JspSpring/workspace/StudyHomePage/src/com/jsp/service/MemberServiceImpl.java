package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;

public class MemberServiceImpl implements MemberService{
	
	protected SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	protected MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	//회원리스트 출력
	@Override
	public Map<String, Object> getMemberListPage(Criteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		SearchCriteria searchCri = (SearchCriteria) cri;
		Map<String, Object> dataMap = null;
		
		try {
			List<MemberVO> memberList = null;
			PageMaker pageMaker = null;
			
			memberList = memberDAO.selectSearchMemberList(session, searchCri);
			
			pageMaker = new PageMaker();
			pageMaker.setCri(searchCri);
			pageMaker.setTotalCount(memberDAO.selectSearchMemberListCount(session, searchCri));
			
			dataMap = new HashMap<String, Object>();
			dataMap.put("memberList", memberList);
			dataMap.put("pageMaker", pageMaker);
			
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return dataMap;
	}
	
	//회원 상세내용
	@Override
	public MemberVO getMember(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			MemberVO memberVO = memberDAO.selectMemberById(session, id);
			return memberVO;
		} finally {
			session.close();
		}
	}
	
	//회원 수정
	@Override
	public void modifyMember(MemberVO memberVO) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();		
		
		try {
			memberDAO.updateMember(session, memberVO);
		} finally {
			session.close();
		}
	}
	
	//회원 삭제
	@Override
	public void removeMember(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			memberDAO.deleteMember(session, id);
		} finally {
			session.close();
		}
	}
	
	//회원 추가
	@Override
	public void registMember(MemberVO memberVO) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			memberDAO.insertMember(session, memberVO);
		} finally {
			session.close();
		}
	}
	
	//회원 상태 변경
	@Override
	public void enabledMember(String id, int enabled) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			memberDAO.enabledMember(session, id, enabled);
		} finally {
			session.close();
		}
	}
	
	//회원 로그인
	@Override
	public void loginMember(String id, String pwd) throws NotFoundIDException, InvalidPasswordException, SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			MemberVO memberVO = memberDAO.selectMemberById(session, id);
			
			if(memberVO == null)
				throw new NotFoundIDException();
			if(!pwd.equals(memberVO.getPwd()))
				throw new InvalidPasswordException();
		} finally {
			session.close();
		}
	}
}
