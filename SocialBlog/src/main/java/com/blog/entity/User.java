package com.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")

public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	@Column(name = "user_name", nullable = false, length=100)
	@NotEmpty
	private String name;
	
	@Email(message= "Email address is not valid!!")
	@Column(unique=true)
	private String emailId;
	
	@NotEmpty
	@Size(min=6, max = 20, message= "password must be minimum 3 and 10 characters!!")
	private String password;
	
	@NotEmpty
	@Size(min=10, message ="Description should be more thatn 10 character")
	private String about;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Post> posts = new ArrayList<>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(Integer userId, String name, String emailId, String password, String about) {
		super();
		this.userId = userId;
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.about = about;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
}
