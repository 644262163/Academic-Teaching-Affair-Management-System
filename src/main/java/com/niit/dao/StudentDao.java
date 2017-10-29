package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Student;

public interface StudentDao {
    public Student selectStudentById(String id);
    public List<Student> selectStudentList();
    public List<Student> selectStudentListByPage(@Param("student") Student student, @Param("start") Integer start, @Param("end") Integer end);
    public Long selectTotal(Student student);
    
    public Integer updateStudent(Student student);
    
    public Integer deleteStudentById(String id);
    
    public Integer insertStudent(Student student);
}
