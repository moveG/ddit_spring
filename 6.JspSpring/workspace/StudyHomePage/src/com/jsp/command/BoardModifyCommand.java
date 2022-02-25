package com.jsp.command;

import java.util.Date;

import com.jsp.dto.BoardVO;

public class BoardModifyCommand {
	private String bno;			//게시물 번호
	private String title;		//제목
	private String writer;		//작성자
	private String content;		//내용
	
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public BoardVO toBoardVO() {
		BoardVO board = new BoardVO();
		
		//BoardVO세팅
		board.setBno(Integer.parseInt(this.bno));
		board.setTitle(this.title);
		board.setWriter(this.writer);
		board.setContent(this.content);
		board.setUpdateDate(new Date());
		
		return board;
	}	
}
