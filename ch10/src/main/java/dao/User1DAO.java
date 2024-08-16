package dao;

import java.util.ArrayList;
import java.util.List;

import dto.User1DTO;
import util.DBHelper;

public class User1DAO extends DBHelper {

	//싱글톤
	private static User1DAO instance = new User1DAO();
	public static User1DAO getInstance() {
		return instance;
	}
	private User1DAO() {}
	
	// 기본 CRUD 메서드
	public void insertUser1(User1DTO dto) {
		
		String sql = "insert into `user1` values (?,?,?,?,?)";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getBirth());
			psmt.setString(4, dto.getHp());
			psmt.setInt(5, dto.getAge());
			
			psmt.executeUpdate();
			closeAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User1DTO selectUser1(String uid) {
		
		User1DTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `user1` where `uid`=?");
			psmt.setString(1, uid);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new User1DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirth(rs.getString(3));
				dto.setHp(rs.getString(4));
				dto.setAge(rs.getInt(5));
			}
			closeAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public List<User1DTO> selectUser1s() {
		
		List<User1DTO> users = new ArrayList<User1DTO>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `user1`");
			
			while(rs.next()) {
				
				User1DTO dto = new User1DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirth(rs.getString(3));
				dto.setHp(rs.getString(4));
				dto.setAge(rs.getInt(5));
				
				users.add(dto);
			}
			closeAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void updateUser1(User1DTO dto) {
		
		String sql = "update user1 set `name`=?, `birth`=?, `hp`=?, `age`=? where `uid`=?";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getBirth());
			psmt.setString(3, dto.getHp());
			psmt.setInt(4, dto.getAge());
			psmt.setString(5, dto.getUid());
			psmt.executeUpdate();
			closeAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser1(String uid) {

		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `user1` where `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			closeAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}













