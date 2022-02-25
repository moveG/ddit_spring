package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;

public class MemberDAOImplTemplate implements MemberDAOBean {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	//회원리스트 출력
	@Override
	public List<MemberVO> selectSearchMemberList(SearchCriteria cri) throws SQLException {
		return memberDAO.selectSearchMemberList(session, cri);
	}

	//회원 숫자
	@Override
	public int selectSearchMemberListCount(SearchCriteria cri) throws SQLException {
		return memberDAO.selectSearchMemberListCount(session, cri);
	}
	
	//회원 상세내용
	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		return memberDAO.selectMemberById(session, id);
	}
	
	//사진 삭제
	@Override
	public MemberVO selectMemberByPicture(String picture) throws SQLException {
		return session.selectOne("Member-Mapper.selectMemberByPicture", picture);
	}
	
	//회원 수정
	@Override
	public void updateMember(MemberVO memberVO) throws SQLException {
		memberDAO.updateMember(session, memberVO);
	}
	
	//회원 삭제
	@Override
	public void deleteMember(String id) throws SQLException {
		memberDAO.deleteMember(session, id);
	}
	
	//회원 추가
	@Override
	public void insertMember(MemberVO memberVO) throws SQLException {
		memberDAO.insertMember(session, memberVO);
	}
	
	//회원 상태 변경
	@Override
	public void enabledMember(String id, int enabled) throws SQLException {
		memberDAO.enabledMember(session, id, enabled);
	}
}
