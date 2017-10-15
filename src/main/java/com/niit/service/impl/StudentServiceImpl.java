package com.niit.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.Student;
import com.niit.dao.StudentDao;
import com.niit.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Resource
	private StudentDao studentDao;
	
	@Override
	public Student getStudent(String id) {
		// TODO �Զ����ɵķ������
		Student student = studentDao.getStudent(id);
		return student;
	}

}
