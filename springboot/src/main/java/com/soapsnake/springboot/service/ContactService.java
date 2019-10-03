package com.soapsnake.springboot.service;

import com.soapsnake.springboot.pojo.User;

import java.util.List;

public interface ContactService {

	void anotherAction(User user);

	List<User> getAllUsers();

	void saveUser(User user);
}
