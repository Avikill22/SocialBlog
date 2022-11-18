package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Category;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<Category> createCategory(@RequestBody Category categeory){
		return new ResponseEntity<Category> (categoryService.createCategory(categeory),HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable(value="categoryId") Integer userId){
		return new ResponseEntity<Category>(categoryService.updateCategory(category, userId),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> getCategories(){
		return new ResponseEntity<List<Category>> (categoryService.getAllCategory(),HttpStatus.FOUND);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoriesById(@PathVariable Integer categoryId){
		return new ResponseEntity<Category> (categoryService.getCategoryById(categoryId),HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Category> deletedCategory(@PathVariable Integer categoryId){
		return new ResponseEntity<Category>(categoryService.deletedCategory(categoryId),HttpStatus.OK);
	}
}
