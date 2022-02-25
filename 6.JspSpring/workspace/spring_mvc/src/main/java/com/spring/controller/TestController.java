package com.spring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.command.ParamCommand;

@Controller
public class TestController {
	
	@RequestMapping(value = "/http", method = RequestMethod.GET)
	public void getHttp(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		int num = Integer.parseInt(request.getParameter("num"));
		
		System.out.println("id : " + id + ", pwd : " + pwd + ", num : " + num);
	}
	
	//Parameter 방식
	/*@RequestMapping(value = "/param", method = RequestMethod.GET)
	public void getParam(String id, String pwd, int num) 
			throws IOException, ServletException{
		System.out.println("id : " + id + ", pwd : " + pwd + ", num : " + num);
	}*/
	
	//Parameter 방식 - 변수명이 다르면 파라미터를 받을 수 없다.
	/*@RequestMapping(value = "/param", method = RequestMethod.GET)
	public void getParam(String idd, String pwd, int num) 
			throws IOException, ServletException{
		System.out.println("idd : " + idd + ", pwd : " + pwd + ", num : " + num);
	}*/
	
	//Parameter 방식 
	//변수명을 다르게 하고 싶으면 "@RequestParam(value="id")String idd" 형식을 사용한다.
	// @RequestParam(value="", defaultValue="") : defaultValue로 null처리도 가능하다.
	// @RequestParam(defaultValue="")String idd : Request에 idd가 없으면 idd에 무조건 defaultValue를 넣는다.
	// 주소창의 num에 숫자대신 문자를 넣으면(잘못된 Request) 400에러가 터지고, console창에는 아무것도 출력되지 않는다.
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public void getParam(@RequestParam(value="id", defaultValue="nana")String idd, String pwd, int num) 
			throws IOException, ServletException{
		System.out.println("idd : " + idd + ", pwd : " + pwd + ", num : " + num);
	}
	
	//Command 방식
	@RequestMapping(value = "/command", method = RequestMethod.GET)
	public void getParam(ParamCommand cmd) 
			throws IOException, ServletException{
		System.out.println(cmd);
	}
}
