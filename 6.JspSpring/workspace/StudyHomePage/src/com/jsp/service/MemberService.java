package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;

public interface MemberService {
	
	//회원리스트 출력
	public Map<String, Object> getMemberListPage(Criteria cri) throws SQLException;
	
	//회원 상세내용
	public MemberVO getMember(String id) throws SQLException;
	
	//회원 수정
	public void modifyMember(MemberVO memberVO) throws SQLException;
	
	//회원 삭제
	public void removeMember(String id) throws SQLException;
	
	//회원 추가
	public void registMember(MemberVO memberVO) throws SQLException;
	
	//회원 상태 변경
	public void enabledMember(String id, int enabled) throws SQLException;
	
	//회원 로그인
	public void loginMember(String id, String pwd) throws NotFoundIDException, InvalidPasswordException, SQLException;
}
