package com.jsp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;
import com.jsp.dto.PagingVO;

public class MemberDaoImpl implements IMemberDao {
	
	//회원리스트 출력, 회원 검색
	@Override
	public List<MemberVO> listMember(SqlSession session, PagingVO pagingVO) throws Exception {
		return session.selectList("Member-Mapper.listMember", pagingVO);
	}
	
	//회원리스트 숫자
	@Override
	public int countMember(SqlSession session, PagingVO pagingVO) throws Exception {
		return (Integer) session.selectOne("Member-Mapper.countMember", pagingVO);
	}
	
	//회원 상세내용
	@Override
	public MemberVO detailMember(SqlSession session, String id) throws Exception {
		return (MemberVO) session.selectOne("Member-Mapper.detailMember", id);
	}
	
	//회원 추가
	@Override
	public int insertMember(SqlSession session, MemberVO vo) throws Exception {
		return session.update("Member-Mapper.insertMember", vo);
	}
	
	//회원 수정
	@Override
	public int updateMember(SqlSession session, MemberVO vo) throws Exception {
		return session.update("Member-Mapper.updateMember", vo);
	}

	//회원 삭제
	@Override
	public int deleteMember(SqlSession session, String id) throws Exception {
		return session.delete("Member-Mapper.deleteMember", id);
	}
	
	//아이디 중복 검사
	@Override
	public int idCheck(SqlSession session, String id) throws Exception {
		return (Integer) session.selectOne("Member-Mapper.idCheck", id);
	}
}
