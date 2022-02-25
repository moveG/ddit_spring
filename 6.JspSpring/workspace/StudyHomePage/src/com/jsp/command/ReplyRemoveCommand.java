package com.jsp.command;

import com.jsp.dto.ReplyVO;

public class ReplyRemoveCommand {
	private String bno;		//글 번호
	private String rno;		//댓글 번호
	private String page;	//댓글 현재페이지 번호
	
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	public int getRnoInt() {
		return Integer.parseInt(rno);
	}
	public int getBnoInt() {
		return Integer.parseInt(bno);
	}
	public int getPageInt() {
		return Integer.parseInt(page);
	}
}
