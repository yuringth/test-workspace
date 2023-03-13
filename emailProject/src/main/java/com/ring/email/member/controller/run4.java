package com.ring.email.member.controller;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class run4 {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("sendfile")
	public String mail() throws MessagingException {
		
		// MimeMessage를 이용한 파일 첨부
		// - javax.acvation.DataSource : 파일 정보(sql이면 데이터베이스 관련)
		
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		

		helper.setSubject("파일테스트");
		helper.setText("본문.");
		String[] to = {"testemaildayo2@gmail.com", "dldlwlrma@gmail.com"}; // 받는사람 배열로 생성 후 message객체에 대입
		helper.setTo(to);
		helper.setCc("dldlwlrma@gmail.com"); // 참조
		helper.setBcc("dldlwlrma@gamil.com"); // 숨은 참조
		
		// 파일 첨부
		DataSource source = new FileDataSource("C:\\Users\\user\\Desktop\\logo/no_image.jpg");
		helper.addAttachment(source.getName(), source);
		
		sender.send(message);
		return "redirect:/";
		
		
		
	}
	
}