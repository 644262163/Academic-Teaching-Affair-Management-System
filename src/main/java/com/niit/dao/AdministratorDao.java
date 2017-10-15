package com.niit.dao;

import java.util.List;

import com.niit.bean.Administrator;

public interface AdministratorDao {
	public Administrator getAdministratorByUser(String user);
	public List<Administrator> getAdministratorList();
}
