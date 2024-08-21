package com.jboard.controller.article;

import java.io.IOException;

import com.jboard.dto.UserDto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserDto sessUser = (UserDto) session.getAttribute("sessUser");
		
		if(sessUser == null) {
			// 로그인을 하지 않으면 로그인 페이지로 이동
			resp.sendRedirect("/jboard/user/login.do?success=102");
		}else {
			// 로그인 상태
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/list.jsp");
			dispatcher.forward(req, resp);
		}
		
		
	}
	
}








