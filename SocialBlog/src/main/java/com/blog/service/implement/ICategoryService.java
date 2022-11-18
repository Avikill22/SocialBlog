package com.blog.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exceptions.BusinessException;
import com.blog.repository.CategoryRepository;
import com.blog.service.CategoryService;

@Service
public class ICategoryService implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		if(!categories.isEmpty()) {
			return categories;
		}else {
			throw new BusinessException("No category is present.",600);
		}
	}
	

	@Override
	public Category getCategoryById(Integer categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);
		
		if(category.isPresent()) {
			return category.get();
		}else {
			throw new BusinessException("Category with this id Not present"+categoryId,600);
		}
	}

	@Override
	public Category deletedCategory(Integer categoryId) {
		
		Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
		
		if(categoryOptional.isPresent()) {
			Category category = categoryOptional.get(); 
			categoryRepository.delete(category);
			return category;
		}else {
			throw new BusinessException("Category with this id Not present"+categoryId,600);
		}
	}

	@Override
	public Category updateCategory(Category category, Integer categoryId) {
		
		Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
		
		if(categoryOptional.isPresent()) {
			categoryOptional.ifPresent(c -> c.setTitle(category.getTitle() == null?c.getTitle():category.getTitle()));
			categoryRepository.save(categoryOptional.get());
			return categoryOptional.get();
		}else {
			throw new BusinessException("Category with this id Not present"+categoryId,600);
		}
		
	}

	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

}
