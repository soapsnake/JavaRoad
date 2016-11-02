package com.vico.license.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: BounceController
 * @Description: 处理页面跳转
 * @author: Liu.Dun
 * @date: 2016年7月5日 下午5:38:47
 */

@Controller
@RequestMapping(value="bounceController")
public class BounceController {
	
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
