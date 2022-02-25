package com.jsp.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;

public class CheckEnabledMemberFilter implements Filter {
	
	private Set<String> checkUrlsSet = new HashSet<String>();
	
	public void init(FilterConfig fConfig) throws ServletException {
		String checkUrlsParam = fConfig.getInitParameter("checkUrl");
		
		StringTokenizer token = new StringTokenizer(checkUrlsParam.trim(), ",", true);
		
		while(token.hasMoreTokens()) {
			checkUrlsSet.add(token.nextToken());
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		String requestURI = httpReq.getRequestURI();
		String requestURIArray[] = requestURI.split("/");
		String endPoint = requestURIArray[requestURIArray.length - 1];
		String result = "";
		
		if(checkUrlsSet.contains(endPoint)) {
			//web.xml에서 가져온 init-param에 endPoint와 동인한 것이 있는지 확인하여 true/false를 반환
			HttpSession session = httpReq.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("loginUser");
			if(memberVO == null) {	//로그인 필요
				result += "<script>" 
					    + "alert('로그인이 필요합니다.');"
						+ "location.href='" + httpReq.getContextPath() + "/common/login';"
						+ "</script>";
			}else {
				int enabled = memberVO.getEnabled();
				
				switch(enabled) {
				case 1:	//활성 상태
					chain.doFilter(request, response);
					return;
				case 0:	//비활성 사태
					result += "<script>" 
						    + "alert('비활성 상태로 사용이 제한됩니다.');";
					
					String cw = request.getParameter("cw");
					
					if(cw != null && cw.equals("t")) {
						result += "window.close();";
					}else {
						result += "history.go(-1);";
					}
					result += "</script>";
					break;
				}
			}
			httpResp.setContentType("text/html;charset=utf-8");
			PrintWriter out = httpResp.getWriter();
			out.print(result);
		}else {
			chain.doFilter(request, response);	//regist, modify, remove, enabled가 아닌 경우
		}
	}

	public void destroy() {}
}
