package com.niit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.Administrator;
import com.niit.bean.PageBean;
import com.niit.dao.AdministratorDao;
import com.niit.service.AdministratorService;

@Service("administratorService")
public class AdministratorServiceImpl implements AdministratorService{

	@Resource
	private AdministratorDao administratorDao;
	
	@Override
	public Administrator getAdministratorByUser(String user) {
		// TODO 自动生成的方法存根
		Administrator administrator = administratorDao.getAdministratorByUser(user);
		return administrator;
	}

	@Override
	public List<Administrator> getAdministratorList() {
		// TODO 自动生成的方法存根
		List<Administrator> list = administratorDao.getAdministratorList();
		return list;
	}

	@Override
	public PageBean<Administrator> getAdministratorListByPage(PageBean<Administrator> pageBean) {
		// TODO 自动生成的方法存根
		//查询分页结果
        pageBean.setResult(administratorDao.getAdministratorListByPage(pageBean.getStart(), pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(administratorDao.getTotal());
        return pageBean;
	}

}
