package com.jsp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.utils.FileDownloadResolver;
import com.jsp.utils.GetUploadPath;

@WebServlet("/notice/getPicture")
public class NoticeGetPictureServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String url = null;
		
		String fileName = request.getParameter("picture");
		String savedPath = GetUploadPath.getUploadPath("notice.picture.upload");
		
		FileDownloadResolver.sendFile(fileName, savedPath, request, response);
	}
}
