package com.jsp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.IMemberDao;
import com.jsp.dto.MemberVO;
import com.jsp.dto.PagingVO;

public class MemberServiceImpl implements IMemberService{
	
	private SqlSessionFactory sqlSessionFactory;
	private IMemberDao memberDao;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	public void setMemberDao(IMemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	//회원리스트 출력, 회원 검색
	@Override
	public List<MemberVO> listMember(PagingVO pagingVO) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		List<MemberVO> list = null;
		
		try {
			list = memberDao.listMember(session, pagingVO);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return list;
	}
	
	//회원리스트 숫자
	@Override
	public int countMember(PagingVO pagingVO) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		int cnt = 0;
		
		try {
			cnt = memberDao.countMember(session, pagingVO);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return cnt;
	}
	
	

	//회원 상세내용
	@Override
	public MemberVO detailMember(String id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		MemberVO vo = null;
		
		try {
			vo = memberDao.detailMember(session, id);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return vo;
	}

	//회원 추가
	@Override
	public int insertMember(MemberVO vo) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		int cnt = 0;
		
		try {
			cnt = memberDao.insertMember(session, vo);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return cnt;
	}

	//회원 수정
	@Override
	public int updateMember(MemberVO vo) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		int cnt = 0;
		
		try {
			cnt = memberDao.updateMember(session, vo);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return cnt;
	}

	//회원 삭제
	@Override
	public int deleteMember(String id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		int cnt = 0;
		
		try {
			cnt = memberDao.deleteMember(session, id);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return cnt;
	}
	
	//아이디 중복 검사
	@Override
	public int idCheck(String id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		int cnt = 0;
		
		try {
			cnt = memberDao.idCheck(session, id);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return cnt;
	}	
}
