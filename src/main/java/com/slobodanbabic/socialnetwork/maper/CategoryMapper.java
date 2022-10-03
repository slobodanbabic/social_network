package com.slobodanbabic.socialnetwork.maper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.slobodanbabic.socialnetwork.dto.CategoryDTO;
import com.slobodanbabic.socialnetwork.entity.Category;

@Component
public class CategoryMapper {

	public CategoryDTO transformToDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		BeanUtils.copyProperties(category, categoryDTO);
		return categoryDTO;
	}

	public Category transformToEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDTO, category);
		return category;
	}
}
