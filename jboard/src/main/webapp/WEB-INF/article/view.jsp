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
    		
    		
    		// 댓글 등록
    		commentForm.onsubmit = function(e){
    			e.preventDefault();
    			
    			const parent = commentForm.parent.value;
    			const writer = commentForm.writer.value;
    			const comment = commentForm.comment.value;
    			
    			// 폼 데이터 생성
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
    					
    					if(data.result > 0){
    						alert('댓글이 등록되었습니다.');
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
	                            <a href="/jboard/article/fileDownload.do?fno=${file.fno}">${file.oName}</a>
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
                    <article class="comment">
                        <span>
                            <span>길동이</span>
                            <span>20-05-13</span>
                        </span>
                        <textarea name="comment" readonly>댓글 샘플입니다.</textarea>
                        <div>
                            <a href="#">삭제</a>
                            <a href="#">수정</a>
                        </div>
                    </article>
                    <p class="empty">
                        등록된 댓글이 없습니다.
                    </p>
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