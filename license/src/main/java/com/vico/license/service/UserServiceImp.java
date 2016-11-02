package com.vico.license.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.vico.license.dao.UserDao;
import com.vico.license.pojo.DataTableRequest;
import com.vico.license.pojo.User;
import com.vico.license.pojo.UserByPage;


@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> SelectAllUsers() {
		
		List<User> users = new ArrayList<User>();
		
		users = userDao.selectAllUsers();
		
		return users;
	}

	@Override
	public int addUser(User user) {
		
		int res = 0;
		res = userDao.addUser(user);
		return res;
	}

	@Override
	public UserByPage getUserByPage(DataTableRequest request) {
		Integer length = 0;
		Integer start = 0;
		Integer draw = 0;
		Integer recordsTotal = 0;
		Integer filterRecordsTotal = 0;
		List<User> users = new ArrayList<User>();
		
		length = request.getLength();
		start = request.getStart();
		draw = request.getDraw();
		try {
			users = userDao.getUserByPage(length,start);
			recordsTotal = userDao.getTotal();
			filterRecordsTotal = recordsTotal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserByPage result = new UserByPage();
		result.setDraw(draw);
		result.setData(users);
		result.setRecordsTotal(recordsTotal);
		result.setRecordsFilterted(filterRecordsTotal);	
		System.out.println("service>>>>>>>>>>"+JSON.toJSONString(result));
		return result;
	}

}
