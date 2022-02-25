package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;

public interface MemberDAOBean {
	
	//회원리스트 출력
	public List<MemberVO> selectSearchMemberList(SearchCriteria cri) throws SQLException;
	
	//회원 숫자
	public int selectSearchMemberListCount(SearchCriteria cri) throws SQLException;
	
	//회원 상세내용
	public MemberVO selectMemberById(String id) throws SQLException;
	
	//회원 수정
	public void updateMember(MemberVO memberVO) throws SQLException;
	
	//회원 삭제
	public void deleteMember(String id) throws SQLException;
	
	//회원 추가
	public void insertMember(MemberVO memberVO) throws SQLException;
	
	//회원 상태 변경
	public void enabledMember(String id, int enabled) throws SQLException;
	
	//사진 삭제
	public MemberVO selectMemberByPicture(String picture) throws SQLException;
}
