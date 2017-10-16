package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Student;

public interface StudentDao {
	public Student getStudentById(String id);
	public List<Student> getStudentList();
	public List<Student> getStudentListByPage(@Param("start") Integer start, @Param("end") Integer end);
	public Long getTotal();
}
