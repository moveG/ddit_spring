package com.spring.mail;

import java.io.File;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.spring.command.MailRequestCommand;

public class MimeAttachNotifier {
	
	private JavaMailSender mailSender;
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(MailRequestCommand command, String uploadPath) throws Exception {
		//메시지 작성
		MimeMessage message = mailSender.createMimeMessage();
		
		//메시지 작성 헬퍼 : 첨부파일 여부(true/false)
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
		
		//받는 사람 : 이메일의 @같은 특수문자 유지(깨짐 방지)를 위해 new InternetAddress()형식을 사용함
		messageHelper.setTo(new InternetAddress(command.getReceiver()));
		//보내는 사람 : 앞은 이메일 주소, 뒤는 이름
		messageHelper.setFrom(command.getSender(), "운영자");
		//제목
		messageHelper.setSubject(command.getTitle());
		//내용 - html형식이면 true, 일반텍스트 형식이면 false
		messageHelper.setText(command.getContent(), true);
		//첨부파일
		if(command.getFile() != null && !command.getFile().isEmpty()) {
			String fileName = command.getFile().getOriginalFilename();
			
			DataSource dataSource = new FileDataSource(new File(uploadPath, fileName));
			
			messageHelper.addAttachment(MimeUtility.encodeText(fileName, "utf-8", "B"), dataSource);
		}
		
		//메일 보내기
		mailSender.send(message);
	}
}
