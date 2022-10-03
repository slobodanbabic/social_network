package com.slobodanbabic.socialnetwork.service;

import java.util.List;

import com.slobodanbabic.socialnetwork.dto.LikeDTO;

public interface LikeService extends CrudService<LikeDTO> {

	List<LikeDTO> findLikesByPostId(Integer postId);
	int findCountLikeByPostId(int postId);
	int findCountDislikeByPostId(int postId);
}
