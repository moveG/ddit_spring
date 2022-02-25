package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;

public interface PdsService {
	
	//자료게시판 출력
	public Map<String, Object> getPdsList(SearchCriteria cri) throws SQLException;
	
	//자료 게시물 출력(조회수 증가)
	public PdsVO getPds(int pno) throws SQLException;
	
	//자료 게시물 출력
	public PdsVO getPdsForModify(int pno) throws SQLException;
	
	//자료 게시물 작성
	public void registPds(PdsVO pdsVO) throws SQLException;
	
	//자료 게시물 수정
	public void modifyPds(PdsVO pdsVO) throws SQLException;
	
	//자료 게시물 삭제
	public void removePds(int pno) throws SQLException;
	
	//첨부파일 조회
	public AttachVO getAttachByAno(int ano) throws SQLException;
	
	//파일정보 삭제
	public void removeAttachByAno(int ano) throws SQLException;
}
