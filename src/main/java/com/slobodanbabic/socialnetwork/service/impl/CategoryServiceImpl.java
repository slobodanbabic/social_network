package com.slobodanbabic.socialnetwork.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slobodanbabic.socialnetwork.dto.CategoryDTO;
import com.slobodanbabic.socialnetwork.dto.PostCountI;
import com.slobodanbabic.socialnetwork.entity.Category;
import com.slobodanbabic.socialnetwork.maper.CategoryMapper;
import com.slobodanbabic.socialnetwork.repository.CategoryRepository;
import com.slobodanbabic.socialnetwork.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public CategoryDTO findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDTO> findAll() {
		return categoryRepository.findAll().stream().map(i -> categoryMapper.transformToDTO(i))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO save(CategoryDTO dto) {
		Category category = categoryMapper.transformToEntity(dto);
		return categoryMapper.transformToDTO(categoryRepository.save(category));
	}

	@Override
	public void remove(Integer id) throws IllegalArgumentException {
		categoryRepository.deleteById(id);
	}

	public CategoryDTO removeById(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (!category.isPresent()) {
			throw new IllegalArgumentException("Category with the following id = " + id + " is not found.");
		}
		remove(id);
		return categoryMapper.transformToDTO(category.get());
	}

	@Override
	public CategoryDTO update(Integer id, CategoryDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostCountI> getCountTotalPostsByCategory() {
		return categoryRepository.countTotalPostsByCategory();
	}

}
