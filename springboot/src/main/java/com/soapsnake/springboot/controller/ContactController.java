package com.soapsnake.springboot.controller;

import com.soapsnake.springboot.pojo.User;
import com.soapsnake.springboot.service.ContactService;
import com.soapsnake.springboot.service.HelloService;
import com.soapsnake.springboot.service.KafkaProduerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by soapsnake on 2017/6/3.
 */

@Controller
@RequestMapping("/")
public class ContactController {
    @Autowired
    private HelloService helloService;

    @Resource
    private ContactService contactService;

    @Resource
    private KafkaProduerService produerService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHome(Map<String, Object> model) {
        model.put("contacts", contactService.getAllUsers());
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveContact(User user) {
        contactService.saveUser(user);
        helloService.sayHello();
        return "redirect:/";
    }

    @RequestMapping(value = "send", method = RequestMethod.GET)
    @ResponseBody
    public String sendKafkaMsg() {
        return produerService.send();
    }


}
