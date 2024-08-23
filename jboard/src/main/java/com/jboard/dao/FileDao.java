package com.jboard.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dto.FileDto;
import com.jboard.util.DBHelper;
import com.jboard.util.SQL;

public class FileDao extends DBHelper {
	
	private static FileDao instance = new FileDao();
	public static FileDao getInstance() {
		return instance;
	}
	private FileDao() {}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertFile(FileDto dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getoName());
			psmt.setString(3, dto.getsName());
			psmt.executeUpdate();
			closeAll();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public FileDto selectFile(String fno) {
		
		FileDto fileDto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, fno);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				fileDto = new FileDto();
				fileDto.setFno(rs.getInt(1));
				fileDto.setAno(rs.getInt(2));
				fileDto.setoName(rs.getString(3));  
				fileDto.setsName(rs.getString(4));  
				fileDto.setDownload(rs.getInt(5));
				fileDto.setRdate(rs.getString(6));
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return fileDto;
	}
	
	public List<FileDto> selectFiles() {
		return null;
	}
	
	public void updateFile(FileDto dto) {		
	}
	
	public void updateFileDownloadCount(String fno) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_FILE_DOWNLOAD_COUNT);
			psmt.setString(1, fno);
			psmt.executeUpdate();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
	
	public FileDto deleteFile(String fno) {
		/*
		 * 삭제하기 전에 반드시 파일의 글 번호를 반환해야 함
		 * 반환된 파일의 글 번호를 가지고 해당 글의 file 컬럼 값을 -1 해줘야 함
		 */
		
		// 삭제할 파일의 글번호
		int ano = 0;
		String sname = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt_1 = conn.prepareStatement(SQL.SELECT_FILE_FOR_DELETE);
			psmt_1.setString(1, fno);
			logger.info("deleteFile : " + psmt_1);
			
			psmt_2 = conn.prepareStatement(SQL.DELETE_FILE);			
			psmt_2.setString(1, fno);
			logger.info("deleteFile : " + psmt_2);
			
			rs = psmt_1.executeQuery();
			psmt_2.executeUpdate();
			conn.commit();
			
			if(rs.next()) {
				ano = rs.getInt(1);
				sname = rs.getString(2);
			}
			
			closeAll();
		}catch (Exception e) {
			logger.error("deleteFile : " + e.getMessage());
		}
		
		return new FileDto(ano, sname);
	} 
	
}
