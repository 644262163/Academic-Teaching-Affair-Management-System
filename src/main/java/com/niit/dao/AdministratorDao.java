package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Administrator;

public interface AdministratorDao {
	public Administrator getAdministratorByUser(String user);
	public List<Administrator> getAdministratorList();
	public List<Administrator> getAdministratorListByPage(@Param("start") Integer start, @Param("end") Integer end);
	public Long getTotal();
}
