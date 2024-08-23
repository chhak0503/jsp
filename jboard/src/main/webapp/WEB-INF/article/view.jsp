<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글보기</title>
    <link rel="stylesheet" href="/jboard/css/style.css">
    <script>
    	window.onload = function(){
    		
    		const commentForm = document.commentForm;
    		const commentList = document.getElementsByClassName('commentList')[0];
    		
    		let originalText = '';
    		
    		// 동적 이벤트 처리
    		document.addEventListener('click', function(e){
    			
    			const article = e.target.closest('article');
				const textarea = article.querySelector('textarea');
				const commentRemove = article.querySelector('.commentRemove');
				const commentCancel = article.querySelector('.commentCancel');
				const commentModify = article.querySelector('.commentModify');
    			
    			// 취소(224라인에 취소 a태그 추가할것)
    			if(e.target.classList == 'commentCancel'){
    				e.preventDefault();
    				textarea.value = originalText;
    				textareaEditMode(false);
    			}
    			
    			// 수정 & 수정완료
    			if(e.target.classList == 'commentModify'){
    				e.preventDefault();
        			const mode = commentModify.innerText;
    				
    				if(mode == '수정'){
    					originalText = textarea.value;
    					textareaEditMode(true);
    				}else{
    					// 수정완료
	    				const no = commentModify.dataset.no;
	    				const comment = textarea.value;
	    				
	    				const formData = new FormData();
	    				formData.append("no", no);
	    				formData.append("comment", comment);
	    				
	    				fetch('/jboard/comment/modify.do', {
	    						method: 'POST',
	    						body: formData
	    					})
	    					.then(resp => resp.json())
	    					.then(data => {
	    						console.log(data);
	    						
	    						if(data.result > 0){
	    							alert('댓글이 수정되었습니다.');
	    							textareaEditMode(false);
	    						}
	    					})
	    					.catch(err => {
	    						console.log(err);
	    					});
    				}
    			}
    			
    			// 댓글 Textarea 수정모드/일반모드 전환 함수
    			function textareaEditMode(edit) {
        			if(edit){
        				// 수정모드
        				textarea.readOnly = false;
        				textarea.style.background = 'white';
        				textarea.style.border = '1px solid #555';
        				textarea.focus();
        				commentModify.innerText = '수정완료';
        				commentCancel.style.display = 'inline';
        			}else{
        				// 일반모드
        				textarea.readOnly = true;
	    				textarea.style.background = 'transparent';
	    				textarea.style.border = 'none';
	    				commentModify.innerText = '수정';
	    				commentCancel.style.display = 'none';
        			}
        		}
    			
    			// 삭제
    			if(e.target.classList == 'commentRemove'){
    				e.preventDefault();
    					
   					if(!confirm('정말 삭제하시겠습니까?')){
       					return;
       				}
       				const article = e.target.closest('article');
       				const no = e.target.dataset.no; // a태그 data-no 속성값 가져오기
       				
       				fetch('/jboard/comment/delete.do?no='+no)
       					.then(resp => resp.json())
       					.then(data => {
       						console.log(data);
       						if(data.result > 0){
       							alert('댓글이 삭제되었습니다.');
       							
       							// 동적 삭제 처리
       							article.remove();
       						}else{
       							alert('댓글이 삭제가 실패했습니다.');
       						}
       					})
       					.catch(err => {
       						console.log(err);
       					});
    			}
    		});
    		
    		// 댓글 등록
    		commentForm.onsubmit = function(e){
    			e.preventDefault();
    			
    			// 사용자가 입력한 값 취합
    			const parent = commentForm.parent.value;
    			const writer = commentForm.writer.value;
    			const comment = commentForm.comment.value;
    			
    			// 폼 데이터 생성(JSON 데이터로 전송하게 되면 getParameter 수신 처리가 안되기 때문에 FormData로 전송)
    			const formData = new FormData();
    			formData.append("parent", parent);
    			formData.append("writer", writer);
    			formData.append("comment", comment);
    			console.log(formData);
    			
    			fetch('/jboard/comment/write.do', {
    					method: 'POST',
    					body: formData
    				})
    				.then(resp => resp.json())
    				.then(data => {
    					console.log(data);
    					
    					if(data != null){
    						alert('댓글이 등록되었습니다.');
    						
    						// 등록한 댓글 동적 태그 생성
    						const commentArticle = `<article class='comment'>
								                        <span>
								                            <span>\${data.rdate}</span>
								                            <span>\${data.writer}</span>
								                        </span>
								                        <textarea name='comment' readonly>\${data.content}</textarea>
								                        <div>
									                        <a href="#" class="commentRemove">삭제</a>
								                            <a href="#" class="commentModify">수정</a>
								                            <a href="#" class="commentUpdate">수정완료</a>
								                        </div>
								                    </article>`;
    						
    						commentList.insertAdjacentHTML('beforeend', commentArticle);
    						
    					}else{
    						alert('댓글 등록이 실패했습니다.');
    					}
    				})
    				.catch(err => {
    					console.log(err);
    				});
    		}
    	}
    </script>
    
