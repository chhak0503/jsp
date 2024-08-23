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

@WebServlet("/comment/modify.do")
public class ModifyController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CommentService service = CommentService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		String comment = req.getParameter("comment");
		
		CommentDto dto = new CommentDto();
		dto.setNo(no);
		dto.setContent(comment);
		logger.debug(dto.toString());
		
		int result = service.updateComment(dto);
	
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json);
	}
	
}








