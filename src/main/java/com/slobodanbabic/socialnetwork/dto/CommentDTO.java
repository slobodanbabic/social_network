package com.slobodanbabic.socialnetwork.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentDTO implements Serializable {

	private Integer id;
	private String content;
	private Integer postId;
	private Integer userId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="M/d/yyyy, h:mm:ss a")//"9/23/2021, 9:47:40 AM"
	private LocalDateTime dateOfCreation;
	private String img_url;

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public LocalDateTime getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(LocalDateTime dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

}
