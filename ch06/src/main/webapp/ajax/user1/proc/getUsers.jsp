<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="user1.User1VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<User1VO> users = new ArrayList<>();

	try {
		// 1단계 - JNDI 서비스 객체 생성
		Context ctx = (Context) new InitialContext().lookup("java:comp/env");
		
		// 2단계 - 커넥션 가져오기
		DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
		Connection conn = ds.getConnection();
		
		// 2단계 - SQL실행 객체 생성
		String sql = "select * from `user1`";
		PreparedStatement psmt = conn.prepareStatement(sql);
		
		// 3단계 - SQL실행
		ResultSet rs = psmt.executeQuery();
		
		// 4단계 - 결과처리(SELECT일 경우)
		while(rs.next()){
			User1VO vo = new User1VO();
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setBirth(rs.getString(3));
			vo.setHp(rs.getString(4));
			vo.setAge(rs.getInt(5));
			
			users.add(vo);
		}
		
		// 5단계 - 데이터베이스 종료
		rs.close();
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}

	// JSON 출력 --> content-type을 application/json 설정
	Gson gson = new Gson();
	String jsonData = gson.toJson(users);
	out.print(jsonData);
%>

