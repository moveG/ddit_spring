package com.jsp.action.pds;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.utils.MakeFileName;

public class PdsModifyFormAction implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/pds/modify";
		
		try {
			int pno = Integer.parseInt(request.getParameter("pno"));
			PdsVO pdsVO = pdsService.getPds(pno);
			
			List<AttachVO> renamedAttachList = MakeFileName.parseFileNameFromAttaches(pdsVO.getAttachList(), "\\$\\$");
			pdsVO.setAttachList(renamedAttachList);
			
			request.setAttribute("pdsVO", pdsVO);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;
		}
		return url;
	}
}
