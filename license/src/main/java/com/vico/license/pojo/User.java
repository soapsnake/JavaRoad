package com.vico.license.pojo;

import org.springframework.stereotype.Component;

@Component
public class User {
	
	private String username;
	private Integer userID;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
}
