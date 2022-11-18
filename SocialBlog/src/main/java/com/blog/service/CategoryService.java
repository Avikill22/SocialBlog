package com.blog.service;

import java.util.List;

import com.blog.entity.Category;

public interface CategoryService {

	List<Category> getAllCategory();
	Category getCategoryById(Integer categoryId);
	Category deletedCategory(Integer categoryId);
	Category updateCategory(Category category, Integer categoryId); 
	Category createCategory(Category category);
}
