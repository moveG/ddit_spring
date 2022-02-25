package com.jsp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;
import com.jsp.dto.PagingVO;

public interface IMemberDao {
	
	//회원리스트 출력, 회원 검색
	public List<MemberVO> listMember(SqlSession session, PagingVO pagingVO) throws Exception;
	
	//회원리스트 숫자
	public int countMember(SqlSession session, PagingVO pagingVO) throws Exception;
	
	//회원 상세내용
	public MemberVO detailMember(SqlSession session, String id) throws Exception;
	
	//회원 추가
	public int insertMember(SqlSession session, MemberVO vo) throws Exception;
	
	//회원 수정
	public int updateMember(SqlSession session, MemberVO vo) throws Exception;
	
	//회원 삭제
	public int deleteMember(SqlSession session, String id) throws Exception;
	
	//아이디 중복 검사
	public int idCheck(SqlSession session, String id) throws Exception;
}
