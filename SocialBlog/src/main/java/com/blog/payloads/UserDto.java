package com.blog.payloads;

public class UserDto {
	
	private String name;
	
	private String emailId;
	
	private String password;
	
	private String about;

	public UserDto(String name, String emailId, String password, String about) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.about = about;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
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

}
