package com.niit.service;

import java.util.List;

import com.niit.bean.PageBean;
import com.niit.bean.Student;

public interface StudentService {
    public Student selectStudentById(String id);
    public List<Student> selectStudentList();
    public PageBean<Student> selectStudentListByPage(Student student, PageBean<Student> pageBean);
    
    public Integer updateStudent(Student student);
    
    public Integer deleteStudentById(String id);
    
    public Integer insertStudent(Student student);
}