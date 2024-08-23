<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글수정</title>
    <link rel="stylesheet" href="/jboard/css/style.css">    
</head>
<body>
    <div id="container">
        <%@ include file="./_header.jsp" %>
        <main>
            <section class="write">
                <h3>글쓰기</h3>
                <article>
                	<!-- 
                		multipart/form-data로 데이터 및 파일 전송을 위해 톰캣 설정해야됨 안하면 getParameter 수신 안됨
                		 - 톰캣 context.xml > Context 태그에 allowCasualMultipartParsing="true" 설정
						 - 톰캣 server.xml > Connector 태그에 maxPostSize="10485760" (1024 x 1024 x 10 = 10MB) 설정                		
                	-->
                    <form action="/jboard/article/write.do" method="post" enctype="multipart/form-data">
                    	<input type="hidden" name="writer" value="${sessUser.uid}">
                        <table>
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" placeholder="제목을 입력하세요."/></td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td>
                                    <textarea name="content"></textarea>                                
                                </td>
                            </tr>
                            <tr>
                                <td>첨부</td>
                                <td>
                                	<p style="margin-bottom: 6px;">
                                		최대 2개 파일 첨부 가능, 각 파일당 최대 10MB까지 가능
                                	</p>
                                	<input type="file" name="file1"/>
                                	<input type="file" name="file2"/>                                	
                                </td>
                            </tr>
                        </table>
                        <div>
                            <a href="/jboard/article/list.do" class="btnCancel">취소</a>
                            <input type="submit"  class="btnWrite" value="작성완료">
                        </div>
                    </form>
                </article>
            </section>
        </main>
        <%@ include file="./_footer.jsp" %>
    </div>
</body>
</html>