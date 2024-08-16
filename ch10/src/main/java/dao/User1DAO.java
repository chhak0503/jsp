package dao;

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
		return null;
	}
	
	public List<User1DTO> selectUser1s() {
		return null;
	}
	
	public void updateUser1(User1DTO dto) {
		
	}
	
	public void deleteUser1(String uid) {
		
	}
	
	
	
}
