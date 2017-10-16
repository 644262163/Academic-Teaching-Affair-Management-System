package com.niit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.PageBean;
import com.niit.bean.Student;
import com.niit.dao.StudentDao;
import com.niit.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Resource
	private StudentDao studentDao;
	
	@Override
	public Student getStudentById(String id) {
		// TODO �Զ����ɵķ������
		Student student = studentDao.getStudentById(id);
		return student;
	}

	@Override
	public List<Student> getStudentList() {
		// TODO �Զ����ɵķ������
		List<Student> list = studentDao.getStudentList();
		return list;
	}

	@Override
	public PageBean<Student> getStudentListByPage(PageBean<Student> pageBean) {
		// TODO �Զ����ɵķ������
		//��ѯ��ҳ���
        pageBean.setResult(studentDao.getStudentListByPage(pageBean.getStart(), pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(studentDao.getTotal());
		return pageBean;
	}

}
