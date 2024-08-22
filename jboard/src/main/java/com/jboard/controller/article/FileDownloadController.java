package com.jboard.controller.article;

import java.io.IOException;

import com.jboard.dto.FileDto;
import com.jboard.service.FileService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/fileDownload.do")
public class FileDownloadController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FileService service = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fno = req.getParameter("fno");
		
		// 파일 조회
		FileDto fileDto = service.selectFile(fno);
		
		// 공유 참조(service -> fileDownload 메서드에서 getAttribute로 가져감)
		req.setAttribute("fileDto", fileDto);
		
		// 파일 다운로드 카운트 업데이트
		service.updateFileDownloadCount(fno);
		
		// 파일 다운로드
		service.fileDownload(req, resp);
	}
}








