package com.niit.service;

import com.niit.bean.PageBean;
import com.niit.bean.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    public List<StudentCourse> selectStudentCourseList();
    public PageBean<StudentCourse> selectStudentCourseListByPage(StudentCourse studentCourse, PageBean<StudentCourse> pageBean);

    public Integer updateStudentCourse(StudentCourse studentCourse);

    public Integer deleteStudentCourseById(String id);

    public Integer insertStudentCourse(StudentCourse studentCourse);
}
