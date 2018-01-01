package com.niit.service;

import com.niit.bean.PageBean;
import com.niit.bean.TeacherCourse;

import java.util.List;

public interface TeacherCourseService {
    public List<TeacherCourse> selectTeacherCourseList();
    public PageBean<TeacherCourse> selectTeacherCourseListByPage(TeacherCourse teacherCourse, PageBean<TeacherCourse> pageBean);

    public Integer updateTeacherCourse(TeacherCourse teacherCourse);

    public Integer deleteTeacherCourseById(String id);

    public Integer insertTeacherCourse(TeacherCourse teacherCourse);
}
