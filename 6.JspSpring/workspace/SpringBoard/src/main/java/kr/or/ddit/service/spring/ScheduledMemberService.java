package kr.or.ddit.service.spring;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public interface ScheduledMemberService extends MemberService {
	
	//사진 삭제
	public MemberVO getMemberByPicture(String picture) throws SQLException;
}
