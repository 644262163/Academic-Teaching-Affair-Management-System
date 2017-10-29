package com.niit.service;

import java.util.List;

import com.niit.bean.Course;
import com.niit.bean.PageBean;

public interface CourseService {
    public Course selectCourseById(String id);
    public List<Course> selectCourseList();
    public PageBean<Course> selectCourseListByPage(Course course, PageBean<Course> pageBean);
    
    public Integer updateCourse(Course course);
    
    public Integer deleteCourseById(String id);
    
    public Integer insertCourse(Course course);
}
