package com.jboard.controller.file;

import java.io.IOException;
import java.io.PrintWriter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.jboard.service.ArticleService;
import com.jboard.service.FileService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/file/delete.do")
public class FileDeleteController extends HttpServlet {

	private static final long serialVersionUID = 6309902834677709443L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private FileService service = FileService.INSTANCE;
	private ArticleService articleService = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파일번호 수신
		String fno = req.getParameter("fno");
		
		// 디렉터리의 파일과 데이터베이스의 파일정보 삭제 후 해당 파일 글번호 반환
		int ano = service.deleteFile(req, fno);
		
		// 해당 글의 file 컬럼 값을 -1 카운팅
		//articleService.updateArticleForFileCount(ano);
		
		// ajax로 요청했기 때문에 결과 JSON 출력(결과값은 파일의 글번호)
		JsonObject json = new JsonObject();
		json.addProperty("result", ano);
		
		// JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json);
	}
	
	
}





