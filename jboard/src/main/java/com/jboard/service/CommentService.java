package com.jboard.service;

import java.util.List;

import com.jboard.dao.CommentDao;
import com.jboard.dto.CommentDto;

public enum CommentService {
	
	INSTANCE;
	private CommentDao dao = CommentDao.getInstance();
	
	public int insertComment(CommentDto dto) {
		return dao.insertComment(dto);
	}
	public CommentDto selectComment(int no) {
		return dao.selectComment(no);
	}
	public List<CommentDto> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	public int updateComment(CommentDto dto) {
		return dao.updateComment(dto);
	}
	public int deleteComment(String no) {
		return dao.deleteComment(no);
	}
}
