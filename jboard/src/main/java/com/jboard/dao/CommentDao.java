package com.jboard.dao;

import java.util.ArrayList;
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
	
	public int insertComment(CommentDto dto) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			result = psmt.executeUpdate();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return result;
	}
	
	public CommentDto selectComment(int no) {
		return null;
	}
	public List<CommentDto> selectComments(String parent) {
		
		List<CommentDto> comments = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, parent);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CommentDto dto = new CommentDto();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setContent(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setRegip(rs.getString(5));
				dto.setRdate(rs.getString(6));
				comments.add(dto);
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		
		return comments;
	}
	
	public void updateComment(CommentDto dto) {
		
	}
	
	public void deleteComment(int no) {
		
	}
}
