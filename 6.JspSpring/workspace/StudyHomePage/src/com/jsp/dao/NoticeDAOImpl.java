package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.NoticeVO;

public class NoticeDAOImpl implements NoticeDAO {
	
	//공지게시판 출력
	@Override
	public List<NoticeVO> selectSearchNoticeList(SqlSession session, SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<NoticeVO> noticeList = session.selectList("Notice-Mapper.selectSearchNoticeList", cri, rowBounds);
		return noticeList;
	}
	
	//공지게시물 숫자
	@Override
	public int selectSearchNoticeListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int cnt = session.selectOne("Notice-Mapper.selectSearchNoticeListCount", cri);
		return cnt;
	}
	
	//중요게시물 출력
	@Override
	public List<NoticeVO> selectPointNoticeList(SqlSession session) throws SQLException {
		List<NoticeVO> pointList = session.selectList("Notice-Mapper.selectPointNoticeList");
		return pointList;
	}
	
	//공지사항 조회
	@Override
	public NoticeVO selectNoticeByNno(SqlSession session, int nno) throws SQLException {
		NoticeVO noticeVO = session.selectOne("Notice-Mapper.selectNoticeByNno", nno);
		return noticeVO;
	}
	
	//공지사항 조회수 증가
	@Override
	public void increaseViewCount(SqlSession session, int nno) throws SQLException {
		session.update("Notice-Mapper.increaseViewCount", nno);
	}

	//시퀀스 가져오기
	@Override
	public int selectNoticeSequenceNextValue(SqlSession session) throws SQLException {
		int seq_num = session.selectOne("Notice-Mapper.selectNoticeSequenceNextValue");
		return seq_num;
	}
	
	//공지사항 작성
	@Override
	public void insertNotice(SqlSession sesssion, NoticeVO noticeVO) throws SQLException {
		sesssion.update("Notice-Mapper.insertNotice", noticeVO);
	}
	
	//공지사항 수정
	@Override
	public void updateNotice(SqlSession session, NoticeVO noticeVO) throws SQLException {
		session.update("Notice-Mapper.updateNotice", noticeVO);
	}	
	
	//공지사항 삭제
	@Override
	public void deleteNotice(SqlSession session, int nno) throws SQLException {
		session.update("Notice-Mapper.deleteNotice", nno);
	}
}
