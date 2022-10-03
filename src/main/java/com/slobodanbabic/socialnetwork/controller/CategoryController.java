package com.slobodanbabic.socialnetwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slobodanbabic.socialnetwork.dto.CategoryDTO;
import com.slobodanbabic.socialnetwork.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;

	@PostMapping(value = "/addCategory", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDTO saveCategory(@RequestBody CategoryDTO categorytDTO) {		
		return categoryServiceImpl.save(categorytDTO);
	}
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryDTO> getAllCategory() {
		return categoryServiceImpl.findAll();
	}
	
	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDTO deleteCategory(@PathVariable("id") Integer id) {
		return categoryServiceImpl.removeById(id);
	}
}
