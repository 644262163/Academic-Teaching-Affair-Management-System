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
		// TODO �Զ����ɵķ������
		Administrator administrator = administratorDao.getAdministratorByUser(user);
		return administrator;
	}

	@Override
	public List<Administrator> getAdministratorList() {
		// TODO �Զ����ɵķ������
		List<Administrator> list = administratorDao.getAdministratorList();
		return list;
	}

	@Override
	public PageBean<Administrator> getAdministratorListByPage(PageBean<Administrator> pageBean) {
		// TODO �Զ����ɵķ������
		//��ѯ��ҳ���
        pageBean.setResult(administratorDao.getAdministratorListByPage(pageBean.getStart(), pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(administratorDao.getTotal());
        return pageBean;
	}

}
