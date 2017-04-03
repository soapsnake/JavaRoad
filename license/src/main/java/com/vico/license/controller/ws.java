package com.vico.license.controller;

import com.ld.wsdl.server.WebService001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kevin on 2017/4/3.
 */

@RestController
@RequestMapping("webServiceTest")
public class ws {

    @Autowired
    private WebService001 webServiceBean;

    @RequestMapping("test")
    public String test(){
        return webServiceBean.testWebService("liudun");
    }
}
