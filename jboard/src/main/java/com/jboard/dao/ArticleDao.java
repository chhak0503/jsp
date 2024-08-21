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
	
	public int insertArticle(ArticleDto dto) {
		int no = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			psmt.executeUpdate();
			
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			if(rs.next()) {
				no = rs.getInt(1);
			}
			conn.commit();
			
			closeAll();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return no;
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
