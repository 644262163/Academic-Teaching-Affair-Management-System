package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Administrator;

public interface AdministratorDao {
    public Administrator selectAdministratorById(String id);
    public List<Administrator> selectAdministratorList();
    public List<Administrator> selectAdministratorListByPage(@Param("administrator") Administrator administrator, @Param("start") Integer start, @Param("end") Integer end);
    public Long selectTotal(Administrator administrator);
    
    public Integer updateAdministrator(Administrator administrator);
    
    public Integer deleteAdministratorById(String id);
    
    public Integer insertAdministrator(Administrator administrator);
}
