package kr.or.ddit.service.spring;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;
import com.jsp.dto.ReplyVO;
import com.jsp.service.ReplyService;

import kr.or.ddit.dao.spring.MemberDAOBean;
import kr.or.ddit.dao.spring.ReplyDAOBean;

public class ReplyServiceImpl implements ReplyService {
	
	private ReplyDAOBean replyDAOBean;
	public void setReplyDAOBean(ReplyDAOBean replyDAOBean) {
		this.replyDAOBean = replyDAOBean;
	}
	
	private MemberDAOBean memberDAOBean;
	public void setMemberDAOBean(MemberDAOBean memberDAOBean) {
		this.memberDAOBean = memberDAOBean;
	}
	
	//댓글 리스트 출력
	@Override
	public Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<ReplyVO> replyList = replyDAOBean.selectReplyList(bno, cri);
			
		if(replyList != null) for(ReplyVO reply : replyList) {
			MemberVO member = memberDAOBean.selectMemberById(reply.getReplyer());
			reply.setPicture(member.getPicture());
		}
			
		int count = replyDAOBean.selectReplyListCount(bno);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);

		dataMap.put("replyList", replyList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	
	//댓글수 출력
	@Override
	public int getReplyListCount(int bno) throws SQLException {
		int count = replyDAOBean.selectReplyListCount(bno);
		return count;
	}
	
	//댓글 등록
	@Override
	public void registReply(ReplyVO replyVO) throws SQLException {
		int rno = replyDAOBean.selectReplySequenceNextValue();
		replyVO.setRno(rno);

		replyDAOBean.insertReply(replyVO);
	}
	
	//댓글 수정
	@Override
	public void modifyReply(ReplyVO replyVO) throws SQLException {
		replyDAOBean.updateReply(replyVO);		
	}
	
	//댓글 삭제
	@Override
	public void removeReply(int rno) throws SQLException {
		replyDAOBean.deleteReply(rno);
	}
}
