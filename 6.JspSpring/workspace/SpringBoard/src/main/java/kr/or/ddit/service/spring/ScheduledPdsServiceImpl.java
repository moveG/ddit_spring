package kr.or.ddit.service.spring;

import java.sql.SQLException;

import com.jsp.dto.PdsVO;

import kr.or.ddit.dao.spring.PdsDAOBean;

public class ScheduledPdsServiceImpl extends PdsServiceImpl implements ScheduledPdsService {

	private PdsDAOBean pdsDAOBeanChild;
	public void setPdsDAOBeanChild(PdsDAOBean pdsDAOBeanChild) {
		this.pdsDAOBeanChild = pdsDAOBeanChild;
	}
	
	//사진 삭제
	@Override
	public PdsVO getPdsByImage(String imageFile) throws SQLException {
		PdsVO pdsVO = pdsDAOBeanChild.selectPdsByImage(imageFile);
		return pdsVO;
	}
}
