<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
	//유효성 검사에 사용할 정규표현식
	const reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
	const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
	const reName  = /^[가-힣]{2,10}$/ 
	const reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
	const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	const reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

	window.onload = function(){
		
		const btnCheckUid = document.getElementById('btnCheckUid');
		const registerForm = document.getElementsByTagName('form')[0];
		const resultId = document.getElementsByClassName('resultId')[0];
		const resultPass = document.getElementsByClassName('resultPass')[0];
		const resultName = document.getElementsByClassName('resultName')[0];
		const resultNick = document.getElementsByClassName('resultNick')[0];
		
		// 1.아이디 유효성 검사
		btnCheckUid.onclick = function(){
			
			const uid = registerForm.uid.value;
			
			// 아이디 유효성 검사
			if(!uid.match(reUid)){
				resultId.innerText = '아이디가 유효하지 않습니다.';
				resultId.style.color = 'red';
				return;
			}
			
			// 중복체크
			fetch('/jboard/user/checkUser.do?type=uid&value='+uid)
				.then(resp => resp.json())
				.then(data => {
					console.log(data);
					
					if(data.result > 0){
						resultId.innerText = '이미 사용중인 아이디 입니다.';
						resultId.style.color = 'red';
					}else{
						resultId.innerText = '사용 가능한 아이디 입니다.';
						resultId.style.color = 'green';
					}
				})
				.catch(err => {
					console.log(err);
				});
		}
		
		// 2.비밀번호 유효성 검사
		registerForm.pass2.addEventListener('focusout', function(){
		
			const pass1 = registerForm.pass1.value;
			const pass2 = registerForm.pass2.value;
			
			if(!pass1.match(rePass)){
				resultPass.innerText = "비밀번호가 유효하지 않습니다.";
				resultPass.style.color = 'red';
				return;
			}
			
			if(pass1 == pass2){
				resultPass.innerText = "비밀번호가 일치합니다.";
				resultPass.style.color = 'green';
			}else{
				resultPass.innerText = "비밀번호가 일치하지 않습니다.";
				resultPass.style.color = 'red';
			}
		});
		
		// 3.이름 유효성 검사
		registerForm.name.addEventListener('focusout', function(){
			
			const name = registerForm.name.value;
			
			if(!name.match(reName)){
				resultName.innerText = "이름이 유효하지 않습니다.";
				resultName.style.color = 'red';				
			}else{
				resultName.innerText = "";
			}
		});
		
		// 4.별명 유효성 검사
		registerForm.nick.addEventListener('focusout', function(){
			
			const nick = registerForm.nick.value;
			
			if(!nick.match(reNick)){
				resultNick.innerText = '별명이 유효하지 않습니다.';
				resultNick.style.color = 'red';
				return;
			}
			
			fetch('/jboard/user/checkUser.do?type=nick&value='+nick)
				.then(response => response.json())
				.then(data => {
					console.log(data);
					if(data.result > 0){
						resultNick.innerText = '이미 사용중인 별명입니다.';
						resultNick.style.color = 'red';
					}else{
						resultNick.innerText = '사용 가능한 별명입니다.';
						resultNick.style.color = 'green';
					}
				})
				.catch(err => {
					console.log(err);
				});
		});
		
		
		// 5.이메일 유효성 검사
		// 6.휴대폰 유효성 검사
		
		
		
		
		
	}
</script>
<main>
    <section class="register">
        <form action="/jboard/user/register.do" method="post">
            <table border="1">
                <caption>사이트 이용정보 입력</caption>
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" name="uid" placeholder="아이디 입력"/>
                        <button type="button" id="btnCheckUid"><img src="../images/chk_id.gif" alt=""></button>
                        <span class="resultId"></span>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td>
                        <input type="password" name="pass1" placeholder="비밀번호 입력"/>                            
                    </td>
                </tr>
                <tr>
                    <td>비밀번호 확인</td>
                    <td>
                        <input type="password" name="pass2" placeholder="비밀번호 확인 입력"/>
                        <span class="resultPass"></span>
                    </td>
                </tr>
            </table>
            <table border="1">
                <caption>개인정보 입력</caption>
                <tr>
                    <td>이름</td>
                    <td>
                        <input type="text" name="name" placeholder="이름 입력"/>
						<span class="resultName"></span>
                    </td>
                </tr>
                <tr>
                    <td>별명</td>
                    <td>
                        <p>공백없이 한글, 영문, 숫자만 입력가능</p>
                        <input type="text" name="nick" placeholder="별명 입력"/>
                        <span class="resultNick"></span>                            
                    </td>
                </tr>
                <tr>
                    <td>E-Mail</td>
                    <td>
                        <input type="email" name="email" placeholder="이메일 입력"/>
                    </td>
                </tr>
                <tr>
                    <td>휴대폰</td>
                    <td>
                        <input type="text" name="hp" placeholder="- 포함 13자리 입력" minlength="13" maxlength="13" />
                    </td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>
                        <div>
                            <input type="text" name="zip" placeholder="우편번호" readonly/>                                
                            <button class="btnZip"><img src="../images/chk_post.gif" alt=""></button>
                        </div>                            
                        <div>
                            <input type="text" name="addr1" placeholder="주소를 검색하세요." readonly/>
                        </div>
                        <div>
                            <input type="text" name="addr2" placeholder="상세주소를 입력하세요."/>
                        </div>
                    </td>
                </tr>
            </table>

            <div>
                <a href="/jboard/user/login.do" class="btnCancel">취소</a>
                <input type="submit"   class="btnSubmit" value="회원가입"/>
            </div>    
        </form>
    </section>
</main>
<%@ include file="./_footer.jsp" %>