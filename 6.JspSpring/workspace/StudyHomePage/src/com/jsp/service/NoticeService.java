package com.jsp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.NoticeVO;

public interface NoticeService {
	
	//공지게시판 출력
	public Map<String, Object> getNoticeList(SearchCriteria cri) throws SQLException;
	
	//중요게시물 출력
	public List<NoticeVO> getPointList() throws SQLException;
	
	//공지사항 조회(조회수 증가O)
	public NoticeVO getNotice(int nno) throws SQLException;
	
	//수정화면 상세(조회수 증가X)
	public NoticeVO getNoticeForModify(int nno) throws SQLException;
	
	//공지사항 작성
	public void registNotice(NoticeVO noticeVO) throws SQLException;
	
	//공지사항 수정
	public void modifyNotice(NoticeVO noticeVO)throws SQLException;
	
	//공지사항 삭제
	public void removeNotice(int nno) throws SQLException;
}
