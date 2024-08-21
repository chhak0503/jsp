package com.jboard.service;

import java.util.List;

import com.jboard.dao.ArticleDao;
import com.jboard.dto.ArticleDto;

public enum ArticleService {

	INSTANCE;
	
	private ArticleDao dao = ArticleDao.getInstance();
	
	public int insertArticle(ArticleDto dto) {
		return dao.insertArticle(dto);
	}
	
	public ArticleDto selectArticle(int no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleDto> selectArticles() {
		return dao.selectArticles();
	}
	
	public void updateArticle(ArticleDto dto) {
		dao.updateArticle(dto);
	}	
	
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}	
}
