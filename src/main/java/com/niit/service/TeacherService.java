package com.niit.service;

import java.util.List;

import com.niit.bean.PageBean;
import com.niit.bean.Teacher;

public interface TeacherService {
	public Teacher getTeacherById(String id);
	public List<Teacher> getTeacherList();
	public PageBean<Teacher> getTeacherListByPage(PageBean<Teacher> page);
}