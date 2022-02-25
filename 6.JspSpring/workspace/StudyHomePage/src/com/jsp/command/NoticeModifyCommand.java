package com.jsp.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jsp.dto.NoticeVO;

public class NoticeModifyCommand {
	private String nno;			//번호
	private String title;		//제목
	private String writer;		//작성자
	private String content;		//내용
	private String point;		//중요도
	private String startDate;	//게시 시작일
	private String endDate;		//게시 종료일
	
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
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public NoticeVO toNoticeVO() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		NoticeVO noticeVO = new NoticeVO();
		
		//NoticeVO 세팅
		try {
			Date dateStartDate = sdf.parse(startDate);
			Date dateEndDate = null;
			
			if(endDate == "") {
				dateEndDate = null;
			}else {
				dateEndDate = sdf.parse(endDate);
			}
			noticeVO.setStartDate(dateStartDate);
			noticeVO.setEndDate(dateEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		noticeVO.setTitle(title);
		noticeVO.setWriter(writer);
		noticeVO.setContent(content);
		noticeVO.setNno(Integer.parseInt(nno));
		noticeVO.setPoint(Integer.parseInt(point));
		
		return noticeVO;
	}
}
