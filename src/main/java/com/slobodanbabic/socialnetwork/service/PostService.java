package com.slobodanbabic.socialnetwork.service;

import java.util.List;

import com.slobodanbabic.socialnetwork.dto.PostDTO;
import com.slobodanbabic.socialnetwork.dto.PostPerMonth;

public interface PostService extends CrudService<PostDTO> {

	List<PostDTO> findByCategoryId(int categoryId);

	List<PostDTO> findAllByOrderByDateOfCreationDesc();

	public List<PostPerMonth> findPostsCountPerMonth(String dateFrom, String dateTo);
}
