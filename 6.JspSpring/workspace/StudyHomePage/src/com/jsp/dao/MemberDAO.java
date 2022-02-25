package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;

public interface MemberDAO {
	
	//회원리스트 출력
	public List<MemberVO> selectSearchMemberList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	//회원 숫자
	public int selectSearchMemberListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	//회원 상세내용
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException;
	
	//회원 수정
	public void updateMember(SqlSession session, MemberVO memberVO) throws SQLException;
	
	//회원 삭제
	public void deleteMember(SqlSession session, String id) throws SQLException;
	
	//회원 추가
	public void insertMember(SqlSession session, MemberVO memberVO) throws SQLException;
	
	//회원 상태 변경
	public void enabledMember(SqlSession session, String id, int enabled) throws SQLException;
}
