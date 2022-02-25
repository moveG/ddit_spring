package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dao.PdsDAO;
import com.jsp.dto.PdsVO;

public class PdsDAOImplTemplate implements PdsDAOBean {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	private PdsDAO pdsDAO;
	public void setPdsDAO(PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}
	
	//자료게시판 출력
	@Override
	public List<PdsVO> selectSearchPdsList(SearchCriteria cri) throws SQLException {
		return pdsDAO.selectSearchPdsList(session, cri);
	}

	//자료게시물 숫자
	@Override
	public int selectSearchPdsListCount(SearchCriteria cri) throws SQLException {
		return pdsDAO.selectSearchPdsListCount(session, cri);
	}
	
	//자료 게시물 출력
	@Override
	public PdsVO selectPdsByPno(int pno) throws SQLException {
		return pdsDAO.selectPdsByPno(session, pno);
	}

	//사진 삭제
	@Override
	public PdsVO selectPdsByImage(String imageFile) throws SQLException {
		return session.selectOne("Pds-Mapper.selectPdsByImage", imageFile);
	}
	
	//자료 게시물 조회수 증가
	@Override
	public void increaseViewCount(int pno) throws SQLException {
		pdsDAO.increaseViewCount(session, pno);
	}

	//시퀀스 가져오기
	@Override
	public int selectPdsSequenceNextValue() throws SQLException {
		return pdsDAO.selectPdsSequenceNextValue(session);
	}
	
	//자료 게시물 작성
	@Override
	public void insertPds(PdsVO pdsVO) throws SQLException {
		pdsDAO.insertPds(session, pdsVO);
	}
	
	//자료 게시물 수정
	@Override
	public void updatePds(PdsVO pdsVO) throws SQLException {
		pdsDAO.updatePds(session, pdsVO);
	}
	
	//자료 게시물 삭제
	@Override
	public void deletePds(int pno) throws SQLException {
		pdsDAO.deletePds(session, pno);
	}
}
