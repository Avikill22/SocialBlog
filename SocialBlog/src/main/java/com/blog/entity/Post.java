package com.blog.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer postId;
	
	@Column(name="title")
	@NotEmpty
	private String title;
	
	@Column(name="content")
	@Size(min=20, max=10000)
	private String content;
	
	private String imageName;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post", fetch=FetchType.EAGER)
	@JsonIgnore
	private List<PostOfCategory> PostOfCategory;
	
	@Column(name="date")
	private Date postDate;

	public Post() {
		super();
	}

	public Post(Integer postId, @NotEmpty String title, @Size(min = 20, max = 10000) String content, String imageName,
			User user) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.user = user;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public List<PostOfCategory> getPostOfCategory() {
		return PostOfCategory;
	}

	public void setPostOfCategory(List<PostOfCategory> postOfCategory) {
		PostOfCategory = postOfCategory;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	
}
