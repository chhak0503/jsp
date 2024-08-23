package com.jboard.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dao.FileDao;
import com.jboard.dto.ArticleDto;
import com.jboard.dto.FileDto;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public enum FileService {

	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private FileDao dao = FileDao.getInstance();
	
	public void insertFile(FileDto dto) {
		dao.insertFile(dto);
	}
	
	public FileDto selectFile(String fno) {
		return dao.selectFile(fno);
	}
	
	public List<FileDto> selectFiles() {
		return dao.selectFiles();
	}
	
	public void updateFile(FileDto dto) {
		dao.updateFile(dto);
	}
	public void updateFileDownloadCount(String fno) {
		dao.updateFileDownloadCount(fno);
	}

	/*
	public void deleteFile(int fno) {
		dao.deleteFile(fno);
	}
	*/
	public int deleteFile(HttpServletRequest req, String fno) {
		
		FileDto fileDTO = dao.deleteFile(fno);
		
		// 업로드 디렉토리 파일 삭제
		ServletContext ctx = req.getServletContext();
		String uploadPath = ctx.getRealPath("/uploads"); 
		
		// 파일 객체 생성
		File file = new File(uploadPath + File.separator + fileDTO.getsName());
		
		// 파일 삭제
		if(file.exists()) {
			file.delete();
		}
		
		return fileDTO.getAno();
	} 
	
	/*
	 * Java-17, Tomcat10.1.x 환경 fileUpload
	 */
	public List<FileDto> fileUpload(HttpServletRequest req) {
		
		List<FileDto> files = new ArrayList<>();
		
		ServletContext ctx = req.getServletContext();
		String uploadPath = ctx.getRealPath("/uploads");
		logger.debug("uploadPath : " + uploadPath);
		
		try {
			// 첨부 파일 정보객체 가져오기
			Collection<Part> parts = req.getParts();
			
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
					
					// fileDTO 생성 후 리스트 저장
					FileDto fileDto = new FileDto();
					fileDto.setoName(ofileName);
					fileDto.setsName(sfileName);
					files.add(fileDto);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return files;
	}
	
	/*
		Java 8, Tomcat9.x 환경 fileUpload
	*/
	/*
	public ArticleDto fileUpload2(HttpServletRequest req) {
		// 파일 업로드 경로 설정
		ServletContext ctx = req.getServletContext();
		String uploadPath = ctx.getRealPath("/uploads");
		
		// 파일 업로드 처리 객체 새성
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 최대 파일 크기 설정
		upload.setSizeMax(1024 * 1024 * 10); // 10MB
		
		// ArticleDTO 생성
		ArticleDto articleDTO = new ArticleDto(); 
		
		// 파일 DTO 리스트 생성
		List<FileDto> fileDTOs = new ArrayList<>();
		
		// 파일 업로드 스트림 처리
		try {
			List<FileItem> items = upload.parseRequest(req);
			
			// 첨부파일 갯수
			int count = 0;
			
			for(FileItem item : items) {
				
				logger.debug("item : " + item);
				
				if(!item.isFormField()) {
					// 첨부 파일일 경우
					if(!item.getName().isEmpty()) {
						count++;
											
						String fname = item.getName();
						int idx = fname.lastIndexOf(".");
						String ext = fname.substring(idx);
						
						String saveName = UUID.randomUUID().toString() + ext;
						
						FileDto fileDTO = new FileDto();
						fileDTO.setoName(fname);
						fileDTO.setsName(saveName);
						fileDTOs.add(fileDTO);
						
						File file = new File(uploadPath + File.separator + saveName);
						item.write(file);
					}
				}else {
					// 폼 데이터일 경우
					String fieldName  = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					
					if(fieldName.equals("title")) {
						articleDTO.setTitle(fieldValue);
					}else if(fieldName.equals("content")) {
						articleDTO.setContent(fieldValue);
					}else if(fieldName.equals("writer")) {
						articleDTO.setWriter(fieldValue);
					}else if(fieldName.equals("no")) {
						articleDTO.setNo(fieldValue);
					}
				}
			}
			articleDTO.setFile(count);
		}catch (Exception e) {
			logger.error("fileUpload : " + e.getMessage());
		}
		
		articleDTO.setFiles(fileDTOs);
		
		return articleDTO;
	}
	*/
	
	public void fileDownload(HttpServletRequest req, HttpServletResponse resp) {
		
		// 공유 참조값 가져오기
		FileDto fileDto = (FileDto) req.getAttribute("fileDto");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			// response 헤더정보 수정
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileDto.getoName(), "utf-8"));
			resp.setHeader("Content-Transfer-Encoding", "binary");
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "private");
	
			// 파일 내용 스트림 처리
			ServletContext ctx = req.getServletContext();
			String path = ctx.getRealPath("/uploads");
			File file = new File(path + File.separator + fileDto.getsName());
			
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(resp.getOutputStream());
			
			bis.transferTo(bos);
			bos.flush();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			
			try {
				bos.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
