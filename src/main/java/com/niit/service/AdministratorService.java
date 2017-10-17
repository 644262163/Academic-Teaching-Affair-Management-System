package com.niit.service;

import java.util.List;

import com.niit.bean.Administrator;
import com.niit.bean.PageBean;

public interface AdministratorService {
	public Administrator selectAdministratorByUser(String user);
	public List<Administrator> selectAdministratorList();
	public PageBean<Administrator> selectAdministratorListByPage(PageBean<Administrator> page);
	
	public Integer updateAdministrator(Administrator administrator);
}