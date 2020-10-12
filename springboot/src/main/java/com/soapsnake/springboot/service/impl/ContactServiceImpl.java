package com.soapsnake.springboot.service.impl;

import com.soapsnake.springboot.mapper.UserMapper;
import com.soapsnake.springboot.pojo.User;
import com.soapsnake.springboot.service.ContactService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ContactServiceImpl implements ContactService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)  //这里无论调用方是否有事务我都会加事务
//	@Transactional(isolation = Isolation.READ_COMMITTED)  //这里设置该session的隔离级别,这里设置会覆盖数据库默认
//	@Transactional(propagation = Propagation.NEVER)  //无论调用方是否有事务,我都不加事务
//	@Transactional(propagation = Propagation.MANDATORY) //如果调用方没有事务那别怪我抛异常
//	@Transactional(propagation = Propagation.NESTED)  //调用发无事务我就开新的,如果有那就会嵌套子事务
    public void anotherAction(User user) {
        user.setFirstName(user.getFirstName() + "-anot");
        user.setPhoneNumber(RandomStringUtils.randomNumeric(8));
        userMapper.saveUser(user);
        throw new RuntimeException("测试回滚");
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);

        //实测这样可行,不过pom要加依赖,而且下面的注解要加到Application上
        //@EnableAspectJAutoProxy(proxyTargetClass=true, exposeProxy=true)
        ContactService proxy = (ContactService) (AopContext.currentProxy());
        proxy.anotherAction(user);
//		this.anotherAction(user);  //这种this方式调用肯定不行了,死心吧,除非B挪到另外一个类
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }
}
