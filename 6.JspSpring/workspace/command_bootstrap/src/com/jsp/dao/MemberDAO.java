package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;

public interface MemberDAO {
	
	//회원 리스트 조회
	List<MemberVO> selectMemberList(SqlSession session) throws SQLException;
	List<MemberVO> selectMemberList(SqlSession session, Criteria cri) throws SQLException;
	List<MemberVO> selectMemberList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	//일반 리스트 전체 개수
	int selectMemberListCount(SqlSession session) throws SQLException;
	//검색 결과의 전체 리스트 개수
	int selectMemberListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	//회원정보 조회
	MemberVO selectMemberById(SqlSession session, String id) throws SQLException;
	
	//회원 등록
	public void insertMember(SqlSession session, MemberVO member) throws SQLException;
	
	//회원 수정
	public void updateMember(SqlSession session, MemberVO member) throws SQLException;
	
	//회원 삭제
	public void deleteMember(SqlSession session, String id) throws SQLException;

	//회원 상태 변경
	public void enabledMember(SqlSession session, String id, int enabled) throws SQLException;
}
