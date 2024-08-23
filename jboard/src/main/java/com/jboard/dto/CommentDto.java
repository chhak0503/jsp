package com.jboard.dto;

public class CommentDto {
	private int no;
	private int parent;
	private String content;
	private String writer;
	private String regip;
	private String rdate;
	
	// 추가필드
	private String nick;
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setNo(String no) {
		this.no = Integer.parseInt(no);
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public void setParent(String parent) {
		this.parent = Integer.parseInt(parent);
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public void setRdateSubstring(String rdate) {
		this.rdate = rdate.substring(0, 10);
	}
	@Override
	public String toString() {
		return "CommentDto [no=" + no + ", parent=" + parent + ", content=" + content + ", writer=" + writer
				+ ", regip=" + regip + ", rdate=" + rdate + "]";
	}
	
	
}
