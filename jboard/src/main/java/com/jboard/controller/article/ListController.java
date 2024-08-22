package com.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import com.jboard.dto.ArticleDto;
import com.jboard.dto.UserDto;
import com.jboard.service.ArticleService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleService service = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 조회
		List<ArticleDto> articles = service.selectArticles();
		
		// 공유 참조
		req.setAttribute("articles", articles);
		
		// 포워드
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/list.jsp");
		dispatcher.forward(req, resp);
	}
}








