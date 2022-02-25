package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.NoticeDAO;
import com.jsp.dto.NoticeVO;

public class NoticeServiceImpl implements NoticeService {

	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private NoticeDAO noticeDAO;
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	//공지게시판 출력
	@Override
	public Map<String, Object> getNoticeList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			List<NoticeVO> noticeList = noticeDAO.selectSearchNoticeList(session, cri);
			
			int totalCount = noticeDAO.selectSearchNoticeListCount(session, cri);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);

			dataMap.put("noticeList", noticeList);
			dataMap.put("pageMaker", pageMaker);

			return dataMap;
		} finally {
			session.close();
		}
	}
	
	//중요게시물 출력
	@Override
	public List<NoticeVO> getPointList() throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			List<NoticeVO> pointList = noticeDAO.selectPointNoticeList(session);
			
			return pointList;
		} finally {
			session.close();
		}
	}
	
	//공지사항 조회(조회수 증가O)
	@Override
	public NoticeVO getNotice(int nno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			NoticeVO board = noticeDAO.selectNoticeByNno(session, nno);
			noticeDAO.increaseViewCount(session, nno);
			return board;
		} finally {
			session.close();
		}
	}
	
	//수정화면 상세(조회수 증가X)
	@Override
	public NoticeVO getNoticeForModify(int nno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			NoticeVO board = noticeDAO.selectNoticeByNno(session, nno);
			return board;
		} finally {
			session.close();
		}
	}
	
	//공지사항 작성
	@Override
	public void registNotice(NoticeVO noticeVO) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int nno = noticeDAO.selectNoticeSequenceNextValue(session);
			noticeVO.setNno(nno);
			noticeDAO.insertNotice(session, noticeVO);
		} finally {
			session.close();
		}
	}
	
	//공지사항 수정
	@Override
	public void modifyNotice(NoticeVO noticeVO) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			noticeDAO.updateNotice(session, noticeVO);
		} finally {
			session.close();
		}
	}
	
	//공지사항 삭제
	@Override
	public void removeNotice(int nno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			noticeDAO.deleteNotice(session, nno);
		} finally {
			session.close();
		}
	}
}
