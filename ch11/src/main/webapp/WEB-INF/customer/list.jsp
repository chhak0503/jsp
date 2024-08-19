<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>customer::list</title>
</head>
<body>
	<h3>고객목록</h3>
	
	<a href="/ch11/">처음으로</a>
	<a href="/ch11/customer/register.do">등록</a>
	
	<table border="1">
		<tr>
			<th>고객아이디</th>
			<th>고객명</th>
			<th>휴대폰</th>
			<th>주소</th>
			<th>가입일</th>
			<th>관리</th>
		</tr>
		<c:forEach var="customer" items="${customers}">
		<tr>
			<td>${customer.custId}</td>
			<td>${customer.name}</td>
			<td>${customer.hp}</td>
			<td>${customer.addr}</td>
			<td>${customer.rdate}</td>
			<td>
				<a href="#">수정</a>
				<a href="">삭제</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>