package com.bookManagementSystem.payload;

public class ChangPassRequest {

	private String email;
	private String prePassword;
	private String newPassword;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPrePassword() {
		return prePassword;
	}
	public void setPrePassword(String prePassword) {
		this.prePassword = prePassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
