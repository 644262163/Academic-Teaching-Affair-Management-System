package com.niit.service;

import java.util.List;

import com.niit.bean.PageBean;
import com.niit.bean.Student;

public interface StudentService {
	public Student getStudentById(String id);
	public List<Student> getStudentList();
	public PageBean<Student> getStudentListByPage(PageBean<Student> page);
}