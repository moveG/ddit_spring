package com.jsp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.NoticeDAO;
import com.jsp.dto.NoticeVO;

public class NoticeServiceImpl implements NoticeService {
	
	protected SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	protected NoticeDAO noticeDAO;
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	//공지게시판 출력
	@Override
	public Map<String, Object> getNoticeListPage(Criteria cri) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		SearchCriteria searchCri = (SearchCriteria) cri;
		Map<String, Object> dataMap = null;
		
		try {
			List<NoticeVO> noticeList = null;
			PageMaker pageMaker = null;
			
			noticeList = noticeDAO.selectSearchNoticeList(session, searchCri);
			
			pageMaker = new PageMaker();
			pageMaker.setCri(searchCri);
			pageMaker.setTotalCount(noticeDAO.selectSearchNoticeListCount(session, searchCri));
			
			dataMap = new HashMap<String, Object>();
			dataMap.put("noticeList", noticeList);
			dataMap.put("pageMaker", pageMaker);
			
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return dataMap;
	}
	
	//중요게시물 출력
	@Override
	public List<NoticeVO> getPointListPage() throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		List<NoticeVO> pointList = null;
		try {
			pointList = noticeDAO.selectPointNoticeList(session);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return pointList;
	}
	
	//공지사항 조회
	@Override
	public NoticeVO getNotice(int nNo) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		NoticeVO noticeVO = null;
		try {
			noticeVO = noticeDAO.selectNoticeByNno(session, nNo);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return noticeVO;
	}
	
	//공지사항 삭제
	@Override
	public void removeNotice(int nNo) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			noticeDAO.deleteNotice(session, nNo);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}
	
	//공지사항 수정
	@Override
	public void modifyNotice(NoticeVO noticeVO) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			noticeDAO.updateNotice(session, noticeVO);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}
	
	//공지사항 작성
	@Override
	public void registNotice(NoticeVO noticeVO) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			noticeDAO.insertNotice(session, noticeVO);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}
	
	//공지사항 작성자 조회
	@Override
	public List<String> getNoticeWriter() throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		List<String> idList = null;
		try {
			idList = noticeDAO.selectNoticeWriter(session);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
		return idList;
	}

	//공지사항 조회수 증가
	@Override
	public void plusViews(int nNo) throws Exception {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			noticeDAO.plusViews(session, nNo);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}	
	}
}
