package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.NoticeVO;

public interface NoticeDAO {
	
	//공지게시판 출력
	public List<NoticeVO> selectSearchNoticeList(SqlSession session, SearchCriteria cri) throws SQLException;
		
	//공지게시물 숫자
	public int selectSearchNoticeListCount(SqlSession session, SearchCriteria cri) throws SQLException;
		
	//중요게시물 출력
	public List<NoticeVO> selectPointNoticeList(SqlSession session) throws SQLException;
	
	//공지사항 조회
	public NoticeVO selectNoticeByNno(SqlSession session, int nno) throws SQLException;
	
	//공지사항 조회수 증가
	public void increaseViewCount(SqlSession session, int nno) throws SQLException;
	
	//시퀀스 가져오기
	public int selectNoticeSequenceNextValue(SqlSession session) throws SQLException;
	
	//공지사항 작성
	public void insertNotice(SqlSession sesssion, NoticeVO noticeVO) throws SQLException;
	
	//공지사항 수정
	public void updateNotice(SqlSession session, NoticeVO noticeVO) throws SQLException;
	
	//공지사항 삭제
	public void deleteNotice(SqlSession session, int nno) throws SQLException;
}
