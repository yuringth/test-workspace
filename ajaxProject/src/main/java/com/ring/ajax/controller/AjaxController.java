package com.ring.ajax.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

	// HttpServletResponse 객체로 응답데이터 응답하기(기존의 JSP/Servlet을 사용했을 때 했던 Stream을 이용한 방식)
	
	// 1.
	/*
	@RequestMapping("ajax.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
		// 반환형이 String이 아닌 이유는 ajax로 요청한 것을 return하여 어딘가로 포워딩 하는 것이 아니기 때문이다.
		
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
	/*
	@ResponseBody
	@RequestMapping(value="ajax.do", produces="text/html; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		String responseData = "응답 문자열 : " + name + "은(는)" + age + "살 입니다.";
		return responseData;
	}
	*/
	
	/*
	// 3. 다수의 응답데이터가 있을 경우에는?
	@RequestMapping("ajax.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
		//response.setContentType("text/html; charset=UTF-8");
		//response.getWriter().print(name);
		//response.getWriter().print(age);
		// 문제점 ) 출력하는 데이터가 하나의 문자열로 쭉 이어져있음 => 해결방안 :json사용
		
		// JSON(JavaScript Object Notation)형태로 담아서 응답 => 단점 : 몇번째 인덱스에 어떤 값이 있는지 하나하나 기억해야한다
		// 1. JSONArray => [값, 값, 값, ...] => 자바에서의 ArrayList와 유사
		// 2. JSONObject => {키:값, 키:값, ...} => 자바에서의 HashMap과 유사
		
		// 1. JSONArray 방법
		//JSONArray jArr = new JSONArray();
		//jArr.add(name); // ["홍길동"]
		//jArr.add(age);  // ["홍길동", 15]
		
		//response.setContentType("application/json; charset=UTF-8");
		//response.getWriter().print(jArr);
		
		
		// 2. JSONObject 방법
		JSONObject jObj = new JSONObject();
		jObj.put("name", name);
		jObj.put("age", age);

		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jObj);
	}
	*/
	
	
	
	
	
	// 스프링 방식
	
	@ResponseBody // 해결2) 뷰로 보내는것이 아니고 데이터를 보내는 거야
	@RequestMapping(value="ajax.do", produces="application/json; charset=UTF-8") // 해결3) 문자열이 아닌 json타입으로 돌려주고 싶다
	public String ajaxMethod1(String name, int age) {
		
		JSONObject jObj = new JSONObject();
		
		jObj.put("name", name);
		jObj.put("age", age);
		
		return jObj.toJSONString(); // 해결1) toJSONString() : 메소드를 호출하면 JSONObject를 문자열로 반환 / JSONArray에서도 똑같이 적용 가능
	}
	
	
	
	
}
