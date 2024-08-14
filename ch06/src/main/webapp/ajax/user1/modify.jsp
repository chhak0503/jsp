<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ajax::user1</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
		
		
		window.onload = function(){
			
			// 주소 파라미터 파싱(수정 아이디 추출하기)
			const params = location.href.split('?')[1];
			const value = params.split('=')[1];
			
			// 문서객체 생성
			const form = document.getElementsByTagName('form')[0];
			const btnSubmit = form.submit;
			
			// 수정하기 버튼 이벤트
			btnSubmit.onclick = (e) => {
				e.preventDefault();
				
				const jsonData = {
					"uid": form.uid.value,
					"name": form.name.value,
					"birth": form.birth.value,
					"hp": form.hp.value,
					"age": form.age.value
				};
				
				// POST 전송 --> jquery ajax()는 getparameter() 수신				
				$.ajax({
					method: 'POST',
					url: './proc/modifyProc.jsp',
					data: jsonData,
					success: function(data){
						console.log(data);
						
						if(data.result > 0){
							alert('수정 되었습니다.');
							location.href = '../user1/list.jsp';
						}
					}
				});
				
				
				/*
				fetch('./proc/modifyProc.jsp', {
						method: 'POST',
						headers: {'Content-Type': 'application/json'},
						body: JSON.stringify(jsonData)
					})
					.then(res => res.json())
					.then(data => {
						
						if(data.result > 0){
							alert('수정 되었습니다.');
							location.href = '../list.jsp';
						}
					})
					.catch(err => {
						console.log(err);
					});
				*/
			}
			
			// 수정 데이터 요청하기
			fetch('./proc/getUser.jsp?uid='+value)
				.then(resp => resp.json())
				.then(data => {
					console.log(data);
					
					// 수정 데이터 출력
					form.uid.value = data.uid;
					form.name.value = data.name;
					form.birth.value = data.birth;
					form.hp.value = data.hp;
					form.age.value = data.age;
				})
				.catch(err => {
					console.log(err);
				});

		}// onload end
	
	
	
	</script>
	
	
</head>
<body>
	<h3>user1 수정</h3>
	
	<a href="./list.jsp">목록이동</a>
	<form action="#" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="uid" value="a101" readonly/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="홍길동"/></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input type="date" name="birth" value="1990-10-11"/></td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="text" name="hp" value="010-1234-1001"/></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="number" name="age" value="21"/></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" name="submit" value="수정하기"/>
				</td>
			</tr>		
		</table>
	
	</form>
</body>
</html>