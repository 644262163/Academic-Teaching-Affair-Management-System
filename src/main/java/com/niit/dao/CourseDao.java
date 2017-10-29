package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Course;

public interface CourseDao {
    public Course selectCourseById(String id);
    public List<Course> selectCourseList();
    public List<Course> selectCourseListByPage(@Param("course") Course course, @Param("start") Integer start, @Param("end") Integer end);
    public Long selectTotal(Course course);
    
    public Integer updateCourse(Course course);
    
    public Integer deleteCourseById(String id);
    
    public Integer insertCourse(Course course);
}
