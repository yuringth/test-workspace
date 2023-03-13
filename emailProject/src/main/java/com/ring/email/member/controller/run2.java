package com.ring.email.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class run2 {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("mail")
	public String mail() {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setSubject("테스트메일2에용");
		message.setText("테스트메일2의 본문 내용입니다.");

		String[] to = {"testemaildayo2@gmail.com", "dldlwlrma@gmail.com"}; // 받는사람 배열로 생성 후 message객체에 대입
		message.setTo(to);
		
		message.setCc("dldlwlrma@gmail.com"); // 참조
		message.setBcc("dldlwlrma@gamil.com"); // 숨은 참조
		
		// 메시지 전송
		sender.send(message);
		
		return "redirect:/";
		
	}
	
}