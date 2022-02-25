package kr.or.ddit.service.spring;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

import kr.or.ddit.dao.spring.AttachDAOBean;
import kr.or.ddit.dao.spring.PdsDAOBean;

public class PdsServiceImpl implements PdsService {
	
	private PdsDAOBean pdsDAOBean;
	public void setPdsDAOBean(PdsDAOBean pdsDAOBean) {
		this.pdsDAOBean = pdsDAOBean;
	}
	
	private AttachDAOBean attachDAOBean;
	public void setAttachDAOBean(AttachDAOBean attachDAOBean) {
		this.attachDAOBean = attachDAOBean;
	}
	
	//자료게시판 출력
	@Override
	public Map<String, Object> getPdsList(SearchCriteria cri) throws SQLException {
		List<PdsVO> pdsList = pdsDAOBean.selectSearchPdsList(cri);

		if (pdsList != null) {
			for (PdsVO pdsVO : pdsList) {
				addAttachList(pdsVO);
			}
		}

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(pdsDAOBean.selectSearchPdsListCount(cri));

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("pdsList", pdsList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	
	//자료 게시물 출력(조회수 증가)
	@Override
	public PdsVO getPds(int pno) throws SQLException {
		PdsVO pdsVO = pdsDAOBean.selectPdsByPno(pno);
		pdsDAOBean.increaseViewCount(pno);
		addAttachList(pdsVO);
		return pdsVO;
	}
		
	//자료 게시물 출력
	@Override
	public PdsVO getPdsForModify(int pno) throws SQLException {
		PdsVO pdsVO = pdsDAOBean.selectPdsByPno(pno);
		addAttachList(pdsVO);	
		return pdsVO;
	}
	
	//자료 게시물 작성
	@Override
	public void registPds(PdsVO pdsVO) throws SQLException {
		int pno = pdsDAOBean.selectPdsSequenceNextValue();

		pdsVO.setPno(pno);
		pdsDAOBean.insertPds(pdsVO);
			
		if(pdsVO.getAttachList() != null) {
			for(AttachVO attachVO : pdsVO.getAttachList()) {
				attachVO.setPno(pno);
				attachVO.setAttacher(pdsVO.getWriter());
				attachDAOBean.insertAttach(attachVO);
			}
		}
	}
	
	//자료 게시물 수정
	@Override
	public void modifyPds(PdsVO pdsVO) throws SQLException {
		pdsDAOBean.updatePds(pdsVO);
		
		if (pdsVO.getAttachList() != null) {
			for (AttachVO attachVO : pdsVO.getAttachList()) {
				attachVO.setPno(pdsVO.getPno());
				attachVO.setAttacher(pdsVO.getWriter());
				attachDAOBean.insertAttach(attachVO);
			}
		}
		
	}
	
	//자료 게시물 삭제
	@Override
	public void removePds(int pno) throws SQLException {
		attachDAOBean.deleteAllAttach(pno);
		pdsDAOBean.deletePds(pno);
	}
	
	//첨부파일 조회
	@Override
	public AttachVO getAttachByAno(int ano) throws SQLException {
		AttachVO attach = attachDAOBean.selectAttachByAno(ano);
		return attach;
	}
	
	//파일정보 삭제
	@Override
	public void removeAttachByAno(int ano) throws SQLException {
		attachDAOBean.deleteAttach(ano);
	}
	
	//첨부파일 리스트 조회
	private void addAttachList(PdsVO pdsVO) throws SQLException {
		if (pdsVO == null) {
			return;
		}

		int pno = pdsVO.getPno();
		List<AttachVO> attachList = attachDAOBean.selectAttachByPno(pno);

		pdsVO.setAttachList(attachList);
	}
}
