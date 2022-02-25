package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;

public class SearchMemberServiceImpl extends MemberServiceImpl {

	@Override
	public Map<String, Object> getMemberListPage(Criteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		SearchCriteria searchCri = (SearchCriteria) cri;
		Map<String, Object> dataMap = null;
		
		try {
			List<MemberVO> memberList = null;
			PageMaker pageMaker = null;
			
			memberList = memberDAO.selectMemberList(session, searchCri);
			pageMaker = new PageMaker();
			pageMaker.setCri(searchCri);
			pageMaker.setTotalCount(memberDAO.selectMemberListCount(session, searchCri));
			
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
	
	@Override
	public MemberVO getMember(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		
		try {
			MemberVO member = memberDAO.selectMemberById(session, id);
			return member;
		} finally {
			session.close();
		}
	}

	@Override
	public void registMember(MemberVO member) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			memberDAO.insertMember(session, member);
		} finally {
			session.close();
		}
	}
}
