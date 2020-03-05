package com.soapsnake.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.soapsnake.springboot.pojo.User;

@Mapper
public interface UserMapper {

    List<User> findAllUsers();

    void saveUser(User user);
}
