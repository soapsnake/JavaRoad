package com.vico.license.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.vico.license.aop.TokenManager;
import com.vico.license.dao.UserDao;
import com.vico.license.pojo.DataTableRequest;
import com.vico.license.pojo.User;
import com.vico.license.pojo.UserByPage;


@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImp implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	private TokenManager tokenManager;
	
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
		result.setRecordsFiltered(filterRecordsTotal);	
		System.out.println("service>>>>>>>>>>"+JSON.toJSONString(result));
		return result;
	}

	@Override
	public int modifyUserByID(User user) {
		// TODO Auto-generated method stub
		int res = 0;
		
		try {
			res = userDao.modifyUserByID(user);
			//测试spring事务:
			//userDao.addUser(user);  //添加该user会失败,因为ID和原来的一样,抛出异常应该回滚,上一条sql不应该执行
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();   //关键语句,只有抛出这个运行时异常,事务才会发生回滚
		}
		return res;
	}

	@Override
	public int userLogin(User user) {
		// TODO Auto-generated method stub
		int usergroup = -1;
		User user2 = userDao.getUserByName(user.getUsername());
		
		try {
			if(user2 != null){
			if(user2.getPassword().equals(user.getPassword())){
				usergroup = user2.getUsergroup();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return usergroup;
	}

	@Override
	public String createToken(String username) {
		String token = "";
		token = tokenManager.createToken(username);
		return token;
	}

}
