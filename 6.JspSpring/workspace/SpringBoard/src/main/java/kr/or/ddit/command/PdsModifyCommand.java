package kr.or.ddit.command;

import com.jsp.dto.PdsVO;

public class PdsModifyCommand extends PdsRegistCommand{
	private String pno;				//자료실 번호
	private String[] deleteFile;	//삭제할 파일
	
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public String[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(String[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	@Override
	public PdsVO toPdsVO() {
		PdsVO pdsVO = super.toPdsVO();
		pdsVO.setPno(Integer.parseInt(this.pno));
		
		return pdsVO;
	}
}
