package kr.or.ddit.interceptor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jsp.dto.MemberVO;

public class LoginUserLoginInterceptor extends HandlerInterceptorAdapter {
	
	private String savePath = "c:\\log";
	private String saveFileName = "login_user_log.csv";
	
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	
	/*@Override
	public boolean preHandle(HttpServletRequest request
						   , HttpServletResponse response
						   , Object handler) throws Exception {
		//false를 리턴하면 controller가 실행이 안됨
		//true를 리턴하면 controller가 실행이 됨
	}*/

	@Override	//controller가 실행한 것을 받아줌
	public void postHandle(HttpServletRequest request
						 , HttpServletResponse response
						 , Object handler
						 , ModelAndView modelAndView) throws Exception {
		MemberVO loginUser = (MemberVO) request.getSession().getAttribute("loginUser");
		if(loginUser == null) return;
		
		//로그인 정보를 스트링으로 저장
		String tag = "[login:user]";
		String log = tag + " "
				   + loginUser.getId() + ", "
				   + loginUser.getPhone() + ", "
				   + loginUser.getEmail() + ", "
				   + request.getRemoteAddr() + ", " 
				   + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		File file = new File(savePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		String logFilePath = savePath + File.separator + saveFileName;
		BufferedWriter out = new BufferedWriter(new FileWriter(logFilePath, true));
		
		//로그 기록
		out.write(log);
		out.newLine();
		
		out.close();
	}

	/*@Override
	public void afterCompletion(HttpServletRequest request
							  , HttpServletResponse response
							  , Object handler
							  , Exception ex) throws Exception {
		//exception 처리
	}*/

}
