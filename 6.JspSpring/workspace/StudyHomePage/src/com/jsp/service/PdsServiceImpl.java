package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.AttachDAO;
import com.jsp.dao.PdsDAO;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;

public class PdsServiceImpl implements PdsService {

	private SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private PdsDAO pdsDAO;
	public void setPdsDAO(PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}

	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	
	//자료게시판 출력
	@Override
	public Map<String, Object> getPdsList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			List<PdsVO> pdsList = pdsDAO.selectSearchPdsList(session, cri);

			if (pdsList != null) {
				for (PdsVO pdsVO : pdsList) {
					addAttachList(session, pdsVO);
				}
			}

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(pdsDAO.selectSearchPdsListCount(session, cri));

			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("pdsList", pdsList);
			dataMap.put("pageMaker", pageMaker);

			return dataMap;
		} finally {
			session.close();
		}
	}
	
	//자료 게시물 출력(조회수 증가)
	@Override
	public PdsVO getPds(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			PdsVO pdsVO = pdsDAO.selectPdsByPno(session, pno);
			pdsDAO.increaseViewCount(session, pno);
			addAttachList(session, pdsVO);

			return pdsVO;
		} finally {
			session.close();
		}
	}
	
	//자료 게시물 출력
	@Override
	public PdsVO getPdsForModify(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			PdsVO pdsVO = pdsDAO.selectPdsByPno(session, pno);
			addAttachList(session, pdsVO);
			
			return pdsVO;
		} finally {
			session.close();
		}
	}
	
	//자료 게시물 작성
	@Override
	public void registPds(PdsVO pdsVO) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			int pno = pdsDAO.selectPdsSequenceNextValue(session);

			pdsVO.setPno(pno);
			pdsDAO.insertPds(session, pdsVO);
			
			if(pdsVO.getAttachList() != null) {
				for(AttachVO attachVO : pdsVO.getAttachList()) {
					attachVO.setPno(pno);
					attachVO.setAttacher(pdsVO.getWriter());
					attachDAO.insertAttach(session, attachVO);
				}
			}
		} finally {
			session.close();
		}
	}
	
	//자료 게시물 수정
	@Override
	public void modifyPds(PdsVO pdsVO) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			pdsDAO.updatePds(session, pdsVO);
			// attachDAO.deleteAllAttach(pds.getPno());
			if (pdsVO.getAttachList() != null)
				for (AttachVO attach : pdsVO.getAttachList()) {
					attach.setPno(pdsVO.getPno());
					attach.setAttacher(pdsVO.getWriter());
					attachDAO.insertAttach(session, attach);
				}
		} finally {
			session.close();
		}
	}
	
	//자료 게시물 삭제
	@Override
	public void removePds(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			attachDAO.deleteAllAttach(session, pno);
			pdsDAO.deletePds(session, pno);
		} finally {
			session.close();
		}
	}

	//첨부파일 조회
	@Override
	public AttachVO getAttachByAno(int ano) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			AttachVO attach = attachDAO.selectAttachByAno(session, ano);
			return attach;
		} finally {
			session.close();
		}
	}
	
	//파일정보 삭제
	@Override
	public void removeAttachByAno(int ano) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			attachDAO.deleteAttach(session, ano);
		} finally {
			session.close();
		}
	}
	
	//첨부파일 리스트 조회
	private void addAttachList(SqlSession session, PdsVO pdsVO) throws SQLException {
		if (pdsVO == null) {
			return;
		}

		int pno = pdsVO.getPno();
		List<AttachVO> attachList = attachDAO.selectAttachByPno(session, pno);

		pdsVO.setAttachList(attachList);
	}
}
