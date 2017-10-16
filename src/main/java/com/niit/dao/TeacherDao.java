package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Teacher;

public interface TeacherDao {
	public Teacher getTeacherById(String id);
	public List<Teacher> getTeacherList();
	public List<Teacher> getTeacherListByPage(@Param("start") Integer start, @Param("end") Integer end);
	public Long getTotal();
}
