package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Administrator;

public interface AdministratorDao {
	public Administrator selectAdministratorByUser(String user);
	public List<Administrator> selectAdministratorList();
	public List<Administrator> selectAdministratorListByPage(@Param("start") Integer start, @Param("end") Integer end);
	public Long selectTotal();
	
	public Integer updateAdministrator(Administrator administrator);
	
	public Integer deleteAdministratorByUser(String user);
}
