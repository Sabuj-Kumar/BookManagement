package com.bookManagementSystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bookManagementSystem.dto.UserDto;

public interface UserService {

	UserDetailsService userDetailService();
	UserDto updateUser(UserDto userDto,Integer id);
	UserDto getUserById(Integer userid);
	void deleteUser(Integer id);
	Boolean deleteBookFromUser(Integer userId, Integer bookId);
	UserDto getUserByEail(String email);
	void addBook(Integer userId, Integer bookId);
}
