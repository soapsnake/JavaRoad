package com.soapsnake.springboot.service;

import java.util.List;

import com.soapsnake.springboot.pojo.User;

public interface ContactService {

    void anotherAction(User user);

    List<User> getAllUsers();

    void saveUser(User user);
}
