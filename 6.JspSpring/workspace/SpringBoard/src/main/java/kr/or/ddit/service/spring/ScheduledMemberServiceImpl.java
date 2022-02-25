package kr.or.ddit.service.spring;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

import kr.or.ddit.dao.spring.MemberDAOBean;

public class ScheduledMemberServiceImpl extends MemberServiceImpl implements ScheduledMemberService {
	
	private MemberDAOBean memberDAOBeanChild;
	public void setMemberDAOBeanChild(MemberDAOBean memberDAOBeanChild) {
		this.memberDAOBeanChild = memberDAOBeanChild;
	}
	
	//사진 삭제
	@Override
	public MemberVO getMemberByPicture(String picture) throws SQLException {
		MemberVO memberVO = memberDAOBeanChild.selectMemberByPicture(picture);
		return memberVO;
	}
}
