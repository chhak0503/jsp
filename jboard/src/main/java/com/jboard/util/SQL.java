package com.jboard.util;

public class SQL {
	
	
	public static final String SELECT_TERMS = "select * from terms";
	
	// user
	public static final String SELECT_COUNT_USER = "SELECT COUNT(*) FROM `user` ";
	public static final String WHERE_UID  = "WHERE `uid`=?";
	public static final String WHERE_NICK = "WHERE `nick`=?";
	public static final String WHERE_EMAIL = "WHERE `email`=?";
	public static final String WHERE_HP = "WHERE `hp`=?";
	
	public static final String SELECT_USER = "select * from `user` where `uid`=? and `pass`=SHA2(?, 256)";
	public static final String INSERT_USER = "insert into user set "
											+ "`uid`=?,"
											+ "`pass`=SHA2(?, 256),"
											+ "`name`=?,"
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`regip`=?,"
											+ "`regDate`=NOW()";
	
	// article
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `article`";
	public static final String SELECT_ARTICLES = "SELECT a.*, b.nick  from `article` AS a "
												+ "JOIN `user` AS b ON a.writer = b.uid "
												+ "ORDER BY `no` DESC";
	
	public static final String SELECT_MAX_NO = "select MAX(`no`) from `article`";
	public static final String INSERT_ARTICLE = "insert into article set "
												+ "`title`=?,"
												+ "`content`=?,"
												+ "`file`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	// file
	public static final String INSERT_FILE = "insert into file set "
											+ "`ano`=?,"
											+ "`oName`=?,"
											+ "`sName`=?,"
											+ "`rdate`=NOW()";
	
}










