package com.ring.email.member.controller;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// 직접 도구를 생성해서 이메일을 보내는 방법
// 필요한 의존성 목록
// - spring-context-support
// - java mail-api


public class run1 {
	
	public static JavaMailSenderImpl sender; // 메일 전송도구 설정 (요청이 들어오면 전송도구를 이용하여 메일을 보내게 해야함)
	public static void main(String[] args) {
		
		JavaMailSenderImpl impl = new JavaMailSenderImpl();
		
		// - 계정 설정
		impl.setHost("smtp.gmail.com"); // 구글 사용(고정)
		impl.setPort(587); // 구글 포트 번호(고정)
		impl.setUsername("testemaildayo2"); // 아이디
		impl.setPassword("xsmrvbmkemfslghr"); // 패스워드
		
		// - 옵션 설정
		Properties prop = new Properties(); // Map<Object, Object> 형태
		prop.put("mail.smtp.auth", true); // 권한 관련 옵션
		prop.put("mail.smtp.starttls.enable", true); // 보안 관련 옵션
		
		impl.setJavaMailProperties(prop); // 옵션과 연결
		
		sender = impl; 
		
		
		// 메시지 생성
		SimpleMailMessage message = new SimpleMailMessage();
		
		// 메시지 정보 설정 : 제목, 내용, 받는사람, 참조, 숨은참조 (첨부파일은 simple에선 불가)
		message.setSubject("테스트메일이에용");
		message.setText("테스트메일의 본문 내용입니다.");

		String[] to = {"testemaildayo2@gmail.com", "dldlwlrma@gmail.com"}; // 받는사람 배열로 생성 후 message객체에 대입
		message.setTo(to);
		
		message.setCc("dldlwlrma@gmail.com"); // 참조
		message.setBcc("dldlwlrma@gamil.com"); // 숨은 참조
		
		// 전송
		sender.send(message); // 전송할때 메시지 정보들을 담아서 보냄
		
		
	}

}