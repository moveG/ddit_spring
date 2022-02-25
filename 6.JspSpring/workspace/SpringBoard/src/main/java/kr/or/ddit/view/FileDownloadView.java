package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;

import com.jsp.utils.MakeFileName;

public class FileDownloadView implements View {
	
	private String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
	
	public void setContentType(String contentType) {
		this.contentType = contentType;	//만들어는 놨지만, 쓸 일은 없음
	}

	@Override
	public String getContentType() {
		return this.contentType;
	}

	@Override
	public void render(Map<String, ?> model	//?: 너가 준 그거 그 타입, Object: Object타입
					 , HttpServletRequest request
					 , HttpServletResponse response) throws Exception {
		String savedPath = (String) model.get("savedPath");
		String fileName = (String) model.get("fileName");
		
		//보낼 파일 설정
		File downloadFile = new File(savedPath + File.separator + fileName);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		//파일 포맷으로 MIME 결정 - MIME타입이 없으면 무조건 다운로드, 있으면 확장자를 기준으로 MIME타입을 만들어줌 - pdf, image파일은 열림
		ServletContext context = request.getServletContext();
		String mimeType = context.getMimeType(downloadFile.getName());	//확장자를 기준으로 MIME타입을 만들어줌
		if(mimeType != null) {
			this.contentType = mimeType;
		}
		
		//response 수정 - 위에서 설정한 MIME Type을 바탕으로 Header를 설정
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		
		String headerKey = "Content-Disposition";
		
		//한글깨짐 방지 : ISO-8859-1
		String sendFileName = MakeFileName.parseFileNameFromUUID(downloadFile.getName(), "\\$\\$");
		
		String header = request.getHeader("User-Agent");
		if(header.contains("MISE")) {
			sendFileName = URLEncoder.encode(sendFileName, "UTF-8");
		}else {
			sendFileName = new String(sendFileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		String headerValue = String.format("attachment; filename=\"%s\"", sendFileName);
		response.setHeader(headerKey, headerValue);
		
		//파일 내보내기
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		while((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		
		inStream.close();
		outStream.close();
	}
}
