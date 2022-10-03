package com.slobodanbabic.socialnetwork.service;

import java.util.List;

import com.slobodanbabic.socialnetwork.dto.CommentDTO;
import com.slobodanbabic.socialnetwork.dto.UserCommentI;

public interface CommentService extends CrudService<CommentDTO> {

	List<CommentDTO> findCommentByPostId(Integer postId);

	int findCountByPostId(int postId);

	List<UserCommentI> getTenUserCommentTheMost();
}
