package com.jsp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;

public class MockMemberDAOImpl implements MemberDAO{

	@Override
	public List<MemberVO> selectMemberList(SqlSession session) throws SQLException {
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		for(int i = 0; i < 10; i++) {
			memberList.add(new MemberVO("kk" + i, "kk" + i));
		}
		return memberList;
		
	}
	
	@Override
	public List<MemberVO> selectMemberList(SqlSession session, Criteria cri) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int selectMemberListCount(SqlSession session) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> selectMemberList(SqlSession session, SearchCriteria cri) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectMemberListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMember(SqlSession session, MemberVO member) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMember(SqlSession session, MemberVO member) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMember(SqlSession session, String id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enabledMember(SqlSession session, String id, int enabled) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
