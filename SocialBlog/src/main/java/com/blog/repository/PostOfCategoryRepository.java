package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Category;
import com.blog.entity.PostOfCategory;

@Repository
public interface PostOfCategoryRepository extends JpaRepository<PostOfCategory,Long>  {
	
	List<PostOfCategory> findByCategory(Category category);

}
