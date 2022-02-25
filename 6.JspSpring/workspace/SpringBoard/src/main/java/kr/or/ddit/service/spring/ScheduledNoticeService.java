package kr.or.ddit.service.spring;

import java.sql.SQLException;

import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public interface ScheduledNoticeService extends NoticeService {
	
	//사진 삭제
	public NoticeVO getNoticeByImage(String imageFile) throws SQLException;
}
