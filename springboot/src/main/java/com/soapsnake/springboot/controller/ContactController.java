package com.soapsnake.springboot.controller;

import com.soapsnake.springboot.domain.Contact;
import com.soapsnake.springboot.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(method = RequestMethod.GET)
    public String getHome(Map<String, Object> model) {
        List<Contact> contacts = contactRepo.findAll();
        model.put("contacts", contacts);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveContact(Contact contact) {
        contactRepo.saveContact(contact);
        return "redirect:/";
    }


}
