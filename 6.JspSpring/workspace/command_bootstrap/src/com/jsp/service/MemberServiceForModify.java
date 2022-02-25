package com.jsp.service;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;

public interface MemberServiceForModify extends MemberService {
	
	//회원 수정
	public void modifyMember(MemberVO member) throws SQLException;
	
	//회원 삭제
	public void removeMember(String id) throws SQLException;
	
	//회원 상태 변경
	public void enabledMember(String id, int enabled) throws SQLException;
	
	//회원 로그인
	public void login(String id, String pwd) throws NotFoundIDException
												  , InvalidPasswordException
												  , SQLException;
}
