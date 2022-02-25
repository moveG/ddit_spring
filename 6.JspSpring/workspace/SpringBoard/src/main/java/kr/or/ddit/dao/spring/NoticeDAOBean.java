package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.NoticeVO;

public interface NoticeDAOBean {
	
	//공지게시판 출력
	public List<NoticeVO> selectSearchNoticeList(SearchCriteria cri) throws SQLException;
		
	//공지게시물 숫자
	public int selectSearchNoticeListCount(SearchCriteria cri) throws SQLException;
		
	//중요게시물 출력
	public List<NoticeVO> selectPointNoticeList() throws SQLException;
	
	//공지사항 조회
	public NoticeVO selectNoticeByNno(int nno) throws SQLException;
	
	//사진 삭제
	public NoticeVO selectNoticeByImage(String imageFile) throws SQLException;
	
	//공지사항 조회수 증가
	public void increaseViewCount(int nno) throws SQLException;
	
	//시퀀스 가져오기
	public int selectNoticeSequenceNextValue() throws SQLException;
	
	//공지사항 작성
	public void insertNotice(NoticeVO noticeVO) throws SQLException;
	
	//공지사항 수정
	public void updateNotice(NoticeVO noticeVO) throws SQLException;
	
	//공지사항 삭제
	public void deleteNotice(int nno) throws SQLException;
}
