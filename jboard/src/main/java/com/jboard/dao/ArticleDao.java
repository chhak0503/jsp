package com.jboard.dao;

import java.sql.SQLException;
import java.util.ArrayList;
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
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate();
			
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			if(rs.next()) {
				no = rs.getInt(1);
			}
			conn.commit();	
			
		}catch (Exception e) {
			logger.error(e.getMessage());
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage());
			}
			
		}finally {
			closeAll();
		}
		
		return no;
	}
	
	public ArticleDto selectArticle(int no) {
		return null;
	}
	
	public List<ArticleDto> selectArticles() {
		
		List<ArticleDto> articles = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDto dto = new ArticleDto();
				dto.setNo(rs.getInt(1));
				dto.setCate(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setComment(rs.getInt(5));
				dto.setFile(rs.getInt(6));
				dto.setHit(rs.getInt(7));
				dto.setWriter(rs.getString(8));
				dto.setRegip(rs.getString(9));
				dto.setRdateSubString(rs.getString(10));
				dto.setNick(rs.getString(11));
				articles.add(dto);
			}
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		
		return articles;
	}
	
	public void updateArticle(ArticleDto dto) {
		
	}	
	
	public void deleteArticle(int no) {
		
	}	
}
