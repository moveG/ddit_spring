package com.jsp.service;

import java.util.List;

import com.jsp.dto.MemberVO;
import com.jsp.dto.PagingVO;

public interface IMemberService {
	
	//회원리스트 출력
	public List<MemberVO> listMember(PagingVO pagingVO) throws Exception;

	//회원리스트 숫자
	public int countMember(PagingVO pagingVO) throws Exception;
	
	//회원 상세내용
	public MemberVO detailMember(String id) throws Exception;
	
	//회원 추가
	public int insertMember(MemberVO vo) throws Exception;
	
	//회원 수정
	public int updateMember(MemberVO vo) throws Exception;
	
	//회원 삭제
	public int deleteMember(String id) throws Exception;
	
	//아이디 중복 검사
	public int idCheck(String id) throws Exception;
}
