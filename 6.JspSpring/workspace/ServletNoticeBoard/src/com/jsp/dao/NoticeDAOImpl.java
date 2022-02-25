package com.jsp.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.NoticeVO;

public class NoticeDAOImpl implements NoticeDAO {
	
	//공지게시판 출력
	@Override
	public List<NoticeVO> selectSearchNoticeList(SqlSession session, SearchCriteria cri) throws Exception {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<NoticeVO> noticeList = session.selectList("Notice-Mapper.selectSearchNoticeList", cri, rowBounds);
		return noticeList;
	}

	//공지게시물 숫자
	@Override
	public int selectSearchNoticeListCount(SqlSession session, SearchCriteria cri) throws Exception {
		int cnt = session.selectOne("Notice-Mapper.selectSearchNoticeListCount", cri);
		return cnt;
	}
	
	//중요게시물 출력
	@Override
	public List<NoticeVO> selectPointNoticeList(SqlSession session) throws Exception {
		List<NoticeVO> pointList = session.selectList("Notice-Mapper.selectPointNoticeList");
		return pointList;
	}

	//공지사항 조회
	@Override
	public NoticeVO selectNoticeByNno(SqlSession session, int nNo) throws Exception {
		NoticeVO noticeVO = session.selectOne("Notice-Mapper.selectNoticeByNno", nNo);
		return noticeVO;
	}
	
	//공지사항 삭제
	@Override
	public void deleteNotice(SqlSession session, int nNo) throws Exception {
		session.update("Notice-Mapper.deleteNotice", nNo);
	}
	
	//공지사항 수정
	@Override
	public void updateNotice(SqlSession session, NoticeVO noticeVO) throws Exception {
		session.update("Notice-Mapper.updateNotice", noticeVO);
	}
	
	//공지사항 작성
	@Override
	public void insertNotice(SqlSession sesssion, NoticeVO noticeVO) throws Exception {
		sesssion.update("Notice-Mapper.insertNotice", noticeVO);
	}

	//공지사항 작성자 조회
	@Override
	public List<String> selectNoticeWriter(SqlSession session) throws Exception {
		List<String> idList = session.selectList("Notice-Mapper.selectNoticeWriter");
		return idList;
	}

	//공지사항 조회수 증가
	@Override
	public void plusViews(SqlSession session, int nNo) throws Exception {
		session.update("Notice-Mapper.plusViews", nNo);
		
	}
}