</head>
<body>
    <div id="container">
        <%@ include file="./_header.jsp" %>
        <main>
            <section class="view">
                <h3>글보기</h3>
                <table>
                    <tr>
                        <td>제목</td>
                        <td><input type="text" name="title" value="${articleDto.title}" readonly/></td>
                    </tr>
                    <c:if test="${articleDto.file > 0}">
                    <tr>
                        <td>첨부파일</td>
                        <td>
                        <c:forEach var="file" items="${articleDto.files}">
	                        <p style="margin:4px 0">
	                            <a href="/jboard/file/download.do?fno=${file.fno}">${file.oName}</a>
	                            <span>${file.download}회 다운로드</span>
	                        </p>
                        </c:forEach>
                        </td>
                    </tr>
                    </c:if>
                    <tr>
                        <td>내용</td>
                        <td>
                            <textarea name="content" readonly>${articleDto.content}</textarea>
                        </td>
                    </tr>
                </table>
                <div>
                    <a href="#" class="btnDelete">삭제</a>
                    <a href="#" class="btnModify">수정</a>
                    <a href="/jboard/article/list.do" class="btnList">목록</a>
                </div>  
                
                <!-- 댓글리스트 -->
                <section class="commentList">
                    <h3>댓글목록</h3>
                    <c:forEach var="comment" items="${comments}">
	                    <article class="comment">
	                        <span>
	                            <span>${comment.rdate}</span>
	                            <span>${comment.nick}</span>
	                        </span>
	                        <textarea name="comment" readonly>${comment.content}</textarea>
	                        
	                        <c:if test="${sessUser.uid eq comment.writer}">
		                        <div>
		                        	<!-- HTML 사용자 정의 속성을 이용한 삭제/수정 -->
		                            <a href="#" class="commentRemove" data-no="${comment.no}">삭제</a>
		                            <a href="#" class="commentCancel" data-no="${comment.no}">취소</a><!-- style.css 364라인 display: none; 처리하기 -->
		                            <a href="#" class="commentModify" data-no="${comment.no}">수정</a>
		                        </div>
	                        </c:if>
	                    </article>
                    </c:forEach>
                    <c:if test="${empty comments}">
	                    <p class="empty">등록된 댓글이 없습니다.</p>
                    </c:if>
                </section>
    
                <!-- 댓글입력폼 -->
                <section class="commentForm">
                    <h3>댓글쓰기</h3>
                    <form name="commentForm">
                    	<input type="hidden" name="parent" value="${articleDto.no}"/>
                    	<input type="hidden" name="writer" value="${sessUser.uid}"/>
                        <textarea name="comment"></textarea>
                        <div>
                            <a href="#" class="btnCancel">취소</a>
                            <input type="submit" class="btnWrite" value="작성완료"/>
                        </div>
                    </form>
                </section>
    
            </section>
        </main>
        <%@ include file="./_footer.jsp" %>
    </div>
</body>
</html>