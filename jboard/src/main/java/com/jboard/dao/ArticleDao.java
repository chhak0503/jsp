package com.jboard.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dto.ArticleDto;
import com.jboard.util.DBHelper;
import com.jboard.util.SQL;

public class ArticleDao extends DBHelper {

	private static ArticleDao instance = new ArticleDao();
	public static ArticleDao getInstance() {
		return instance;
	}
	private ArticleDao() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertArticle(ArticleDto dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			psmt.executeUpdate();
			closeAll();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public ArticleDto selectArticle(int no) {
		return null;
	}
	
	public List<ArticleDto> selectArticles() {
		return null;
	}
	
	public void updateArticle(ArticleDto dto) {
		
	}	
	
	public void deleteArticle(int no) {
		
	}	
}
