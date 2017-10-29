package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Teacher;

public interface TeacherDao {
    public Teacher selectTeacherById(String id);
    public List<Teacher> selectTeacherList();
    public List<Teacher> selectTeacherListByPage(@Param("teacher") Teacher teacher, @Param("start") Integer start, @Param("end") Integer end);
    public Long selectTotal(Teacher teacher);
    
    public Integer updateTeacher(Teacher teacher);
    
    public Integer deleteTeacherById(String id);
    
    public Integer insertTeacher(Teacher teacher);
}
