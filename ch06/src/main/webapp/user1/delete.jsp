<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String uid = request.getParameter("uid");

	String host = "jdbc:mysql://127.0.0.1:3306/studydb";
	String user = "root";
	String pass = "1234";

	try {
		// 드라이버 로드		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 1단계 - 데이터베이스 접속
		Connection conn = DriverManager.getConnection(host, user, pass);
		
		// 2단계 - SQL실행 객체 생성
		String sql = "delete from `user1` where `uid`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, uid);
		
		// 3단계 - SQL실행
		psmt.executeUpdate();
		
		// 4단계 - 결과처리(SELECT일 경우)
		// 5단계 - 데이터베이스 종료
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// 목록 이동
	response.sendRedirect("/ch06/user1/list.jsp");
%>
