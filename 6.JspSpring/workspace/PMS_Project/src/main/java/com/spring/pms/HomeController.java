package com.spring.pms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() throws Exception {
		String url = "home";
		return url;
	}
}
