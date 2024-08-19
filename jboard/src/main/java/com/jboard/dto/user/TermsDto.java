package com.jboard.dto.user;

public class TermsDto {
	
	private String terms;
	private String privacy;
	
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	
	@Override
	public String toString() {
		return "TermsDto [terms=" + terms + ", privacy=" + privacy + "]";
	}
	
	

}
