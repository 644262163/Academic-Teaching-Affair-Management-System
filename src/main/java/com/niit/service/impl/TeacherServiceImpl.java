package com.niit.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.Teacher;
import com.niit.dao.TeacherDao;
import com.niit.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

	@Resource
	private TeacherDao teacherDao;
	
	@Override
	public Teacher getTeacher(String id) {
		// TODO 自动生成的方法存根
		Teacher teacher = teacherDao.getTeacher(id);
		return teacher;
	}

}
