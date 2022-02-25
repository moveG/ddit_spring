package egovframework.example.sample.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.example.sample.service.LoginVo;
import egovframework.example.util.SessionUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	//log
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
	
	/*
	 * 후처리
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		LOGGER.debug("후처리 : {}", this.getClass().getCanonicalName());
		
		List<String> codeList = new ArrayList<>();
		
		codeList.add("사자");
		codeList.add("호랑이");
		codeList.add("곰");
		
		modelAndView.addObject("codeList", codeList);
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/*
	 * 전처리
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("=================================== 로그인 인터셉터 작동 ===================================");
		System.out.println("=================================== 로그인 인터셉터 작동 ===================================");
		
		//request.getSession().getAttribute("islogin");
		//boolean isAt = (boolean) request.getSession().getAttribute("islogin");
		//String isAt = (String) request.getSession().getAttribute("islogin");
		//LoginVo loginInfo = (LoginVo) request.getSession().getAttribute("islogin");
		LoginVo loginInfo = SessionUtil.getSession(request);
//		String str = "하이, " + loginInfo.getName() + "입니다.";
		
		LOGGER.debug("loginInfo : {} / test : {}", loginInfo, "test");
//		LOGGER.debug("loginInfo : {} / test : {}", loginInfo, str);
//		System.out.println(loginInfo);
		
		//if(isAt) {
		//if("yes".equalsIgnoreCase(isAt)) {
		if(loginInfo != null && loginInfo.isLogin()) {
			System.out.println("로그인 완료");
		}else {
			System.out.println("로그인 해주세요.");
			ModelAndView modelAndView = new ModelAndView("redirect:/login.do");
			throw new ModelAndViewDefiningException(modelAndView);			
		}
		
		return super.preHandle(request, response, handler);
	}
}
