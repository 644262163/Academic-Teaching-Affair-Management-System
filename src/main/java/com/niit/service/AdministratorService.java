package com.niit.service;

import java.util.List;

import com.niit.bean.Administrator;
import com.niit.bean.PageBean;

public interface AdministratorService {
    public Administrator selectAdministratorById(String id);
    public List<Administrator> selectAdministratorList();
    public PageBean<Administrator> selectAdministratorListByPage(PageBean<Administrator> pageBean);
    
    public Integer updateAdministrator(Administrator administrator);
    
    public Integer deleteAdministratorById(String id);
    
    public Integer insertAdministrator(Administrator administrator);
}