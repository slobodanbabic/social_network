package com.slobodanbabic.socialnetwork.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDTO implements Serializable {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String roleName;
	private String img_url;
	private String token;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="M/d/yyyy, h:mm:ss a")//"9/23/2021, 9:47:40 AM"
	private LocalDateTime ban;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getBan() {
		return ban;
	}

	public void setBan(LocalDateTime ban) {
		this.ban = ban;
	}

}
