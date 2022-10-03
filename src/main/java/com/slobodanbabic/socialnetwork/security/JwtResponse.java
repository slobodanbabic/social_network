package com.slobodanbabic.socialnetwork.security;

public class JwtResponse {
	private String token;
	// private String type = "Bearer";
	private Integer id;
	private String name;
	private String surname;
	private String email;
	private String img_url;
	private String roleName;

	public JwtResponse(String token, Integer id, String name, String surname, String email, String img_url,
			String roleName) {
		super();
		this.token = token;
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.img_url = img_url;
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
