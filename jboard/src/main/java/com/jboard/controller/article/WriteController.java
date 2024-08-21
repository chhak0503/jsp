package com.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import com.jboard.dao.UserDao;
import com.jboard.dto.ArticleDto;
import com.jboard.dto.FileDto;
import com.jboard.dto.UserDto;
import com.jboard.service.ArticleService;
import com.jboard.service.FileService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		
		ArticleDto dto = new ArticleDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		// 글 등록
		int no = articleService.insertArticle(dto);
	
		// 파일 업로드
		List<FileDto> files = fileService.fileUpload(req);
		
		for(FileDto fileDto : files) {
			fileDto.setAno(no);
			fileService.insertFile(fileDto);	
		}
		
		resp.sendRedirect("/jboard/article/list.do");
	}
}
