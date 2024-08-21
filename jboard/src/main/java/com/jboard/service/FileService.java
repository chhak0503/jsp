package com.jboard.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public enum FileService {

	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	public void fileUpload(HttpServletRequest req) {
		
		ServletContext ctx = req.getServletContext();
		String uploadPath = ctx.getRealPath("/uploads");
		logger.debug("uploadPath : " + uploadPath);
		
		try {
			Collection<Part> parts = req.getParts(); // 첨부 파일 정보객체 가져오기
			
			for(Part part : parts) {
				// 파일명 추출
				String ofileName = part.getSubmittedFileName();
				
				// 파일을 첨부했으면
				if(ofileName != null && !ofileName.isEmpty()) {
					logger.debug("ofileName : " + ofileName);
					
					// 고유 파일명 생성
					int idx = ofileName.lastIndexOf(".");
					String ext = ofileName.substring(idx);
					
					String sfileName = UUID.randomUUID().toString() + ext;
					logger.debug("sfileName : " + sfileName);
					
					// 파일 저장
					part.write(uploadPath + File.separator + sfileName);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void fileDownload() {
		
	}
	
}
