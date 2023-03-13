package com.ring.email.member.controller;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ring.email.member.model.service.MemberService;
import com.ring.email.member.model.vo.CertVO;

@Controller // Contoller타입의 어노테이션을 붙여주면 빈 스캐닝을 통해 자동으로 빈 등록
public class MemberController {
	
	//private MemberServiceImpl memberService = new MemberServiceImpl();
	
	/*
	 * 기존 객체 생성 방식
	 * 객체간의 결합도가 높아짐(B클래스의 수정이 일어날경우 B클래스를 의존하고 있던 A클래스도 하나하나 전부 다 바꿔줘야함)
	 * 서비스가 동시에 매우 많은 횟수가 요청될 경우 그만큼 객체 생성된다.
	 */
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private JavaMailSender sender;

	
	@GetMapping("input")
	public String input() {
		return "member/input";
	}
	
	@GetMapping("check")
	public String check() {
		return "member/check";
	}
	
	@PostMapping("input")
	public String input(String email, HttpServletRequest request) throws MessagingException {
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		String ip = request.getRemoteAddr();
		String secret = generateSecret();
		CertVO certVo = CertVO
					    .builder()
						.who(ip)
						.secret(secret)
						.build();
		
		memberService.sendMail(certVo);
		
		helper.setTo(email);
		helper.setSubject("인증해라~");
		helper.setText("인증번호 : " + secret);
		sender.send(message);
		
		return "redirect:check";
	}
	
	
	public String generateSecret() {
		Random r = new Random();
		int n = r.nextInt(100000);
		Format f = new DecimalFormat("000000");
		String secret = f.format(n);
		
		return secret;
	}
	
	@ResponseBody
	@PostMapping("check")
	public String check(String secret, HttpServletRequest request) {
		
		CertVO certVo = CertVO.builder()
							  .who(request.getRemoteAddr())
							  .secret(secret)
							  .build();
		
		boolean result = memberService.validate(certVo);
		
		return "result : " + result;
		
	}
	
	
	
	

}
