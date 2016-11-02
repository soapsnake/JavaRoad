package com.vico.license.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.vico.license.pojo.User;

@Component
public interface UserDao {
	
	List<User> selectAllUsers();

	int addUser(User user);

	List<User> getUserByPage(@Param("length")Integer length, @Param("start")Integer start);

	Integer getTotal();
}
