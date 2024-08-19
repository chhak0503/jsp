package com.jboard.service.user;

import java.util.List;

import com.jboard.dao.user.TermsDao;
import com.jboard.dto.user.TermsDto;

public class TermsService {

	private static TermsService instance = new TermsService();
	public static TermsService getInstance() {
		return instance;
	}
	private TermsService() {}
	
	private TermsDao dao = TermsDao.getInstance();
	
	
	public void insertTerms(TermsDto dto) {
		dao.insertTerms(dto);
	}
	public TermsDto selectTerms() {
		return dao.selectTerms();
	}
	public List<TermsDto> selectTermses() {		
		return dao.selectTermses();
	}
	public void updateTerms(TermsDto dto) {
		dao.updateTerms(dto);
	}
	public void deleteTerms() {
		dao.deleteTerms();
	}
}
