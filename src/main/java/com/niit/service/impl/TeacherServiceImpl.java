package com.niit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.PageBean;
import com.niit.bean.Teacher;
import com.niit.dao.TeacherDao;
import com.niit.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

	@Resource
	private TeacherDao teacherDao;
	
	@Override
	public Teacher getTeacherById(String id) {
		// TODO �Զ����ɵķ������
		Teacher teacher = teacherDao.getTeacherById(id);
		return teacher;
	}

	@Override
	public List<Teacher> getTeacherList() {
		// TODO �Զ����ɵķ������
		List<Teacher> list = teacherDao.getTeacherList();
		return list;
	}

	@Override
	public PageBean<Teacher> getTeacherListByPage(PageBean<Teacher> pageBean) {
		// TODO �Զ����ɵķ������
		//��ѯ��ҳ���
        pageBean.setResult(teacherDao.getTeacherListByPage(pageBean.getStart(), pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(teacherDao.getTotal());
		return pageBean;
	}

}
