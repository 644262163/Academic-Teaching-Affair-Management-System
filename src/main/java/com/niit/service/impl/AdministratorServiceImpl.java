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
    public Administrator selectAdministratorByUser(String user) {
        // TODO �Զ����ɵķ������
        Administrator administrator = administratorDao.selectAdministratorByUser(user);
        return administrator;
    }

    @Override
    public List<Administrator> selectAdministratorList() {
        // TODO �Զ����ɵķ������
        List<Administrator> list = administratorDao.selectAdministratorList();
        return list;
    }

    @Override
    public PageBean<Administrator> selectAdministratorListByPage(PageBean<Administrator> pageBean) {
        // TODO �Զ����ɵķ������
        //��ѯ��ҳ���
        pageBean.setResult(administratorDao.selectAdministratorListByPage(pageBean.getStart(), pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(administratorDao.selectTotal());
        return pageBean;
    }

    @Override
    public Integer updateAdministrator(Administrator administrator) {
        // TODO �Զ����ɵķ������
        Integer i = administratorDao.updateAdministrator(administrator);
        return i;
    }

    @Override
    public Integer deleteAdministratorByUser(String user) {
        // TODO �Զ����ɵķ������
        Integer i = administratorDao.deleteAdministratorByUser(user);
        return i;
    }

    @Override
    public Integer insertAdministrator(Administrator administrator) {
        // TODO �Զ����ɵķ������
        Integer i = administratorDao.insertAdministrator(administrator);
        return i;
    }

}
