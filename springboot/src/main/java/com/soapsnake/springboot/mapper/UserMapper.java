package com.soapsnake.springboot.mapper;

import com.soapsnake.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

	List<User> findAllUsers();

	void saveUser(User user);
}
