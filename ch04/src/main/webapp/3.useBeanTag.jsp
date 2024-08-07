<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>3.useBeanTag</title>
	<!-- 
		날짜 : 2024/08/07
		이름 : 김철학
		내용 : JSP useBean 액션태그 실습하기
	 -->
</head>
<body>
	<h3>3.useBean 액션태그</h3>
	
	<form action="./proc/userProc.jsp" method="GET">
		<input type="text" name="uid" placeholder="아이디 입력"/><br>
		<input type="text" name="name" placeholder="이름 입력"/><br>
		<input type="text" name="hp" placeholder="휴대폰 입력"/><br>
		<input type="number" name="age" placeholder="나이 입력"/><br>
		<input type="submit" value="전송하기"/><br>
	</form>
	
</body>
</html>







