package com.jboard.service.user;

import java.util.List;

import com.jboard.dao.user.UserDao;
import com.jboard.dto.user.UserDto;

public enum UserService {

	INSTANCE;
	
	private UserDao dao = UserDao.getInstance();
	
	public void insertUser(UserDto dto) {
		dao.insertUser(dto);
	}
	public UserDto selectUser(String uid) {
		return dao.selectUser(uid);
	}
	public List<UserDto> selectUsers() {
		return dao.selectUsers();
	}
	public void updateUser(UserDto dto) {
		dao.updateUser(dto);
	}
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
}
