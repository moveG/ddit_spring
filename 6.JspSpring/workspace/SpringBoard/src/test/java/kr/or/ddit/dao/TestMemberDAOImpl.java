package kr.or.ddit.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/context/root-context.xml")
@Transactional
public class TestMemberDAOImpl {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void testSelectMember() throws Exception {
		String id = "mimi";
		
		MemberVO memberVO = memberDAO.selectMemberById(sqlSession, id);
		
		Assert.assertEquals(id, memberVO.getId());
	}
	
	@Test
	@Rollback
	public void testInsertMember() throws Exception {
		MemberVO testMemberVO = new MemberVO();
		
		testMemberVO.setId("kaka");
		testMemberVO.setPwd("kaka");
		testMemberVO.setName("kaka");
		testMemberVO.setPhone("000-0000-0000");
		testMemberVO.setEmail("kaka@kaka.net");
		testMemberVO.setPicture("noImage.jpg");
		testMemberVO.setAuthority("ROLE_USER");
		testMemberVO.setAddress("대전");
		
		memberDAO.insertMember(sqlSession, testMemberVO);
		
		MemberVO resultVO = memberDAO.selectMemberById(sqlSession, testMemberVO.getId());
		
		Assert.assertEquals(testMemberVO.getId(), resultVO.getId());
	}
}
