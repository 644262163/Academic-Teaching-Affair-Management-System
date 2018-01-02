package com.niit.dao;

import com.niit.bean.TeacherCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherCourseDao {
    public List<TeacherCourse> selectTeacherCourseList();
    public List<TeacherCourse> selectTeacherCourseListByPage(@Param("teacherCourse") TeacherCourse teacherCourse, @Param("start") Integer start, @Param("end") Integer end);
    public Long selectTotal(TeacherCourse teacherCourse);

    public Integer updateTeacherCourse(TeacherCourse teacherCourse);

    public Integer deleteTeacherCourseById(String id);

    public Integer insertTeacherCourse(TeacherCourse teacherCourse);

    public List<TeacherCourse> selectTeacherCourseListByTeacherId(String teacherId);
}
