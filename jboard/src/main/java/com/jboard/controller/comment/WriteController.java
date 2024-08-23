package com.jboard.controller.comment;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jboard.dto.CommentDto;
import com.jboard.service.CommentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/comment/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CommentService service = CommentService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 폼 데이터 수신
		String parent = req.getParameter("parent");
		String comment = req.getParameter("comment");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		
		// DTO 생성
		CommentDto dto = new CommentDto();
		dto.setParent(parent);
		dto.setContent(comment);
		dto.setWriter(writer);
		dto.setRegip(regip);
		logger.debug(dto.toString());
		
		// 댓글 등록
		int pk = service.insertComment(dto);
		
		// 방금 등록한 댓글 조회
		CommentDto commentDto = service.selectComment(pk);
		
		// JSON 생성 및 출력
		Gson gson = new Gson();
		String json = gson.toJson(commentDto);
		
		PrintWriter printWriter = resp.getWriter();
		printWriter.print(json);
	}
	
}








