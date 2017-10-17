package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Administrator;
import com.niit.bean.Student;

public interface StudentDao {
    public Student selectStudentById(String id);
    public List<Student> selectStudentList();
    public List<Student> selectStudentListByPage(@Param("start") Integer start, @Param("end") Integer end);
    public Long selectTotal();
}
