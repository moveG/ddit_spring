package com.jsp.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

public class CheckLoginUserFilter implements Filter {
	
	private List<String> exURLs = new ArrayList<String>();
	
	public void init(FilterConfig fConfig) 
			throws ServletException {
		String excludeURLNames = fConfig.getInitParameter("exclude");
		
		StringTokenizer st = new StringTokenizer(excludeURLNames, ",");
		while(st.hasMoreTokens()) {
			exURLs.add(st.nextToken().trim());
		}
		//System.out.println(exURLs);
	}
	
	private boolean excludeCheck(String url) {
		//true일 경우 제외함
		boolean result = false;
		
		//index.jsp을 판단하기 위해 설정 ex)컨텍스트 패스(ex) localhost/command_bootstrap/)까지만 치고 들어온 경우를 판단
		result = result | url.length() <= 1;
		
		for(String exURL : exURLs) {
			result = result | url.contains(exURL);
		}
		return result;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		//제외할 URL 확인
		String reqUrl = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
		
		if(excludeCheck(reqUrl)) {
			chain.doFilter(request, response);
			return;
		}
		
		//로그인 체크
		HttpSession session = httpReq.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		//로그인 확인
		if(loginUser == null) {	//비로그인 상태
			httpResp.setContentType("text/html;charset=utf-8");
			PrintWriter out = httpResp.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 해주세요.')");
			out.println("if(window.opener){window.close();window.opener.parent.location.href='" + httpReq.getContextPath() + "/';}else{");
			out.println("window.parent.location.href='" + httpReq.getContextPath() + "/';");
			out.println("}");
			out.println("</script>");
			out.close();
		}else {					//로그인 상태
			chain.doFilter(request, response);	//통과
		}
	}
	
	public void destroy() {}
}
