<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- jQuery 라이브러리 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>

	<h3>1. 요청시 값 전달, 응답 결과 받아보기</h3>	
	
	이름 : <input type="text" id="name"> <br>
	나이 : <input type="number" id="age"> <br>
	
	<button onclick="test1();">전송</button>
	
	<div id="result1"></div>
	
	<script>
		function test1(){
			$.ajax({
				url : 'ajax.do',
				data : {
					name : $('#name').val(),
					age : $('#age').val()
				},
				success : function(result){
					console.log(result);
					//$('#result1').text(result);
					
					// JSONArray 방법 : 응답데이터가 배열 형태면 => 인덱스에 접근 가능 [인덱스]
					//let value = '이름 : ' + result[0] + '<br>나이 : ' + result[1];
					//$('#result1').html(value);
					
					// JSONObject 방법 : 응답데이터가 객체 형태면 => 속성에 접근가능 .속성명
					let value = '이름 : ' + result.name + '<br>나이 : ' + result.age;
					$('#result1').html(value);
				}, 
				error :  function(){
					console.log('ajax통신 실패');
				}
			});
		}
	</script>
	
	
</body>
</html>