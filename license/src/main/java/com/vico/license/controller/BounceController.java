package com.vico.license.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: BounceController
 * @Description: 处理页面跳转
 * @author: Liu.Dun
 * @date: 2016年7月5日 下午5:38:47
 */

@Controller
@RequestMapping(value = "/")
public class BounceController {


    @RequestMapping(value = "")
    public String defaultPage(){
        return "License 序列号认证项目!";
    }

    @RequestMapping(value = "tocreatecode")
    public String toCreate(HttpServletRequest request) {
        return "creatcode";
    }

    @RequestMapping(value = "toshowallcodes")
    public String showAll(HttpServletRequest request, HttpServletResponse response) {
        return "showallcodes";
    }


    @RequestMapping(value = "toshowallhospital")
    public String hospital() {
        return "showallhospitals";
    }

    @RequestMapping(value = "toaddhospital")
    public String toAdd() {
        return "addhospital";
    }
}
