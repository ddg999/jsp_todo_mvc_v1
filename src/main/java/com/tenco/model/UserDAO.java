package com.tenco.model;

import java.util.List;

public interface UserDAO {
	int addUser(UserDTO userDTO);

	UserDTO getUserById(int id);

	UserDTO getUserByUsername(String username);

	List<UserDTO> getAllUsers();

	// 권한 (내정보수정 -> 나만) - 인증(세션ID 필요)
	int updateUser(UserDTO user, int principalId);

	// 인증검사 필요
	int deleteUser(int id);
}
