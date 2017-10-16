package com.niit.service;

import java.util.List;

import com.niit.bean.Administrator;
import com.niit.bean.PageBean;

public interface AdministratorService {
	public Administrator getAdministratorByUser(String user);
	public List<Administrator> getAdministratorList();
	public PageBean<Administrator> getAdministratorListByPage(PageBean<Administrator> page);
}