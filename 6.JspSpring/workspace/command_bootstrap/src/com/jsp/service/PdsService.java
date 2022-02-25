package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;

public interface PdsService {
	
	//리스트 조회
	Map<String, Object> getList(SearchCriteria cri) throws SQLException;
	
	//글 조회
	PdsVO getPds(int pno) throws SQLException;
	
	//글 조회(조회수 증가)
	PdsVO read(int pno) throws SQLException;
	
	//글 작성
	void regist(PdsVO pds) throws SQLException;
	
	//글 수정
	void modify(PdsVO pds) throws SQLException;
	
	//글 삭제
	void remove(int pno) throws SQLException;
	
	//첨부파일 조회
	AttachVO getAttachByAno(int ano) throws SQLException;
	
	//파일정보 삭제
	void removeAttachByAno(int ano) throws SQLException;
}
