package com.niit.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.Administrator;
import com.niit.dao.AdministratorDao;
import com.niit.service.AdministratorService;

@Service("administratorService")
public class AdministratorServiceImpl implements AdministratorService{

	@Resource
	private AdministratorDao administratorDao;
	
	@Override
	public Administrator getAdministrator(String user) {
		// TODO 自动生成的方法存根
		Administrator administrator = administratorDao.getAdministrator(user);
		return administrator;
	}

}
