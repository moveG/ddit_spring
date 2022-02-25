package com.java.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class View {
	private static void header(PrintWriter out, String title) {
		out.println("<html>");
		out.println("<!DOCTYPE html>");
		out.println("<head>");
		out.println("<title>");
		out.println(title);
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
	}
	
	private static void footer(PrintWriter out) {
		out.println("</body>");
		out.println("</html>");
	}
	
	public static void alert(HttpServletResponse response, String message)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('" + message + "');");
		out.println("</script>");
	}
	
	public static void loginForm(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		header(out, "Login Page");
		
		out.println("<form action='login' method='post'>");
		out.println("아이디 : <input type='text' name='id' value='" + (id != null ? id : "") + "'><br>");
		out.println("비밀번호: <input type='password' name='pwd'><br>");
		out.println("<button type='submit'>");
		out.println("전송</button>");
		out.println("</form>");
		
		footer(out);
	}
	
	public static void customHTMLForBody(HttpServletResponse response, String title, String html)
		throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		header(out, title);
		
		out.println(html);
		
		footer(out);
	}
}
