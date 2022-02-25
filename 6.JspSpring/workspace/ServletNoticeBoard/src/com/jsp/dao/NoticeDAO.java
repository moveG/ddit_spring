package com.jsp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.NoticeVO;

public interface NoticeDAO {
	
	//공지게시판 출력
	public List<NoticeVO> selectSearchNoticeList(SqlSession session, SearchCriteria cri) throws Exception;
	
	//공지게시물 숫자
	public int selectSearchNoticeListCount(SqlSession session, SearchCriteria cri) throws Exception;
	
	//중요게시물 출력
	public List<NoticeVO> selectPointNoticeList(SqlSession session) throws Exception;
	
	//공지사항 조회
	public NoticeVO selectNoticeByNno(SqlSession session, int nNo) throws Exception;
	
	//공지사항 삭제
	public void deleteNotice(SqlSession session, int nNo) throws Exception;
	
	//공지사항 수정
	public void updateNotice(SqlSession session, NoticeVO noticeVO) throws Exception;
	
	//공지사항 작성
	public void insertNotice(SqlSession sesssion, NoticeVO noticeVO) throws Exception;
	
	//공지사항 작성자 조회
	public List<String> selectNoticeWriter(SqlSession session) throws Exception;
	
	//공지사항 조회수 증가
	public void plusViews(SqlSession session, int nNo) throws Exception;
}
