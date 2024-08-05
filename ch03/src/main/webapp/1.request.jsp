<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>1.request</title>
	<!-- 
		날짜 : 2024/08/05
		이름 : 김철학
		내용 : requset 내장객체 실습하기
		
		request 내장객체
		 - 클라이언트 요청 정보를 갖는 객체(header, body 구성)
		 - 클라이언트 전송 데이터를 수신하는 기능(getParameter)
		 
		GET 요청
		 - GET 요청방식은 HTTP 기본 요청방식 
		 - GET은 서버에 있는 자원을 요청하는 요청방식
		 - GET요청으로 데이터를 전송할때는 Query String(Parameter)로 요청 주소에 노출되어 전송 
		
		POST 요청
		 - POST는 사용자가 입력한 값을 서버로 전송하기 위한 요청방식
		 - 전송 데이터는 주소에 노출되지 않고 요청 메시지 바디에 삽입되어 전송
		 - GET방식에 비해 보안에 유리하고 대량의 데이터 전송에 적합
	-->
</head>
<body>
	<h3>1.request 내장객체</h3>
	
	<h4>GET 요청 방식</h4>
	<form action="./proc/result1.jsp" method="get">
		<input type="text" name="uid"><br>
		<input type="text" name="name"><br>
		<input type="number" name="age"><br>
		<input type="submit" value="전송"/>
	</form>
	
	
	<h4>POST 요청 방식</h4>
	<form action="./proc/result2.jsp" method="post">
		<input type="text" name="uid"><br>
		<input type="text" name="name"><br>
		<input type="number" name="age"><br>
		<input type="submit" value="전송"/>
	</form>
	
	<h4>request 기타 정보</h4>
	<p>
		헤더정보 : <%= request.getHeader("User-Agent") %><br>
		프로토콜 : <%= request.getProtocol() %><br>
		서버이름 : <%= request.getServerName() %><br>
		요청주소 : <%= request.getRequestURL() %><br>
		요청경로 : <%= request.getRequestURI() %><br>
		IP주소 : <%= request.getRemoteAddr() %><br>
	</p>
</body>
</html>










