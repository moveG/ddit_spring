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
import com.jsp.service.MemberServiceForModify;

import kr.or.ddit.dao.spring.MemberDAOBean;

public class MemberServiceImpl implements MemberServiceForModify {

	private MemberDAOBean memberDAOBean;
	public void setMemberDAOBean(MemberDAOBean memberDAOBean) {
		this.memberDAOBean = memberDAOBean;
	}

	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		List<MemberVO> memberList = memberDAOBean.selectMemberList();
		return memberList;
	}
	
	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws SQLException {
		List<MemberVO> memberList = memberDAOBean.selectMemberList(cri);
		return memberList;
	}
		
	@Override
	public Map<String, Object> getMemberListPage(Criteria cri) throws SQLException {
		SearchCriteria searchCri = (SearchCriteria) cri;
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(searchCri);
		pageMaker.setTotalCount(memberDAOBean.selectMemberListCount(searchCri));
		
		List<MemberVO> memberList = memberDAOBean.selectMemberList(searchCri);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		dataMap.put("memberList", memberList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	//회원 로그인
	@Override
	public void login(String id, String pwd) throws NotFoundIDException, InvalidPasswordException, SQLException {
		MemberVO member = memberDAOBean.selectMemberById(id);
		if(member == null)
			throw new NotFoundIDException();
		if(!pwd.equals(member.getPwd()))
			throw new InvalidPasswordException();
	}
	
	@Override
	public MemberVO getMember(String id) throws SQLException {
		MemberVO member = memberDAOBean.selectMemberById(id);
		return member;
	}

	@Override
	public void registMember(MemberVO member) throws SQLException {
		memberDAOBean.insertMember(member);
	}
	
	@Override
	public void modifyMember(MemberVO member) throws SQLException {
		memberDAOBean.updateMember(member);		
	}

	@Override
	public void removeMember(String id) throws SQLException {
		memberDAOBean.deleteMember(id);
	}

	@Override
	public void enabledMember(String id, int enabled) throws SQLException {
		memberDAOBean.enabledMember(id, enabled);
	}
}
