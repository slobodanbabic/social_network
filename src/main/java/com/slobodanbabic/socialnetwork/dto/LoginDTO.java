package com.slobodanbabic.socialnetwork.dto;

import com.slobodanbabic.socialnetwork.entity.User;

public class LoginDTO {
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDTO() {
	}

	public LoginDTO(User user) {
		email = user.getEmail();
		password = user.getPassword();
	}
}