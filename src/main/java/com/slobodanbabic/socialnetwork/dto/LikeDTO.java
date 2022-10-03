package com.slobodanbabic.socialnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LikeDTO {

	private Integer id;
	@JsonProperty(value = "isLike")
	private boolean isLike;
	private Integer userId;
	private Integer postId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

}
