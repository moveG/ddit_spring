package com.java.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dto.MemberVO;

@WebServlet("/inObject")
public class InObjectServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();
		
		request.setAttribute("msg", "어서 모이자(2. request)");
		session.setAttribute("msg", "선생님이 우리를(3. session)");
		application.setAttribute("msg", "기다리신다(4. application)");
		
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("girlGroup", "티아라");
		request.setAttribute("dataMap", dataMap);
		
		MemberVO member = new MemberVO();
		member.setId("mimi");
		member.setPwd("mimi");
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/inObject.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
