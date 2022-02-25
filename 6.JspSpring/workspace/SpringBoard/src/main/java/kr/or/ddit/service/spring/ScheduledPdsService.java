package kr.or.ddit.service.spring;

import java.sql.SQLException;

import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public interface ScheduledPdsService extends PdsService {
	
	//사진 삭제
	public PdsVO getPdsByImage(String imageFile) throws SQLException;
}
