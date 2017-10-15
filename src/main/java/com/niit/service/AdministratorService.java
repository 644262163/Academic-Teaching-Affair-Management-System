package com.niit.service;

import java.util.List;

import com.niit.bean.Administrator;

public interface AdministratorService {
	public Administrator getAdministratorByUser(String user);
	public List<Administrator> getAdministratorList();
}