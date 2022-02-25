package kr.or.ddit.service.spring;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

import kr.or.ddit.dao.spring.NoticeDAOBean;

public class NoticeServiceImpl implements NoticeService {
	
	private NoticeDAOBean noticeDAOBean;
	public void setNoticeDAOBean(NoticeDAOBean noticeDAOBean) {
		this.noticeDAOBean = noticeDAOBean;
	}
	
	//공지게시판 출력
	@Override
	public Map<String, Object> getNoticeList(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<NoticeVO> noticeList = noticeDAOBean.selectSearchNoticeList(cri);
		
		int totalCount = noticeDAOBean.selectSearchNoticeListCount(cri);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("noticeList", noticeList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	
	//중요게시물 출력
	@Override
	public List<NoticeVO> getPointList() throws SQLException {
		List<NoticeVO> pointList = noticeDAOBean.selectPointNoticeList();
		return pointList;
	}
	
	//공지사항 조회(조회수 증가O)
	@Override
	public NoticeVO getNotice(int nno) throws SQLException {
		noticeDAOBean.increaseViewCount(nno);
		//if(true) throw new SQLException();
		NoticeVO noticeVO = noticeDAOBean.selectNoticeByNno(nno);
		return noticeVO;
	}
	
	//수정화면 상세(조회수 증가X)
	@Override
	public NoticeVO getNoticeForModify(int nno) throws SQLException {
		NoticeVO boardVO = noticeDAOBean.selectNoticeByNno(nno);
		return boardVO;
	}
	
	//공지사항 작성
	@Override
	public void registNotice(NoticeVO noticeVO) throws SQLException {
		int nno = noticeDAOBean.selectNoticeSequenceNextValue();
		noticeVO.setNno(nno);
		noticeDAOBean.insertNotice(noticeVO);
	}
	
	//공지사항 수정
	@Override
	public void modifyNotice(NoticeVO noticeVO) throws SQLException {
		noticeDAOBean.updateNotice(noticeVO);
	}
	
	//공지사항 삭제
	@Override
	public void removeNotice(int nno) throws SQLException {
		noticeDAOBean.deleteNotice(nno);
	}
}
