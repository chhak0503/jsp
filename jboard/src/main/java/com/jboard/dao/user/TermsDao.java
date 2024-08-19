package com.jboard.dao.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dto.user.TermsDto;
import com.jboard.util.DBHelper;
import com.jboard.util.SQL;

public class TermsDao extends DBHelper {

	private static TermsDao instance = new TermsDao();
	public static TermsDao getInstance() {
		return instance;
	}
	private TermsDao() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void insertTerms(TermsDto dto) {
		
	}
	public TermsDto selectTerms() {
		
		TermsDto dto = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			
			if(rs.next()) {
				dto = new TermsDto();
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
			closeAll();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dto;
	}
	public List<TermsDto> selectTermses() {
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void updateTerms(TermsDto dto) {}
	public void deleteTerms() {}
	
}
