package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.AttachVO;

public interface AttachDAOBean {
	
	//첨부파일 리스트 출력
	public List<AttachVO> selectAttachByPno(int pno) throws SQLException;
	
	//첨부파일 출력
	public AttachVO selectAttachByAno(int ano) throws SQLException;
	
	//첨부파일 삽입
	public void insertAttach(AttachVO attachVO) throws SQLException;
	
	//첨부파일 삭제
	public void deleteAttach(int ano) throws SQLException;
	
	//첨부파일 전체삭제(첨부파일 게시물 삭제)
	public void deleteAllAttach(int pno) throws SQLException;
}
