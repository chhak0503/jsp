package com.jboard.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBHelper {
	
	protected Connection conn = null;
	protected PreparedStatement psmt = null;
	protected PreparedStatement psmt_1 = null;
	protected PreparedStatement psmt_2 = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	
	protected Connection getConnection() throws NamingException, SQLException {
		Context ctx = (Context) new InitialContext().lookup("java:comp/env");
		DataSource ds = (DataSource) ctx.lookup("jdbc/jboard");
		return ds.getConnection();
	}
	
	protected void closeAll() {
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(psmt_1 != null) {
			try {
				psmt_1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		if(psmt_2 != null) {
			try {
				psmt_2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}










