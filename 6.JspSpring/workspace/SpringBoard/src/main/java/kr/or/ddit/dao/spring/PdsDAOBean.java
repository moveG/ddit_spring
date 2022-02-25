package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.PdsVO;

public interface PdsDAOBean {

	//자료게시판 출력
	public List<PdsVO> selectSearchPdsList(SearchCriteria cri) throws SQLException;
	
	//자료게시물 숫자
	public int selectSearchPdsListCount(SearchCriteria cri) throws SQLException;
	
	//자료 게시물 출력
	public PdsVO selectPdsByPno(int pno) throws SQLException;
	
	//사진 삭제
	public PdsVO selectPdsByImage(String imageFile) throws SQLException;
	
	//자료 게시물 조회수 증가
	public void increaseViewCount(int pno) throws SQLException;
	
	//시퀀스 가져오기
	public int selectPdsSequenceNextValue() throws SQLException;
	
	//자료 게시물 작성
	public void insertPds(PdsVO pdsVO) throws SQLException;

	//자료 게시물 수정
	public void updatePds(PdsVO pdsVO) throws SQLException;
	
	//자료 게시물 삭제
	public void deletePds(int pno) throws SQLException;	
}
