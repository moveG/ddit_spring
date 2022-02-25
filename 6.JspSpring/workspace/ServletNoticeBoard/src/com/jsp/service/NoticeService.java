package com.jsp.service;

import java.util.List;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.dto.NoticeVO;

public interface NoticeService {
	
	//공지게시판 출력
	public Map<String, Object> getNoticeListPage(Criteria cri) throws Exception;
	
	//중요게시물 출력
	public List<NoticeVO> getPointListPage() throws Exception;
	
	//공지사항 조회
	public NoticeVO getNotice(int nNo) throws Exception;
	
	//공지사항 삭제
	public void removeNotice(int nNo) throws Exception;
	
	//공지사항 수정
	public void modifyNotice(NoticeVO noticeVO) throws Exception;
	
	//공지사항 작성
	public void registNotice(NoticeVO noticeVO) throws Exception;
	
	//공지사항 작성자 조회
	public List<String> getNoticeWriter() throws Exception;
	
	//공지사항 조회수 증가
	public void plusViews(int nNo) throws Exception;
}
