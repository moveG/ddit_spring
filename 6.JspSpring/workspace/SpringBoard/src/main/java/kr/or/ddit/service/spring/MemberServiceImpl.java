package kr.or.ddit.service.spring;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.service.MemberService;

import kr.or.ddit.dao.spring.MemberDAOBean;

public class MemberServiceImpl implements MemberService {

	private MemberDAOBean memberDAOBean;
	public void setMemberDAOBean(MemberDAOBean memberDAOBean) {
		this.memberDAOBean = memberDAOBean;
	}
	
	//회원리스트 출력
	@Override
	public Map<String, Object> getMemberListPage(Criteria cri) throws SQLException {
		SearchCriteria searchCri = (SearchCriteria) cri;
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(searchCri);
		pageMaker.setTotalCount(memberDAOBean.selectSearchMemberListCount(searchCri));
		
		List<MemberVO> memberList = memberDAOBean.selectSearchMemberList(searchCri);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		dataMap.put("memberList", memberList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	//회원 상세내용
	@Override
	public MemberVO getMember(String id) throws SQLException {
		MemberVO memberVO = memberDAOBean.selectMemberById(id);
		return memberVO;
	}

	//회원 수정
	@Override
	public void modifyMember(MemberVO memberVO) throws SQLException {
		memberDAOBean.updateMember(memberVO);
	}
	
	//회원 삭제
	@Override
	public void removeMember(String id) throws SQLException {
		memberDAOBean.deleteMember(id);
	}
	
	//회원 추가
	@Override
	public void registMember(MemberVO memberVO) throws SQLException {
		memberDAOBean.insertMember(memberVO);
	}
	
	//회원 상태 변경
	@Override
	public void enabledMember(String id, int enabled) throws SQLException {
		memberDAOBean.enabledMember(id, enabled);
	}
	
	//회원 로그인
	@Override
	public void loginMember(String id, String pwd) throws NotFoundIDException, InvalidPasswordException, SQLException {
		MemberVO memberVO = memberDAOBean.selectMemberById(id);
			
		if(memberVO == null)
			throw new NotFoundIDException();
		if(!pwd.equals(memberVO.getPwd()))
			throw new InvalidPasswordException();
	}
}
