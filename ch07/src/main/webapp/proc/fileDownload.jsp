<%@page import="java.io.IOException"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@page import="sub1.FileVO"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 파일번호 수신
	String no = request.getParameter("no");

	// 데이터베이스 조회
	FileVO vo = null;
	
	try{
		Context ctx = (Context) new InitialContext().lookup("java:comp/env");
		DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
		
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery("select * from `filetest` where `no`=" + no);
		
		if(rs.next()){
			vo = new FileVO();
			vo.setNo(rs.getInt(1));
			vo.setUid(rs.getString(2));
			vo.setName(rs.getString(3));
			vo.setOname(rs.getString(4));
			vo.setSname(rs.getString(5));
			vo.setRdate(rs.getString(6));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}


	// response 헤더정보 수정
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(vo.getOname(), "utf-8"));
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "private");

	// 파일 내용 스트림 처리
	String path = application.getRealPath("/uploads");
	File file = new File(path + File.separator + vo.getSname());
	
	
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	
	bis.transferTo(bos); // 파일 내용을 간단하게 전송
    bos.flush();
	
	/*
	while(true){
		int data = bis.read();
		if(data == -1){
			break;
		}
		
		bos.write(data);
	}
	*/
	
	bos.close();
	bis.close();
	
	
	
	
%>

