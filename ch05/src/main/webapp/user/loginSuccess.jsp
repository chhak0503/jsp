<%@page import="sub1.UserVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%



	// 세션 사용자 정보 조회
	UserVO sessUser = (UserVO) session.getAttribute("sessUser");
	
	// 로그인을 하지 않았으면
	if(sessUser == null){
		response.sendRedirect("./login.jsp?success=101");
		return; // 처리 종료
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>loginSuccess</title>
</head>
<body>
	<h3>로그인 성공</h3>
	<p>
		<%= sessUser.getName() %>님 반갑습니다.<br>
		<a href="./logout.jsp">로그아웃</a>	
	</p>
</body>
</html>








