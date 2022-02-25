package com.jsp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;

public interface MemberService {
	
	public List<MemberVO> getMemberList() throws SQLException;
	
	public List<MemberVO> getMemberList(Criteria cri) throws SQLException;

	public Map<String, Object> getMemberListPage(Criteria cri) throws SQLException;
	
	public MemberVO getMember(String id) throws SQLException;
	
	public void registMember(MemberVO member) throws SQLException;
}
