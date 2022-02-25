package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dao.NoticeDAO;
import com.jsp.dto.NoticeVO;

public class NoticeDAOImplTemplate implements NoticeDAOBean {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	private NoticeDAO noticeDAO;
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	//공지게시판 출력
	@Override
	public List<NoticeVO> selectSearchNoticeList(SearchCriteria cri) throws SQLException {
		return noticeDAO.selectSearchNoticeList(session, cri);
	}
	
	//공지게시물 숫자
	@Override
	public int selectSearchNoticeListCount(SearchCriteria cri) throws SQLException {
		return noticeDAO.selectSearchNoticeListCount(session, cri);
	}
	
	//중요게시물 출력
	@Override
	public List<NoticeVO> selectPointNoticeList() throws SQLException {
		return noticeDAO.selectPointNoticeList(session);
	}
	
	//공지사항 조회
	@Override
	public NoticeVO selectNoticeByNno(int nno) throws SQLException {
		return noticeDAO.selectNoticeByNno(session, nno);
	}
	
	//사진 삭제
	@Override
	public NoticeVO selectNoticeByImage(String imageFile) throws SQLException {
		return session.selectOne("Notice-Mapper.selectNoticeByImage", imageFile);
	}
	
	//공지사항 조회수 증가
	@Override
	public void increaseViewCount(int nno) throws SQLException {
		noticeDAO.increaseViewCount(session, nno);
	}
	
	//시퀀스 가져오기
	@Override
	public int selectNoticeSequenceNextValue() throws SQLException {
		return noticeDAO.selectNoticeSequenceNextValue(session);
	}

	//공지사항 작성
	@Override
	public void insertNotice(NoticeVO noticeVO) throws SQLException {
		noticeDAO.insertNotice(session, noticeVO);
	}
	
	//공지사항 수정
	@Override
	public void updateNotice(NoticeVO noticeVO) throws SQLException {
		noticeDAO.updateNotice(session, noticeVO);
	}	
	
	//공지사항 삭제
	@Override
	public void deleteNotice(int nno) throws SQLException {
		noticeDAO.deleteNotice(session, nno);
	}
}
