package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.PostOfCategory;

@Repository
public interface PostOfCategoryRepository extends JpaRepository<PostOfCategory,Long>  {

}
