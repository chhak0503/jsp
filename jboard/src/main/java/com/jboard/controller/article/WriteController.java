package com.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import com.jboard.dto.ArticleDto;
import com.jboard.dto.FileDto;
import com.jboard.service.ArticleService;
import com.jboard.service.FileService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArticleService articleService = ArticleService.INSTANCE;
	private FileService fileService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 데이터 수신
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		
		// 파일 업로드
		List<FileDto> files = fileService.fileUpload(req);
		
		// 글 등록을 위한 DTO 생성
		ArticleDto dto = new ArticleDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(files.size());
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		// 글 등록
		int no = articleService.insertArticle(dto);
		
		// 파일 등록
		for(FileDto fileDto : files) {
			fileDto.setAno(no);
			fileService.insertFile(fileDto);
		}
		
		resp.sendRedirect("/jboard/article/list.do");
	}
}
