package com.slobodanbabic.socialnetwork.service;

import java.util.List;

import com.slobodanbabic.socialnetwork.dto.CategoryDTO;
import com.slobodanbabic.socialnetwork.dto.PostCountI;

public interface CategoryService extends CrudService<CategoryDTO> {

	List<PostCountI> getCountTotalPostsByCategory();
}
