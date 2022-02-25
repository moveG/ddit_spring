package com.jsp.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;

public class MemberServiceForModifyImpl extends SearchMemberServiceImpl 
										implements MemberServiceForModify {

	@Override
	public void modifyMember(MemberVO member) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			memberDAO.updateMember(session, member);
		} finally {
			session.close();
		}
	}

	@Override
	public void removeMember(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			memberDAO.deleteMember(session, id);
		} finally {
			session.close();
		}
	}

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
	public void login(String id, String pwd) throws NotFoundIDException, InvalidPasswordException, SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			MemberVO member = memberDAO.selectMemberById(session, id);
			if(member == null)
				throw new NotFoundIDException();
			if(!pwd.equals(member.getPwd()))
				throw new InvalidPasswordException();
		} finally {
			session.close();
		}
	}
}
