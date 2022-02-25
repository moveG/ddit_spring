package com.jsp.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	//회원리스트 출력
	@Override
	public List<MemberVO> selectSearchMemberList(SqlSession session, SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<MemberVO> memberList
			= session.selectList("Member-Mapper.selectSearchMemberList", cri, rowBounds);
		return memberList;
	}
	
	//회원 숫자
	@Override
	public int selectSearchMemberListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count = 0; 
		count = session.selectOne("Member-Mapper.selectSearchMemberListCount", cri);
		return count;
	}
	
	//회원 상세내용
	@Override
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException {
		MemberVO memberVO = session.selectOne("Member-Mapper.selectMemberById", id);
		return memberVO;
	}
	
	//회원 수정
	@Override
	public void updateMember(SqlSession session, MemberVO memberVO) throws SQLException {
		session.update("Member-Mapper.updateMember", memberVO);
	}
	
	//회원 삭제
	@Override
	public void deleteMember(SqlSession session, String id) throws SQLException {
		session.update("Member-Mapper.deleteMember", id);
	}
	
	//회원 추가
	@Override
	public void insertMember(SqlSession session, MemberVO memberVO) throws SQLException {
		session.update("Member-Mapper.insertMember", memberVO);
	}
	
	//회원 상태 변경
	@Override
	public void enabledMember(SqlSession session, String id, int enabled) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("id", id);
		dataMap.put("enabled", enabled);
		
		session.update("Member-Mapper.enabledMember", dataMap);
	}
}
