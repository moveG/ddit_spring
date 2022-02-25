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

public class PdsDetailAction implements Action {

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/pds/detail";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		String from = request.getParameter("from");
		
		try {
			PdsVO pdsVO = null;
			if(from != null && from.equals("modify")) {
				pdsVO = pdsService.getPdsForModify(pno);
			}else {
				pdsVO = pdsService.getPds(pno);
			}
			
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
