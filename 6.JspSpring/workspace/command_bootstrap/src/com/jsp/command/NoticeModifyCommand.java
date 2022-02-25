package com.jsp.command;

import com.jsp.dto.NoticeVO;

public class NoticeModifyCommand {
	private String nno;			//공지번호
	private String title;		//제목
	private String writer;		//작성자
	private String content;		//내용
	
	public String getNno() {
		return nno;
	}
	public void setNno(String nno) {
		this.nno = nno;
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
	
	public NoticeVO toNoticeVO() {
		NoticeVO notice = new NoticeVO();
		
		//NoticeVO 세팅
		notice.setNno(Integer.parseInt(nno));
		notice.setTitle(title);
		notice.setContent(content);
		
		return notice;
	}
}
