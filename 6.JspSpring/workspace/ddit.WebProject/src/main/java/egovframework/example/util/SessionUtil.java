package egovframework.example.util;

import javax.servlet.http.HttpServletRequest;

import egovframework.example.sample.service.LoginVo;

public class SessionUtil {
	
	public static LoginVo getSession(HttpServletRequest req) {
		LoginVo loginInfo = (LoginVo) req.getSession().getAttribute("loginInfo");
		return loginInfo;
	}
	
	public static void setSession(HttpServletRequest req, LoginVo loginInfo) {
		req.getSession().setAttribute("loginInfo", loginInfo);
	}
}
