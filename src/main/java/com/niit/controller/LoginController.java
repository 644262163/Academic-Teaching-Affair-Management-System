package com.niit.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.bean.Administrator;
import com.niit.service.AdministratorService;

@Controller
@RequestMapping()
public class LoginController {
	
	@Resource
	private AdministratorService administratorService;
	
	@RequestMapping("/index")
	public String index(ModelMap resultMap, String user) {
		Administrator administrator = administratorService.getAdministratorByUser(user);
		resultMap.addAttribute("administrator", administrator);
		return "admin";
	}
}