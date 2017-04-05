package com.vico.license.controller;

import com.ld.wsdl.server.UserInfo;
import com.ld.wsdl.server.WebService001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kevin on 2017/4/3.
 */

@RestController
@RequestMapping("webServiceTest")
public class ws {

    @Autowired
    private WebService001 webServiceBean;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public UserInfo test(@RequestParam("uname")String username,@RequestParam("age")int age){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(username);
        userInfo.setUserAge(age);
        return webServiceBean.testWebService(userInfo);
    }
}
