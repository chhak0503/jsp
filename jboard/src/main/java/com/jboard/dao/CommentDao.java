package com.jboard.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dto.CommentDto;
import com.jboard.util.DBHelper;
import com.jboard.util.SQL;


public class CommentDao extends DBHelper {

	private static CommentDao instance = new CommentDao();
	public static CommentDao getInstance() {
		return instance;
	}
	private CommentDao() {}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertComment(CommentDto dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			psmt.executeUpdate();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
	
	public CommentDto selectComment(int no) {
		return null;
	}
	public List<CommentDto> selectComments() {
		return null;
	}
	
	public void updateComment(CommentDto dto) {
		
	}
	
	public void deleteComment(int no) {
		
	}
}
