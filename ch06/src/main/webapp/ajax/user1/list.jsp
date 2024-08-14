<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ajax::user1</title>
	<script>
	
		window.onload = function(){
			
			const table = document.getElementsByTagName('table')[0];
			
			// 사용자 데이터 요청
			fetch('./proc/getUsers.jsp')
				.then(response=>response.json())
				.then(data => {
					console.log(data);
					
					for(const user of data){
						
						console.log(user.uid);
						
						let row = "<tr>";
							row += "<td>"+user.uid+"</td>";
							row += "<td>"+user.name+"</td>";
							row += "<td>"+user.birth+"</td>";
							row += "<td>"+user.hp+"</td>";
							row += "<td>"+user.age+"</td>";
							row += "<td>";
							row += "<a href='#'>수정</a>";
							row += "<a href='#'>삭제</a>";
							row += "</td>";
							row += "</tr>";							
							
						table.insertAdjacentHTML('beforeend', row);
					}
					
					
					
				})
				.catch(err => {
					console.log(err);
				});
		}
	</script>
</head>
<body>
	<h3>user1 목록</h3>
	
	<a href="./register.jsp">등록하기</a>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>휴대폰</th>
			<th>나이</th>
			<th>관리</th>
		</tr>
		
		
		
	</table>
	
</body>
</html>