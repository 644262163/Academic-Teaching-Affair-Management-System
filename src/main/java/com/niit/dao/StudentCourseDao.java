package com.niit.dao;

import com.niit.bean.StudentCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentCourseDao {
    public List<StudentCourse> selectStudentCourseList();
    public List<StudentCourse> selectStudentCourseListByPage(@Param("studentCourse") StudentCourse studentCourse, @Param("start") Integer start, @Param("end") Integer end);
    public Long selectTotal(StudentCourse studentCourse);

    public Integer updateStudentCourse(StudentCourse studentCourse);

    public Integer deleteStudentCourseById(String id);

    public Integer insertStudentCourse(StudentCourse studentCourse);
}
