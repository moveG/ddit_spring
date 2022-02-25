package com.spring.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.command.MailRequestCommand;
import com.spring.mail.MimeAttachNotifier;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private MimeAttachNotifier notifier;
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public void mailGet() throws Exception {}
	
	@RequestMapping(value="/send"
				  , method=RequestMethod.POST
				  , produces="text/plain;charset=utf-8")
	public ModelAndView mailPost(MailRequestCommand mailReq
							   , HttpServletRequest request
							   , ModelAndView mnv) throws Exception {
		String url = "mail/mail_success";
		
		MultipartFile attach = mailReq.getFile();
		
		String uploadPath = request.getServletContext().getRealPath("resources/mail_attach");
		
		//파일유무 확인
		if(attach != null && !attach.isEmpty()) {
			//파일크기 학인
			if(attach.getSize() < 1024 * 1024 * 5) {
				//파일 임시저장
				File file = new File(uploadPath, attach.getOriginalFilename());
				file.mkdirs();
				
				attach.transferTo(file);
				
				//메일 메시지 보내기
				try {
					notifier.sendMail(mailReq, uploadPath);
				} catch (Exception e) {
					e.printStackTrace();
					url = "mail/mail_fail";
					mnv.addObject("message", "메일 전송을 실패하였습니다.");
				}
			}else {
				url = "mail/mail_fail";
				mnv.addObject("message", "첨부파일 용량이 5MB를 초과하였습니다.");
			}
		}
		mnv.addObject("mailRequest", mailReq);
		
		//화면설정
		mnv.setViewName(url);
		
		return mnv;
	}
}
