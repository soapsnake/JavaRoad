package com.soapsnake.springboot.controller;

import com.soapsnake.springboot.domain.Contact;
import com.soapsnake.springboot.repo.ContactRepository;
import com.soapsnake.springboot.service.HelloService;
import com.soapsnake.springboot.service.KafkaProduerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by soapsnake on 2017/6/3.
 */

@Controller
@RequestMapping("/")
public class ContactController {

    @Autowired
    private ContactRepository contactRepo;

    @Autowired
    private HelloService helloService;

    @Resource
    private KafkaProduerService produerService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHome(Map<String, Object> model) {
        List<Contact> contacts = contactRepo.findAll();
        model.put("contacts", contacts);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveContact(Contact contact) {
        contactRepo.saveContact(contact);

        helloService.sayHello();
        return "redirect:/";
    }

    @RequestMapping(value = "send", method = RequestMethod.GET)
    @ResponseBody
    public String sendKafkaMsg() {
        return produerService.send();
    }


}
