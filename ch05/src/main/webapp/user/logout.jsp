<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 세션 해제(현재 클라이언트 세션 정보 초기화)
	// session.removeAttribute("sessUser"); // --> 아래 invalidate로 한번에 처리
	session.invalidate();
	
	response.sendRedirect("./login.jsp?logout=success");

%>