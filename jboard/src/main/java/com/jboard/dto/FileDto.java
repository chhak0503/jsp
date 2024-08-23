package com.jboard.dto;

public class FileDto {
	
	private int fno;
	private int ano;
	private String oName;
	private String sName;
	private int download;
	private String rdate;
	
	public FileDto() {
		
	}
	
	public FileDto(int ano, String sName) {
		this.ano = ano;
		this.sName = sName;
	}
	
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	@Override
	public String toString() {
		return "FileDto [fno=" + fno + ", ano=" + ano + ", oName=" + oName + ", sName=" + sName + ", download="
				+ download + ", rdate=" + rdate + "]";
	}
	
	
	
	
}
