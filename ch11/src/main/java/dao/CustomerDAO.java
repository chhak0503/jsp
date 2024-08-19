package dao;

import java.util.List;

import dto.CustomerDTO;
import util.DBHelper;

public class CustomerDAO extends DBHelper {
	
	private static CustomerDAO instance = new CustomerDAO();
	public static CustomerDAO getInstance() {
		return instance;
	}
	private CustomerDAO() {}
	
	public void insertCustomer(CustomerDTO dto) {
		
		String sql = "insert into customer values (?,?,?,?,?)";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getCustId());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setString(4, dto.getAddr());
			psmt.setString(5, dto.getRdate());
			psmt.executeUpdate();
			closeAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public CustomerDTO selectCustomer(String custId) {
		return null;
	}
	
	public List<CustomerDTO> selectCustomers() {
		return null;
	}
	
	public void updateCustomer(CustomerDTO dto) {
		
	}
	
	public void deleteCustomer(String custId) {
		
	}
}
