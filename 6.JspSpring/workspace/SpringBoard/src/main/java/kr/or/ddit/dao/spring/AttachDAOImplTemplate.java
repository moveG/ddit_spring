package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dao.AttachDAO;
import com.jsp.dto.AttachVO;

public class AttachDAOImplTemplate implements AttachDAOBean {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	
	//첨부파일 리스트 출력
	@Override
	public List<AttachVO> selectAttachByPno(int pno) throws SQLException {
		return attachDAO.selectAttachByPno(session, pno);
	}
	
	//첨부파일 출력
	@Override
	public AttachVO selectAttachByAno(int ano) throws SQLException {
		return attachDAO.selectAttachByAno(session, ano);
	}
	
	//첨부파일 삽입
	@Override
	public void insertAttach(AttachVO attachVO) throws SQLException {
		attachDAO.insertAttach(session, attachVO);
	}
	
	//첨부파일 삭제
	@Override
	public void deleteAttach(int ano) throws SQLException {
		attachDAO.deleteAttach(session, ano);		
	}
	
	//첨부파일 전체삭제(첨부파일 게시물 삭제)
	@Override
	public void deleteAllAttach(int pno) throws SQLException {
		attachDAO.deleteAllAttach(session, pno);		
	}
}
