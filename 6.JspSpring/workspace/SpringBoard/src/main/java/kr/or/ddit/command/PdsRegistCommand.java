package kr.or.ddit.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jsp.dto.PdsVO;

public class PdsRegistCommand {
	private String title;					//제목
	private String content;					//내용
	private String writer;					//작성자
	private List<MultipartFile> uploadFile;	//업로드 파일
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public PdsVO toPdsVO() {
		PdsVO pdsVO = new PdsVO();
		pdsVO.setTitle(this.title);
		pdsVO.setContent(this.content);
		pdsVO.setWriter(this.writer);
		
		return pdsVO;
	}
}
