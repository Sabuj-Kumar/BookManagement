package com.bookManagementSystem.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.bookManagementSystem.enums.Role;

public class UserDto {

	private Integer id;
	private String userName;
	private String email;
	private Role role;
	private String fullName;
	private Date createdDate;
	private List<BookDto> bookDto = new ArrayList<BookDto>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<BookDto> getBooks() {
		return bookDto;
	}
	public void setBooks(List<BookDto> books) {
		this.bookDto = books;
	}
}
