package com.ring.email.member.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class run3 {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("sendmail")
	public String mail() throws MessagingException {
		
		// Mime Message 생성
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
		
		// helper에 정보 설정
		String[] to = {"testemaildayo2@gmail.com", "dldlwlrma@gmail.com"}; // 받는사람 배열로 생성 후 message객체에 대입
		helper.setTo(to);
		
		helper.setCc("dldlwlrma@gmail.com"); // 참조
		helper.setBcc("dldlwlrma@gamil.com"); // 숨은 참조
		helper.setSubject("테스트메일3이에용");
		// helper.setText("<h1>테스트3</h1><hr><a href='http://localhost:8024/webSocket/'>웹사이트 링크</a>", true);
		
		String url = ServletUriComponentsBuilder
				     .fromCurrentContextPath()
				     .port(8095)
				     .path("/")
				     .toUriString();
	
		helper.setText("<a href='" + url + "'>웹사이트 링크</a>", true);
		
		sender.send(message);
		
		return "redirect:/";
		
		
		
	}
	
}