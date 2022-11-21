package com.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="Categories")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(nullable=false)
	@NotEmpty
	private String title;
	
	@ManyToMany(mappedBy="categories", fetch=FetchType.LAZY)
	@JsonBackReference
	private List<Post> posts = new ArrayList<>();

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(Integer categoryId, @NotEmpty String title) {
		super();
		this.categoryId = categoryId;
		this.title = title;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
