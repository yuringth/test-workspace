package com.ring.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

	/*
	// HttpServletResponse 객체로 응답데이터 응답하기(기존의 JSP/Servlet을 사용했을 때 했던 Stream을 이용한 방식)
	@RequestMapping("ajax.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
		// 반환형이 String이 아닌 이유는 ajax로 요청한 것을 return하여 어딘가로 포워딩 하는 것이 아니기 때문이다.
		System.out.println();
		
		// 요청처리 잘 했다는 가정하에 요청한 페이지에 응답데이터가 존재 할 경우
		String responseData = "응답 문자열 : " + name + "은(는)" + age + "살 입니다.";
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(responseData);
	}
	*/
	
	// 2. 응답할 데이터를 문자열로 리턴
	// 단, 문자열을 리턴하면 원래 포워딩 방식임 => 응답뷰로 인식해서 해당 뷰페이지를 찾음
	// 따라서 내가 리턴하는 String타입이 응답 뷰 정보가 아니라 응답데이터라는 것을 선언하는 어노테이션
	// @ResponseBody => 내가 지금 return하는 정보가 뷰정보가 아니라 응답 데이터를 클라이언트한테 return하는거야
	// produces 속성 사용 전제조건 : 한글이 포함되어있는경우
	
	
	@ResponseBody
	@RequestMapping(value="ajax.do", produces="text/html; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		String responseData = "응답 문자열 : " + name + "은(는)" + age + "살 입니다.";
		return responseData;
	}
	
	
	
	// 3. 다수의 응답데이터가 있을 경우에는?
	//@RequestMapping("ajax1.do")
	
	
	
	
}
