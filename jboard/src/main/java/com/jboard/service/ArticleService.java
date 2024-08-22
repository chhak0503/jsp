package com.jboard.service;

import java.util.List;

import com.jboard.dao.ArticleDao;
import com.jboard.dto.ArticleDto;

public enum ArticleService {

	INSTANCE;
	private ArticleDao dao = ArticleDao.getInstance();
	
	// 전체 게시물 갯수에서 마지막 페이지 번호 구하기
	public int getLastPageNum(int total) {		
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}
	
	
	public int insertArticle(ArticleDto dto) {
		return dao.insertArticle(dto);
	}
	
	public int selectCountTotal() {
		return dao.selectCountTotal();
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
